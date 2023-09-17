package android.ifeanyi.aitrivia.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Config {
    @Composable
    fun height(p: Double): Dp {
        val config = LocalConfiguration.current
        return (config.screenHeightDp * p).dp
    }

    @Composable
    fun width(p: Double): Dp {
        val config = LocalConfiguration.current
        return (config.screenWidthDp * p).dp
    }
}