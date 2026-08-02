package com.max.ai.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.max.ai.ui.theme.Bdr
import com.max.ai.ui.theme.Bg
import com.max.ai.ui.theme.Orange
import com.max.ai.ui.theme.Sfc
import com.max.ai.ui.theme.Txt
import com.max.ai.ui.theme.Txt2
import com.max.ai.ui.theme.Txt3
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor() : ViewModel() {
    var apiKey by mutableStateOf("")
    var wakeWordEnabled by mutableStateOf(true)
    fun save() {}
}

@Composable
fun SettingsScreen(onBack: () -> Unit, viewModel: SettingsVM = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Txt
                )
            }
            Text(
                text = "Settings",
                color = Txt,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "API Keys",
            color = Orange,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        OutlinedTextField(
            value = viewModel.apiKey,
            onValueChange = { viewModel.apiKey = it },
            label = { Text(text = "Gemini API Key", color = Txt2) },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Txt,
                unfocusedTextColor = Txt,
                focusedBorderColor = Orange,
                unfocusedBorderColor = Bdr
            ),
            singleLine = true
        )

        Button(
            onClick = { viewModel.save() },
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Orange)
        ) {
            Text(text = "Save")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Voice",
            color = Orange,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Sfc)
                .clickable { viewModel.wakeWordEnabled = !viewModel.wakeWordEnabled }
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Wake Word (Hey Max)", color = Txt, fontSize = 14.sp)
            Switch(
                checked = viewModel.wakeWordEnabled,
                onCheckedChange = { viewModel.wakeWordEnabled = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Orange,
                    checkedTrackColor = Orange.copy(alpha = 0.35f)
                )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "About",
            color = Orange,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(text = "Max AI v1.0.0", color = Txt2, fontSize = 14.sp)
        Text(text = "Kotlin + Jetpack Compose", color = Txt3, fontSize = 12.sp)
    }
}
