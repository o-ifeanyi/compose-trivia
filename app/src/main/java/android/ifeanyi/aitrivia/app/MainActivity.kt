package android.ifeanyi.aitrivia.app

import android.ifeanyi.aitrivia.core.navigation.AppNavigation
import android.ifeanyi.aitrivia.core.services.SnackBarService
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import android.ifeanyi.aitrivia.core.theme.AITriviaTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AITriviaTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    val controller = rememberNavController()
                    val navBackStackEntry = controller.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry.value?.destination


                    AppNavigation(controller = controller)

                    val snackBarState = SnackBarService.snackBarState.collectAsState().value

                    AnimatedVisibility(
                        modifier = Modifier.align(Alignment.TopCenter),
                        visible = snackBarState.hasMessage,
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        Snackbar(
                            modifier = Modifier.padding(10.dp),
                            containerColor = if (snackBarState.isError) Color.Red else Color.Green,
                            contentColor = Color.White
                        ) { Text(text = snackBarState.message) }
                    }
                }
            }
        }
    }
}
