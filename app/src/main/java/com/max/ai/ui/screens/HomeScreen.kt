package com.max.ai.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.max.ai.ui.theme.Bg
import com.max.ai.ui.theme.Cyan
import com.max.ai.ui.theme.Orange
import com.max.ai.ui.theme.Sfc2
import com.max.ai.ui.theme.Txt
import com.max.ai.ui.theme.Txt2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(onSettings: () -> Unit, onNotes: () -> Unit) {
    val messages = remember { mutableStateListOf("MAX: Namaste! Main Max AI hoon.") }
    val isListening = remember { mutableStateOf(false) }
    val isThinking = remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val transition = rememberInfiniteTransition(label = "orb")
    val pulse by transition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Column(modifier = Modifier.fillMaxSize().background(Bg)) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Max AI",
                color = Orange,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Row {
                IconButton(onClick = onNotes) {
                    Icon(
                        imageVector = Icons.Filled.Description,
                        contentDescription = "Notes",
                        tint = Txt2
                    )
                }
                IconButton(onClick = onSettings) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings",
                        tint = Txt2
                    )
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth().height(180.dp),
            contentAlignment = Alignment.Center
        ) {
            val active = isListening.value || isThinking.value
            val orbSize = if (active) 110.dp * pulse else 110.dp
            Canvas(modifier = Modifier.size(orbSize)) {
                val center = Offset(size.width / 2f, size.height / 2f)
                val radius = size.width / 2.5f
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Orange.copy(alpha = 0.7f), Orange.copy(alpha = 0f))
                    ),
                    radius = radius * 1.6f,
                    center = center
                )
                drawCircle(
                    color = if (active) Orange else Orange.copy(alpha = 0.45f),
                    radius = radius,
                    center = center
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
            state = listState
        ) {
            items(messages) { message ->
                val isUser = message.startsWith("USER:")
                val body = message.removePrefix("USER:").removePrefix("MAX:").trim()
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
                ) {
                    Surface(
                        modifier = Modifier.widthIn(max = 280.dp),
                        color = if (isUser) Orange else Sfc2,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = body,
                            color = Txt,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }
        }

        if (isListening.value) {
            Text(
                text = "Listening...",
                color = Cyan,
                fontSize = 13.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        } else if (isThinking.value) {
            Text(
                text = "Thinking...",
                color = Orange,
                fontSize = 13.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    isListening.value = !isListening.value
                    if (isListening.value) {
                        scope.launch {
                            delay(1500)
                            isListening.value = false
                            isThinking.value = true
                            delay(1200)
                            isThinking.value = false
                            messages.add("USER: Hello Max")
                            messages.add("MAX: Namaste! Kaise help karun?")
                            listState.animateScrollToItem(messages.size - 1)
                        }
                    }
                },
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(if (isListening.value || isThinking.value) Orange else Sfc2)
            ) {
                Icon(
                    imageVector = if (isListening.value) Icons.Filled.Mic else Icons.Filled.MicOff,
                    contentDescription = "Microphone",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
