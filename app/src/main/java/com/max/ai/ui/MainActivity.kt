package com.max.ai.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.max.ai.ui.navigation.MaxNavGraph
import com.max.ai.ui.theme.Bg
import com.max.ai.ui.theme.MaxTheme
import com.max.ai.services.MaxForegroundService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Bg
                ) {
                    MaxNavGraph(
                        onStartService = {
                            startService(Intent(this@MainActivity, MaxForegroundService::class.java))
                        }
                    )
                }
            }
        }
    }
}
