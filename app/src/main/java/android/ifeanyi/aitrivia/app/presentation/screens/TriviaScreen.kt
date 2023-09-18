package android.ifeanyi.aitrivia.app.presentation.screens

import android.ifeanyi.aitrivia.core.theme.AITriviaTheme
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.util.UUID
import kotlin.math.abs
import kotlin.math.roundToInt

enum class Direction {
    Left, Right
}

data class Card(
    val id: UUID = UUID.randomUUID(),
    val index: Int,
    val zIndex: Int,
    val direction: Direction,
    val color: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaScreen(controller: NavHostController) {
    val cards = remember {
        mutableStateListOf(
            Card(index = 0, zIndex = 0, direction = Direction.Left, color = Color.Magenta),
            Card(index = 1, zIndex = 1, direction = Direction.Left, color = Color.Blue),
            Card(index = 2, zIndex = 2, direction = Direction.Left, color = Color.Green),
            Card(index = 3, zIndex = 3, direction = Direction.Left, color = Color.Red),
            Card(index = 4, zIndex = 4, direction = Direction.Left, color = Color.DarkGray),
            Card(index = 5, zIndex = 5, direction = Direction.Left, color = Color.Blue),
        )
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 20.dp + paddingValues.calculateTopPadding()
                ),
            ) {
                Text(text = "Browse", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.weight(1f))
                Circle()
                Spacer(modifier = Modifier.width(20.dp))
                Circle()
            }

            Box(contentAlignment = Alignment.Center, modifier = Modifier.weight(1f)) {
                for (card in cards.sortedBy { it.zIndex }.reversed()) {
                    StackedCard(card) { swiped ->
                        cards[swiped.index] = swiped

                        val right = cards.filter { it.direction == Direction.Right }
                        val left = cards.filter { it.direction == Direction.Left }

                        if ((swiped.direction == Direction.Right && left.isNotEmpty()) || (swiped.direction == Direction.Left && right.isNotEmpty())) {
                            for (i in 0 until cards.size) {
                                when (swiped.direction) {
                                    Direction.Left -> {
                                        if (right.any { it.id == cards[i].id }) {
                                            cards[i] = Card(
                                                id = cards[i].id,
                                                index = cards[i].index,
                                                zIndex = cards[i].zIndex - 1,
                                                direction = cards[i].direction,
                                                color = cards[i].color
                                            )
                                        } else {
                                            cards[i] = Card(
                                                id = cards[i].id,
                                                index = cards[i].index,
                                                zIndex = cards[i].zIndex + 1,
                                                direction = cards[i].direction,
                                                color = cards[i].color
                                            )
                                        }
                                    }

                                    Direction.Right -> {
                                        if (left.any { it.id == cards[i].id }) {
                                            cards[i] = Card(
                                                id = cards[i].id,
                                                index = cards[i].index,
                                                zIndex = cards[i].zIndex - 1,
                                                direction = cards[i].direction,
                                                color = cards[i].color
                                            )
                                        } else {
                                            cards[i] = Card(
                                                id = cards[i].id,
                                                index = cards[i].index,
                                                zIndex = cards[i].zIndex + 1,
                                                direction = cards[i].direction,
                                                color = cards[i].color
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 40.dp,
                        vertical = 20.dp + paddingValues.calculateTopPadding()
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Circle()
                Circle()
                Circle()
                Circle()
            }
        }
    }
}

@Composable
fun StackedCard(card: Card, updateIndex: (Card) -> Unit) {
    val config = LocalConfiguration.current
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
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow),
        label = "AnimateRotation"
    )


    val i = card.zIndex
    val isLeft = card.direction == Direction.Left

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
            .height(if (flipped.value) config.screenHeightDp.dp else (height.value - (i * 25).dp))
            .width(if (flipped.value) config.screenWidthDp.dp else width.value)
            .animateContentSize(animationSpec = spring())
            .background(
                color = card.color,
                shape = RoundedCornerShape(25.dp)
            )
            .clickable {
                if (i != 0) return@clickable
                flipped.value = !flipped.value
            }
            .pointerInput(card.id) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        if (i != 0 || flipped.value) return@detectDragGestures
                        change.consume()
                        o.floatValue = offset.value + dragAmount.x * 2
                        if (abs(offset.value) <= 50) {
                            h.value = (330 - abs(offset.value)).dp
                            w.value = (260 - abs(offset.value)).dp
                        }

                    },
                    onDragEnd = {
                        if (i != 0 || flipped.value) return@detectDragGestures
                        val distance = offset.value

                        val direction =
                            if (distance > 80) Direction.Right else if (distance < -80) Direction.Left else card.direction
                        o.floatValue = 0f
                        h.value = 330.dp
                        w.value = 260.dp
                        if (abs(distance) > 100) {
                            updateIndex.invoke(
                                Card(
                                    id = card.id,
                                    index = card.index,
                                    zIndex = card.zIndex,
                                    direction = direction,
                                    color = card.color
                                )
                            )
                        }


                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        if (flipped.value) {
            Text(
                text = "Back",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.graphicsLayer {
                    rotationY = rotation.value
                }
            )
        } else {
            Text(
                text = "Front",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Composable
fun Circle() {
    Box(
        modifier = Modifier
            .size(20.dp)
            .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
    )
}

@Composable
@Preview
fun PreviewTriviaScreen() {
    AITriviaTheme(darkTheme = true) {
        TriviaScreen(controller = NavHostController(context = LocalContext.current))
    }
}