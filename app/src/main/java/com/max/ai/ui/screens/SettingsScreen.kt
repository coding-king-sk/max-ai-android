package com.max.ai.ui.screens
import androidx.compose.foundation.background; import androidx.compose.foundation.clickable; import androidx.compose.foundation.layout.*; import androidx.compose.foundation.rememberScrollState; import androidx.compose.foundation.shape.RoundedCornerShape; import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons; import androidx.compose.material.icons.filled.*; import androidx.compose.material3.*; import androidx.compose.runtime.*; import androidx.compose.ui.Alignment; import androidx.compose.ui.Modifier; import androidx.compose.ui.draw.clip; import androidx.compose.ui.text.font.FontWeight; import androidx.compose.ui.unit.dp; import androidx.compose.ui.unit.sp
import com.max.ai.ui.theme.*; import androidx.lifecycle.ViewModel; import dagger.hilt.android.lifecycle.HiltViewModel; import javax.inject.Inject; import androidx.hilt.navigation.compose.hiltViewModel
@HiltViewModel class SettingsVM @Inject constructor() : ViewModel() { var key by mutableStateOf(""); var wake by mutableStateOf(true) }
@Composable fun SettingsScreen(back: () -> Unit, vm: SettingsVM = hiltViewModel()) {
    Column(Modifier.fillMaxSize().background(Bg).verticalScroll(rememberScrollState()).padding(16.dp)) {
        Row(Alignment.CenterVertically) { IconButton(back) { Icon(Icons.Default.ArrowBack, "Back", Txt) }; Text("Settings", Txt, fontSize=22.sp, fontWeight=FontWeight.Bold) }
        Spacer(Modifier.height(24.dp)); Text("API Keys", Orange, fontSize=14.sp, fontWeight=FontWeight.SemiBold)
        OutlinedTextField(vm.key, {vm.key=it}, label={Text("Gemini API Key", Txt2)}, modifier=Modifier.fillMaxWidth(), colors=OutlinedTextFieldDefaults.colors(focusedTextColor=Txt,unfocusedTextColor=Txt,focusedBorderColor=Orange,unfocusedBorderColor=Bdr), singleLine=true)
        Button({}, Modifier.padding(top=8.dp), colors=ButtonDefaults.buttonColors(containerColor=Orange)) { Text("Save") }
        Spacer(Modifier.height(24.dp)); Text("Voice", Orange, fontSize=14.sp, fontWeight=FontWeight.SemiBold)
        Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(Sfc).clickable{vm.wake=!vm.wake}.padding(12.dp), Arrangement.SpaceBetween, Alignment.CenterVertically) { Text("Wake Word", Txt, fontSize=14.sp); Switch(vm.wake, {vm.wake=it}, colors=SwitchDefaults.colors(checkedThumbColor=Orange,checkedTrackColor=Orange.copy(alpha=0.3f))) }
        Spacer(Modifier.height(24.dp)); Text("About", Orange, fontSize=14.sp, fontWeight=FontWeight.SemiBold); Text("Max AI v1.0.0", Txt2, fontSize=14.sp); Text("Kotlin + Jetpack Compose", Txt3, fontSize=12.sp)
    }
}