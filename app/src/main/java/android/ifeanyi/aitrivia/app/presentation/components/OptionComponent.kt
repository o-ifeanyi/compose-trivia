package android.ifeanyi.aitrivia.app.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionComponent(
    label: @Composable () -> Unit,
    selected: Boolean,
    onSelected: (Boolean) -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = { onSelected.invoke(!selected) },
        label = label,
        shape = RoundedCornerShape(25.dp),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.tertiary,
            containerColor = MaterialTheme.colorScheme.onTertiary,
            selectedLabelColor = MaterialTheme.colorScheme.onTertiary,
            disabledLabelColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
}