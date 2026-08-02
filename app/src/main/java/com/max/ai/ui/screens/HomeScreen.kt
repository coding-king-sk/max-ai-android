package com.max.ai.ui.screens
import androidx.compose.animation.core.*; import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background; import androidx.compose.foundation.layout.*; import androidx.compose.foundation.lazy.LazyColumn; import androidx.compose.foundation.lazy.items; import androidx.compose.foundation.lazy.rememberLazyListState; import androidx.compose.foundation.shape.CircleShape; import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons; import androidx.compose.material.icons.filled.*; import androidx.compose.material3.*; import androidx.compose.runtime.*; import androidx.compose.ui.Alignment; import androidx.compose.ui.Modifier; import androidx.compose.ui.draw.clip; import androidx.compose.ui.geometry.Offset; import androidx.compose.ui.graphics.Brush; import androidx.compose.ui.graphics.Color; import androidx.compose.ui.text.font.FontWeight; import androidx.compose.ui.unit.dp; import androidx.compose.ui.unit.sp
import com.max.ai.ui.theme.*; import kotlinx.coroutines.launch
@Composable fun HomeScreen(s: () -> Unit, n: () -> Unit) {
    val msgs = remember { mutableStateListOf("M: Namaste! Max AI here. Say Hey Max to start.") }; val l = remember { mutableStateOf(false) }; val t = remember { mutableStateOf(false) }; val list = rememberLazyListState(); val scope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize().background(Bg)) {
        Row(Modifier.fillMaxWidth().padding(16.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) { Text("Max AI", Orange, fontSize=24.sp, fontWeight=FontWeight.Bold); Row { IconButton(n) { Icon(Icons.Default.Description, "Notes", Txt2) }; IconButton(s) { Icon(Icons.Default.Settings, "Settings", Txt2) } } }
        Box(Modifier.fillMaxWidth().height(180.dp), contentAlignment=Alignment.Center) {
            val tr = rememberInfiniteTransition(); val p by tr.animateFloat(0.8f,1.2f, infiniteRepeatable(tween(1500),RepeatMode.Reverse))
            Canvas(Modifier.size(100.dp * (if(l.value||t.value) p else 1f))) {
                val c=Offset(size.width/2,size.height/2); val r=size.width/2.5f
                drawCircle(Brush.radialGradient(listOf(Orange.copy(alpha=0.8f),Orange.copy(alpha=0f))),r*1.5f,c)
                drawCircle(Orange.copy(alpha=if(l.value||t.value)0.9f else 0.4f),r,c)
            }
        }
        LazyColumn(Modifier.weight(1f).padding(horizontal=16.dp), list) { items(msgs) { m -> val isU = m.startsWith("U:"); Row(Modifier.fillMaxWidth().padding(vertical=4.dp), if(isU)Arrangement.End else Arrangement.Start) { Surface(if(isU)Orange else Sfc2, RoundedCornerShape(16.dp), Modifier.widthIn(max=280.dp)) { Text(m.removePrefix("U:").removePrefix("M:"), Txt, Modifier.padding(12.dp), fontSize=14.sp) } } } }
        if(l.value) Text("Listening...", Cyan, Modifier.padding(start=16.dp)) else if(t.value) Text("Thinking...", Orange, Modifier.padding(start=16.dp))
        Row(Modifier.fillMaxWidth().padding(16.dp), Arrangement.Center, Alignment.CenterVertically) {
            IconButton({l.value=!l.value; if(l.value){scope.launch{kotlinx.coroutines.delay(2000);l.value=false;t.value=true;kotlinx.coroutines.delay(1500);t.value=false;msgs.add("U: Hello!");msgs.add("M: Namaste! Kaise help karun?");list.animateScrollToItem(msgs.size-1)}}}, Modifier.size(72.dp).clip(CircleShape).background(if(l.value||t.value)Orange else Sfc2)) { Icon(if(l.value)Icons.Default.Mic else Icons.Default.MicOff, "Mic", Color.White, Modifier.size(32.dp)) }
        }
    }
}
