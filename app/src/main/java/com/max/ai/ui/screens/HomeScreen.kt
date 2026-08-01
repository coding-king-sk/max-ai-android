package com.max.ai.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.max.ai.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToNotes: () -> Unit
) {
    val messages = remember { mutableStateListOf<String>() }
    val isListening = remember { mutableStateOf(false) }
    val isThinking = remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().background(MaxBackground)) {
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Max AI", color = MaxOrange, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Row {
                IconButton(onClick = onNavigateToNotes) {
                    Icon(Icons.Default.Description, "Notes", tint = MaxTextSecondary)
                }
                IconButton(onClick = onNavigateToSettings) {
                    Icon(Icons.Default.Settings, "Settings", tint = MaxTextSecondary)
                }
            }
        }

        // Neural Orb
        Box(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            NeuralOrb(isActive = isListening.value || isThinking.value)
        }

        // Chat messages
        LazyColumn(
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
            state = listState
        ) {
            items(messages) { msg ->
                ChatBubble(message = msg)
            }
        }

        // Status text
        if (isListening.value) {
            Text("Listening...", color = MaxCyan, modifier = Modifier.padding(start = 16.dp))
        } else if (isThinking.value) {
            Text("Thinking...", color = MaxOrange, modifier = Modifier.padding(start = 16.dp))
        }

        // Voice input bar
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    isListening.value = !isListening.value
                    if (isListening.value) {
                        coroutineScope.launch {
                            kotlinx.coroutines.delay(2000)
                            isListening.value = false
                            isThinking.value = true
                            kotlinx.coroutines.delay(1500)
                            isThinking.value = false
                            messages.add("User: Hello Max!")
                            messages.add("Max: Namaste! Main aapki kya madad kar sakta hoon?")
                            listState.animateScrollToItem(messages.size - 1)
                        }
                    }
                },
                modifier = Modifier.size(72.dp).clip(CircleShape).background(
                    if (isListening.value || isThinking.value) MaxOrange else MaxSurfaceElevated
                )
            ) {
                Icon(
                    if (isListening.value) Icons.Default.Mic else Icons.Default.MicOff,
                    "Mic",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
fun NeuralOrb(isActive: Boolean) {
    val infiniteTransition = rememberInfiniteTransition()
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.8f, targetValue = 1.2f,
        animationSpec = infiniteRepeatable(tween(1500, easing = EaseInOutCubic), RepeatMode.Reverse)
    )
    val rotate by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(8000, easing = LinearEasing), RepeatMode.Restart)
    )

    Canvas(modifier = Modifier.size(120.dp * (if (isActive) pulse else 1f))) {
        val cx = size.width / 2
        val cy = size.height / 2
        val radius = size.width / 2.5f

        drawCircle(
            brush = Brush.radialGradient(listOf(MaxOrange.copy(alpha = 0.8f), MaxOrange.copy(alpha = 0f))),
            radius = radius * 1.5f, center = Offset(cx, cy)
        )
        drawCircle(
            color = MaxOrange.copy(alpha = if (isActive) 0.9f else 0.4f),
            radius = radius, center = Offset(cx, cy)
        )
        for (i in 0 until 3) {
            val angle = Math.toRadians((rotate + i * 120.0).toDouble())
            drawCircle(
                color = MaxCyan.copy(alpha = 0.6f),
                radius = 4f,
                center = Offset(cx + (radius * 0.7f * Math.cos(angle)).toFloat(),
                    cy + (radius * 0.7f * Math.sin(angle)).toFloat())
            )
        }
    }
}

@Composable
fun ChatBubble(message: String) {
    val isUser = message.startsWith("User:")
    val text = message.removePrefix("User: ").removePrefix("Max: ")

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (isUser) MaxOrange else MaxSurfaceElevated,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = text,
                color = MaxTextPrimary,
                modifier = Modifier.padding(12.dp),
                fontSize = 14.sp
            )
        }
    }
}
