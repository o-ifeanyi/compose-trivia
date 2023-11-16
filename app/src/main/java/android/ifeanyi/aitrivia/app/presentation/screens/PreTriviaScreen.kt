package android.ifeanyi.aitrivia.app.presentation.screens

import android.annotation.SuppressLint
import android.ifeanyi.aitrivia.app.data.model.TriviaInfoModel
import android.ifeanyi.aitrivia.app.presentation.components.ChoiceChipComponent
import android.ifeanyi.aitrivia.app.presentation.viewmodels.TriviaLoadState
import android.ifeanyi.aitrivia.app.presentation.viewmodels.TriviaViewModel
import android.ifeanyi.aitrivia.core.navigation.AppScreens
import android.ifeanyi.aitrivia.core.services.SnackBarService
import android.ifeanyi.aitrivia.core.utils.Constants
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PreTriviaScreen(
    controller: NavHostController,
    triviaViewModel: TriviaViewModel
) {
    val triviaInfo = remember { mutableStateOf(TriviaInfoModel()) }
    val state = triviaViewModel.uiState.collectAsState().value.state

    Scaffold(
        topBar = {
            TopAppBar(title = { })
        },
        bottomBar = {
            when (state) {
                TriviaLoadState.FetchingTrivia -> Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }

                else -> {
                    ElevatedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        contentPadding = PaddingValues(15.dp),
                        onClick = {
                            val isValid = triviaInfo.value.validate()
                            if (!isValid) {
                                SnackBarService.displayMessage("Missing required fields")
                                return@ElevatedButton
                            }
                            triviaViewModel.generateTrivia(info = triviaInfo.value) {
                                controller.navigate(AppScreens.TriviaScreen.name) {
                                    popUpTo(AppScreens.HomeScreen.name)
                                }
                            }
                        }) {
                        Text(text = "Continue")
                    }
                }
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = it.calculateTopPadding(),
                start = 15.dp, end = 15.dp,
                bottom = it.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                ChoiceChipComponent(
                    header = "Number of questions",
                    choice = triviaInfo.value.amount,
                    choices = Constants.amountOptions,
                    onSelected = { value ->
                        triviaInfo.value = triviaInfo.value.copy(amount = value)
                    },
                )
            }
            item {
                ChoiceChipComponent(
                    header = "Difficulty",
                    choice = triviaInfo.value.difficulty,
                    choices = Constants.difficultyOptions,
                    onSelected = { value ->
                        triviaInfo.value = triviaInfo.value.copy(difficulty = value)
                    },
                )
            }
            item {
                ChoiceChipComponent(
                    header = "Type",
                    choice = triviaInfo.value.type,
                    choices = Constants.triviaTypes,
                    onSelected = { value ->
                        triviaInfo.value = triviaInfo.value.copy(type = value)
                    },
                )
            }
            item {
                ChoiceChipComponent(
                    header = "Category",
                    choice = triviaInfo.value.category,
                    choices = Constants.triviaCategories,
                    onSelected = { value ->
                        triviaInfo.value = triviaInfo.value.copy(category = value)
                    },
                )
            }
        }
    }
}

