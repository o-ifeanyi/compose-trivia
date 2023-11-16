package android.ifeanyi.aitrivia.app.presentation.components

import android.ifeanyi.aitrivia.app.data.model.TriviaQuestion
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BackViewComponent(modifier: Modifier = Modifier, question: TriviaQuestion) {
    LazyColumn(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        item {
            Text(text = question.question, style = MaterialTheme.typography.titleMedium)
        }
        items(question.options) {
            OptionComponent(
                label = {
                    Text(text = it, modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp))
                },
                selected = it == "True",
                onSelected = {},
            )
        }
    }
}