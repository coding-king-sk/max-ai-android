package com.max.ai.tools

import com.max.ai.core.agent.ToolRegistry

/**
 * Registers all 27 Max AI tools into the registry.
 * Call this once at app startup.
 */
fun ToolRegistry.registerAllTools() {
    registerAll(
        // Communication (4)
        WhatsAppTool(),
        CallTool(),
        SmsTool(),
        GmailTool(),
        // System (3)
        FlashlightTool(),
        AppLauncherTool(),
        DeviceSettingsTool(),
        // Media (3)
        SpotifyTool(),
        YouTubeTool(),
        WallpaperTool(),
        // Information (5)
        WebSearchTool(),
        WeatherTool(),
        StockTool(),
        DeepResearchTool(),
        LocationTool(),
        // Productivity (3)
        CalendarTool(),
        AlarmTool(),
        NotesTool(),
        // Memory (2)
        RememberTool(),
        RecallTool(),
        // Automation (1)
        ProtocolTool(),
        // Device (6)
        ScreenVisionTool(),
        CameraVisionTool(),
        BiometricTool(),
        ImageGenTool(),
        SelfDestructTool(),
        WidgetForgeTool()
    )
}
