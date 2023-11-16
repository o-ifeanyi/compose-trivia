package android.ifeanyi.aitrivia.app.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun <T> ChoiceChipComponent(header: String, choice: T?, choices: List<T>, onSelected: (T) -> Unit) {
    Column {
        Text(
            text = header,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(5.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            choices.forEach {
                FilterChip(
                    selected = choice == it,
                    onClick = { onSelected.invoke(it) },
                    label = { Text(text = it.toString()) },
                    shape = RoundedCornerShape(25.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.tertiary,
                        disabledContainerColor = MaterialTheme.colorScheme.background,
                        selectedLabelColor = MaterialTheme.colorScheme.onTertiary,
                        disabledLabelColor = MaterialTheme.colorScheme.onBackground,
                    )
                )
            }
        }
    }
}