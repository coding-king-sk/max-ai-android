package com.max.ai.tools.system

import com.max.ai.core.agent.ToolRegistry
import com.max.ai.tools.communication.*
import com.max.ai.tools.media.*
import com.max.ai.tools.information.*
import com.max.ai.tools.productivity.*
import com.max.ai.tools.memory.*
import com.max.ai.tools.automation.*
import com.max.ai.tools.device.*

fun ToolRegistry.registerAllTools(
    whatsAppTool: WhatsAppTool,
    callTool: CallTool,
    smsTool: SmsTool,
    gmailTool: GmailTool,
    flashlightTool: FlashlightTool,
    appLauncherTool: AppLauncherTool,
    deviceSettingsTool: DeviceSettingsTool,
    spotifyTool: SpotifyTool,
    youTubeTool: YouTubeTool,
    wallpaperTool: WallpaperTool,
    webSearchTool: WebSearchTool,
    weatherTool: WeatherTool,
    stockTool: StockTool,
    deepResearchTool: DeepResearchTool,
    locationTool: LocationTool,
    calendarTool: CalendarTool,
    alarmTool: AlarmTool,
    notesTool: NotesTool,
    rememberTool: RememberTool,
    recallTool: RecallTool,
    protocolTool: ProtocolTool,
    screenVisionTool: ScreenVisionTool,
    cameraVisionTool: CameraVisionTool,
    biometricTool: BiometricTool,
    imageGenTool: ImageGenTool,
    selfDestructTool: SelfDestructTool,
    widgetForgeTool: WidgetForgeTool
) {
    registerAll(
        // Communication
        whatsAppTool, callTool, smsTool, gmailTool,
        // System
        flashlightTool, appLauncherTool, deviceSettingsTool,
        // Media
        spotifyTool, youTubeTool, wallpaperTool,
        // Information
        webSearchTool, weatherTool, stockTool, deepResearchTool, locationTool,
        // Productivity
        calendarTool, alarmTool, notesTool,
        // Memory
        rememberTool, recallTool,
        // Automation
        protocolTool,
        // Device
        screenVisionTool, cameraVisionTool, biometricTool, imageGenTool, selfDestructTool, widgetForgeTool
    )
}
