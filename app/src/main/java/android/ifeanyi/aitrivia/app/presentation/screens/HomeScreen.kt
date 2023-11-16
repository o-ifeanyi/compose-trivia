package android.ifeanyi.aitrivia.app.presentation.screens

import android.ifeanyi.aitrivia.core.navigation.AppScreens
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(controller: NavHostController) {
    Button(onClick = { controller.navigate(AppScreens.PreTriviaScreen.name) }) {
        Text(text = "Start Trivia")
    }
}