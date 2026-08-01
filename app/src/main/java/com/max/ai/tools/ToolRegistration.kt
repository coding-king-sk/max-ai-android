package com.max.ai.tools

import com.max.ai.core.agent.ToolRegistry
import com.max.ai.tools.communication.*
import com.max.ai.tools.system.*
import com.max.ai.tools.media.*
import com.max.ai.tools.information.*
import com.max.ai.tools.productivity.*
import com.max.ai.tools.memory.*
import com.max.ai.tools.automation.*
import com.max.ai.tools.device.*

fun ToolRegistry.registerAllTools(
    t1: WhatsAppTool, t2: CallTool, t3: SmsTool, t4: GmailTool,
    t5: FlashlightTool, t6: AppLauncherTool, t7: DeviceSettingsTool,
    t8: SpotifyTool, t9: YouTubeTool, t10: WallpaperTool,
    t11: WebSearchTool, t12: WeatherTool, t13: StockTool, t14: DeepResearchTool, t15: LocationTool,
    t16: CalendarTool, t17: AlarmTool, t18: NotesTool,
    t19: RememberTool, t20: RecallTool,
    t21: ProtocolTool,
    t22: ScreenVisionTool, t23: CameraVisionTool, t24: BiometricTool, t25: ImageGenTool, t26: SelfDestructTool, t27: WidgetForgeTool
) { registerAll(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,t23,t24,t25,t26,t27) }
