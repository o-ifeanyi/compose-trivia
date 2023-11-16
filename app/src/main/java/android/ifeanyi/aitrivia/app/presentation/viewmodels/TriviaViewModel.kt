package android.ifeanyi.aitrivia.app.presentation.viewmodels

import android.ifeanyi.aitrivia.app.data.model.TriviaInfoModel
import android.ifeanyi.aitrivia.app.data.model.TriviaModel
import android.ifeanyi.aitrivia.app.domain.TriviaRepository
import android.ifeanyi.aitrivia.core.resourse.Resource
import android.ifeanyi.aitrivia.core.services.SnackBarService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class TriviaLoadState { Idle, FetchingTrivia }

data class TriviaState(
    val state: TriviaLoadState = TriviaLoadState.Idle,
    val trivia: TriviaModel? = null,
)
@HiltViewModel
class TriviaViewModel @Inject constructor(private val triviaRepository: TriviaRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(TriviaState())
    val uiState = _uiState.asStateFlow()

    fun generateTrivia(info: TriviaInfoModel, onSuccess: () -> Unit) {
        _uiState.update { it.copy(state = TriviaLoadState.FetchingTrivia) }
        viewModelScope.launch {
            when(val res = triviaRepository.generateTrivia(info)) {
                is Resource.Success -> {
                    _uiState.update { it.copy(trivia = res.data!!) }
                    onSuccess.invoke()
                }
                is Resource.Error -> SnackBarService.displayMessage(message = res.message!!)
            }
            _uiState.update { it.copy(state = TriviaLoadState.Idle) }
        }
    }
}