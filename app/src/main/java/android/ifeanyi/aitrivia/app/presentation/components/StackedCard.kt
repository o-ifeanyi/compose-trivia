package android.ifeanyi.aitrivia.app.presentation.components

import android.ifeanyi.aitrivia.app.data.model.Direction
import android.ifeanyi.aitrivia.app.data.model.TriviaQuestion
import android.ifeanyi.aitrivia.core.utils.Config
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun StackedCard(
    question: TriviaQuestion,
    front: @Composable () -> Unit,
    back: @Composable (Modifier) -> Unit,
    updateIndex: (TriviaQuestion) -> Unit
) {
    val config = Config()
    val flipped = remember {
        mutableStateOf(false)
    }
    val h = remember { mutableStateOf(330.dp) }
    val w = remember { mutableStateOf(260.dp) }
    val o = remember { mutableFloatStateOf(0f) }
    val height = animateDpAsState(targetValue = h.value, label = "")
    val width = animateDpAsState(targetValue = w.value, label = "")
    val offset = animateFloatAsState(targetValue = o.floatValue, label = "")
    val rotation = animateFloatAsState(
        targetValue = if (flipped.value) 180f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "AnimateRotation"
    )


    val i = min(question.zIndex!!, 6)
    val isLeft = question.direction == Direction.Left

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = if (isLeft) (offset.value.roundToInt() - (i * (32 - (i * 2)))) else (offset.value.roundToInt() + (i * (32 - (i * 2)))),
                    y = 0
                )
            }
            .graphicsLayer {
                rotationY = rotation.value
                cameraDistance = 10f * density
            }

            .rotate(if (isLeft) (offset.value / 15) - (i * 3) else (offset.value / 15) + (i * 3))
            .height(if (flipped.value) config.height(p = 0.8) else (height.value - (i * 25).dp))
            .width(if (flipped.value) config.width(p = 1.0) else width.value)
            .animateContentSize(animationSpec = spring())
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(25.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(25.dp)
            )
            .clickable {
                if (i != 0) return@clickable
                flipped.value = !flipped.value
            }
            .pointerInput(question.index) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        if (i != 0 || flipped.value) return@detectDragGestures
                        change.consume()
                        o.floatValue = offset.value + dragAmount.x * 3
                        h.value = (330 - min(40f, abs(offset.value))).dp
                        w.value = (260 - min(40f, abs(offset.value))).dp
                    },
                    onDragEnd = {
                        if (i != 0 || flipped.value) return@detectDragGestures
                        val distance = offset.value

                        val direction =
                            if (distance > 80) Direction.Right else if (distance < -80) Direction.Left else question.direction
                        o.floatValue = 0f
                        h.value = 330.dp
                        w.value = 260.dp
                        if (abs(distance) > 100) {
                            updateIndex.invoke(
                                question.copy(direction = direction)
                            )
                        }


                    }
                )
            },
        contentAlignment = Alignment.TopCenter
    ) {
        if (flipped.value) {
            back.invoke(Modifier.graphicsLayer {
                rotationY = rotation.value
            })
        } else {
            front.invoke()
        }
    }
}