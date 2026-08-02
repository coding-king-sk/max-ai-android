package com.max.ai.ui.screens
import androidx.compose.foundation.background; import androidx.compose.foundation.layout.*; import androidx.compose.foundation.lazy.LazyColumn; import androidx.compose.foundation.lazy.items; import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons; import androidx.compose.material.icons.filled.*; import androidx.compose.material3.*; import androidx.compose.runtime.*; import androidx.compose.ui.Alignment; import androidx.compose.ui.Modifier; import androidx.compose.ui.text.font.FontWeight; import androidx.compose.ui.unit.dp; import androidx.compose.ui.unit.sp
import com.max.ai.ui.theme.*
@Composable fun NotesScreen(back: () -> Unit) {
    val notes = remember { mutableStateListOf("Meeting Notes", "Todo List") }
    Column(Modifier.fillMaxSize().background(Bg).padding(16.dp)) {
        Row(Alignment.CenterVertically) { IconButton(back) { Icon(Icons.Default.ArrowBack, "Back", Txt) }; Text("Notes", Txt, fontSize=22.sp, fontWeight=FontWeight.Bold) }
        Spacer(Modifier.height(16.dp))
        LazyColumn { items(notes) { n -> Surface(Modifier.fillMaxWidth().padding(vertical=4.dp), Sfc, RoundedCornerShape(12.dp)) { Row(Modifier.padding(16.dp), Arrangement.SpaceBetween) { Text(n, Txt, fontSize=16.sp); Icon(Icons.Default.ChevronRight, "Open", Txt3, Modifier.size(20.dp)) } } } }
    }
}
