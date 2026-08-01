package com.max.ai.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme

val MaxOrange = Color(0xFFFF6600)
val MaxCyan = Color(0xFF00E5FF)
val MaxGreen = Color(0xFF10B981)
val MaxError = Color(0xFFFF4444)

val MaxBackground = Color(0xFF0A0A0F)
val MaxSurface = Color(0xFF12121A)
val MaxSurfaceElevated = Color(0xFF1E1E2E)
val MaxBorder = Color(0xFF2A2A3A)

val MaxTextPrimary = Color(0xFFF5F5F7)
val MaxTextSecondary = Color(0xFFA0A0B0)
val MaxTextMuted = Color(0xFF606078)

private val MaxDarkColorScheme = darkColorScheme(
    primary = MaxOrange,
    secondary = MaxCyan,
    tertiary = MaxGreen,
    background = MaxBackground,
    surface = MaxSurface,
    surfaceVariant = MaxSurfaceElevated,
    error = MaxError,
    onPrimary = MaxTextPrimary,
    onSecondary = MaxTextPrimary,
    onBackground = MaxTextPrimary,
    onSurface = MaxTextPrimary,
    onSurfaceVariant = MaxTextSecondary,
    outline = MaxBorder
)

@Composable
fun MaxTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaxDarkColorScheme,
        content = content
    )
}
