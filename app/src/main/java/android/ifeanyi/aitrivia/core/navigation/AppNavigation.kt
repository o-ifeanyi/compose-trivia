package android.ifeanyi.aitrivia.core.navigation

import android.ifeanyi.aitrivia.app.presentation.screens.HomeScreen
import android.ifeanyi.aitrivia.app.presentation.screens.PreTriviaScreen
import android.ifeanyi.aitrivia.app.presentation.screens.TriviaScreen
import android.ifeanyi.aitrivia.app.presentation.viewmodels.TriviaViewModel
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(controller: NavHostController) {
    val triviaViewModel: TriviaViewModel = hiltViewModel()
    NavHost(navController = controller, startDestination = AppScreens.HomeScreen.name) {
        composable(AppScreens.HomeScreen.name) {
            HomeScreen(controller = controller)
        }
        composable(AppScreens.PreTriviaScreen.name) {
            PreTriviaScreen(controller = controller, triviaViewModel = triviaViewModel)
        }
        composable(AppScreens.TriviaScreen.name) {
            TriviaScreen(controller = controller, triviaViewModel = triviaViewModel)
        }
    }
}