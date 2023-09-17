package android.ifeanyi.aitrivia.core.navigation

import android.ifeanyi.aitrivia.app.presentation.screens.HomeScreen
import android.ifeanyi.aitrivia.app.presentation.screens.TriviaScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(controller: NavHostController) {
    NavHost(navController = controller, startDestination = AppScreens.HomeScreen.name) {
        composable(AppScreens.HomeScreen.name) {
            HomeScreen(controller = controller)
        }
        composable(AppScreens.TriviaScreen.name) {
            TriviaScreen(controller = controller)
        }
    }
}