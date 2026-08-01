package com.max.ai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.max.ai.ui.theme.*

@Composable
fun NotesScreen(onBack: () -> Unit) {
    val notes = remember { mutableStateListOf(
        "Sample Note 1", "Todo List", "Meeting Notes"
    ) }

    Column(modifier = Modifier.fillMaxSize().background(MaxBackground).padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back", tint = MaxTextPrimary) }
            Text("Notes", color = MaxTextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(notes) { note ->
                Surface(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    color = MaxSurface,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(note, color = MaxTextPrimary, fontSize = 16.sp)
                        Icon(
                            Icons.Default.ChevronRight, "Open",
                            tint = MaxTextMuted, modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
