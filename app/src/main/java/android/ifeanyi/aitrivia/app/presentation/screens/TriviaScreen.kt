package android.ifeanyi.aitrivia.app.presentation.screens

import android.annotation.SuppressLint
import android.ifeanyi.aitrivia.app.data.model.Direction
import android.ifeanyi.aitrivia.app.presentation.components.BackViewComponent
import android.ifeanyi.aitrivia.app.presentation.components.StackedCard
import android.ifeanyi.aitrivia.app.presentation.viewmodels.TriviaViewModel
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaScreen(controller: NavHostController, triviaViewModel: TriviaViewModel) {
    val state = triviaViewModel.uiState.collectAsState()
    val trivia = state.value.trivia
    Log.d("TRIVIA", trivia.toString())
    val questions = remember(trivia) {
        (0 until (trivia?.questions?.size ?: 0)).mapNotNull {
            trivia?.questions?.get(it)?.copy(index = it, zIndex = it, direction = Direction.Left)
        }.toMutableStateList()
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { /*TODO*/ })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding() + 10.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
                for (question in questions.sortedBy { it.zIndex }.reversed()) {
                    StackedCard(
                        question = question,
                        front = {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(
                                    text = "Front",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        },
                        back = {
                            BackViewComponent(question = question, modifier = it)
                        },
                    ) { swiped ->
                        questions[swiped.index!!] = swiped

                        val right = questions.filter { it.direction == Direction.Right }
                        val left = questions.filter { it.direction == Direction.Left }

                        if ((swiped.direction == Direction.Right && left.isNotEmpty()) || (swiped.direction == Direction.Left && right.isNotEmpty())) {
                            for (i in questions.indices) {
                                when (swiped.direction) {
                                    Direction.Left -> {
                                        if (right.any { it.index == questions[i].index }) {
                                            questions[i] = questions[i].copy(
                                                zIndex = questions[i].zIndex!! - 1
                                            )
                                        } else {
                                            questions[i] = questions[i].copy(
                                                zIndex = questions[i].zIndex!! + 1
                                            )
                                        }
                                    }

                                    Direction.Right -> {
                                        if (left.any { it.index == questions[i].index }) {
                                            questions[i] = questions[i].copy(
                                                zIndex = questions[i].zIndex!! - 1
                                            )
                                        } else {
                                            questions[i] = questions[i].copy(
                                                zIndex = questions[i].zIndex!! + 1
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
