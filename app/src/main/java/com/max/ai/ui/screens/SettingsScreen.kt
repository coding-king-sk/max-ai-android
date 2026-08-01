package com.max.ai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    var apiKey by mutableStateOf("")
    var wakeWordEnabled by mutableStateOf(true)
    var voiceEnabled by mutableStateOf(true)
    var overlayEnabled by mutableStateOf(false)
    fun saveApiKey() {}
}

@Composable
fun SettingsScreen(onBack: () -> Unit, viewModel: SettingsViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize().background(MaxBackground).verticalScroll(scrollState).padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back", tint = MaxTextPrimary) }
            Text("Settings", color = MaxTextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(24.dp))

        // API Keys Section
        SectionHeader("API Keys")
        OutlinedTextField(
            value = viewModel.apiKey,
            onValueChange = { viewModel.apiKey = it },
            label = { Text("Gemini API Key", color = MaxTextSecondary) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaxTextPrimary, unfocusedTextColor = MaxTextPrimary,
                focusedBorderColor = MaxOrange, unfocusedBorderColor = MaxBorder
            ),
            singleLine = true
        )
        Button(
            onClick = { viewModel.saveApiKey() },
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaxOrange)
        ) { Text("Save") }

        Spacer(Modifier.height(24.dp))

        // Voice Section
        SectionHeader("Voice")
        ToggleRow("Wake Word", viewModel.wakeWordEnabled) { viewModel.wakeWordEnabled = it }
        ToggleRow("Voice Input", viewModel.voiceEnabled) { viewModel.voiceEnabled = it }

        Spacer(Modifier.height(24.dp))

        // Overlay Section
        SectionHeader("Overlay")
        ToggleRow("Floating Dock", viewModel.overlayEnabled) { viewModel.overlayEnabled = it }

        Spacer(Modifier.height(24.dp))

        // About
        SectionHeader("About")
        Text("Max AI v1.0.0", color = MaxTextSecondary, fontSize = 14.sp)
        Text("Voice-first Android AI assistant", color = MaxTextMuted, fontSize = 12.sp)
        Text("Built with Kotlin + Jetpack Compose", color = MaxTextMuted, fontSize = 12.sp)
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(title, color = MaxOrange, fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(bottom = 12.dp))
}

@Composable
fun ToggleRow(label: String, checked: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp)).background(MaxSurface)
            .clickable { onToggle(!checked) }.padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = MaxTextPrimary, fontSize = 14.sp)
        Switch(checked = checked, onCheckedChange = onToggle, colors = SwitchDefaults.colors(checkedThumbColor = MaxOrange, checkedTrackColor = MaxOrange.copy(alpha = 0.3f)))
    }
}
