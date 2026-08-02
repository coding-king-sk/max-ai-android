package com.max.ai.ui.theme
import androidx.compose.ui.graphics.Color; import androidx.compose.material3.*; import androidx.compose.runtime.Composable
val Orange=Color(0xFFFF6600);val Cyan=Color(0xFF00E5FF);val Green=Color(0xFF10B981);val Err=Color(0xFFFF4444)
val Bg=Color(0xFF0A0A0F);val Sfc=Color(0xFF12121A);val Sfc2=Color(0xFF1E1E2E);val Bdr=Color(0xFF2A2A3A)
val Txt=Color(0xFFF5F5F7);val Txt2=Color(0xFFA0A0B0);val Txt3=Color(0xFF606078)
private val Dark=darkColorScheme(primary=Orange,secondary=Cyan,tertiary=Green,background=Bg,surface=Sfc,surfaceVariant=Sfc2,error=Err,onPrimary=Txt,onSecondary=Txt,onBackground=Txt,onSurface=Txt,onSurfaceVariant=Txt2,outline=Bdr)
@Composable fun MaxTheme(c:@Composable ()->Unit){MaterialTheme(colorScheme=Dark,content=c)}