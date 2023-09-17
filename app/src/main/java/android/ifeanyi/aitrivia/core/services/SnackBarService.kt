package android.ifeanyi.aitrivia.core.services

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SnackBarState(
    val hasMessage: Boolean = false,
    val isError: Boolean = true,
    val message: String = ""
)

object SnackBarService : ViewModel() {
    private val _snackBarState = MutableStateFlow(SnackBarState())
    val snackBarState = _snackBarState.asStateFlow()

    fun displayMessage(message: String, isError: Boolean = true) = viewModelScope.launch {
        _snackBarState.update { it.copy(hasMessage = true, isError = isError, message = message) }
        delay(4000L)
        _snackBarState.update { it.copy(hasMessage = false, isError = isError, message = "") }
    }
}