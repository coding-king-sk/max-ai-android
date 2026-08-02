package com.max.ai.ui
import android.content.Intent; import android.os.Bundle; import androidx.activity.ComponentActivity; import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize; import androidx.compose.material3.Surface; import androidx.compose.ui.Modifier
import com.max.ai.ui.navigation.MaxNavGraph; import com.max.ai.ui.theme.Bg; import com.max.ai.ui.theme.MaxTheme; import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint class MainActivity:ComponentActivity(){override fun onCreate(s:Bundle?){super.onCreate(s);setContent{MaxTheme{Surface(Modifier.fillMaxSize(),Bg){MaxNavGraph{startService(Intent(this@MainActivity,com.max.ai.services.MaxForegroundService::class.java))}}}}}}