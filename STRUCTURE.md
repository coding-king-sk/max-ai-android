# Max AI — Complete Android Project

Voice-first AI assistant | Kotlin + Jetpack Compose | 27 tools | 5 services | MIT

## File Inventory

### Build System
| File | Description |
|---|---|
| `settings.gradle.kts` | Kotlin 2.1, Compose, Hilt, KSP plugins |
| `build.gradle.kts` | Root Gradle config |
| `gradle.properties` | JVM & Android settings |
| `gradle/libs.versions.toml` | Version catalog (AGP 8.7, Kotlin 2.1, Compose BOM 2025.07) |
| `gradle/wrapper/gradle-wrapper.properties` | Gradle 8.11.1 |

### App Config
| File | Description |
|---|---|
| `app/build.gradle.kts` | SDK 29-35, Room, Ktor, Hilt, CameraX, ML Kit |
| `app/proguard-rules.pro` | Keep rules for core components |
| `app/src/main/AndroidManifest.xml` | 25+ permissions, 6 services, boot receiver |
| `app/src/main/res/values/` | strings.xml, themes.xml |
| `app/src/main/res/xml/` | network_security_config.xml, accessibility_service_config.xml |

### Source Code

#### Core (`com.max.ai`)
| File | Description |
|---|---|
| `MaxApplication.kt` | Hilt app with notification channels |

#### Agent (`core.agent`)
| File | Description |
|---|---|
| `Tool.kt` | Tool interface, ToolResult, ToolCall data classes |
| `ToolRegistry.kt` | Dynamic tool registration, execution, schema generation |
| `PromptBuilder.kt` | System prompt with memories, tool descriptions |
| `AgentCore.kt` | Main agent orchestration |

#### Voice (`core.voice`)
| File | Description |
|---|---|
| `VoiceModules.kt` | WakeWordEngine, GeminiLiveClient, SttEngine, TtsEngine |

#### Network (`core.network`)
| File | Description |
|---|---|
| `ApiClient.kt` | Ktor client for Gemini, Tavily, Weather APIs |
| `ConnectivityMonitor.kt` | Online/offline detection for dual-mode |

#### Screen (`core.screen`)
| File | Description |
|---|---|
| `ScreenReader.kt` | Accessibility screen text extraction |
| `ScreenCaptureService.kt` | MediaProjection service stub |

#### DI (`core.di`)
| File | Description |
|---|---|
| `AppModule.kt` | Hilt singleton providers |

#### Services (`services`)
| File | Description |
|---|---|
| `MaxForegroundService.kt` | Always-on wake word + voice pipeline |
| `MaxAccessibilityService.kt` | Screen reading, auto-click, auto-type |
| `MaxOverlayService.kt` | Floating glassmorphic dock |
| `MaxNotificationListener.kt` | Read phone notifications |
| `BootReceiver.kt` | Auto-start on boot |

#### Data Layer (`data`)
| File | Description |
|---|---|
| `local/db/AppDatabase.kt` | Room database |
| `local/dao/Daos.kt` | MemoryDao, NotesDao, CommandLogDao |
| `local/entity/Entities.kt` | MemoryEntity, NoteEntity, CommandLogEntity |
| `local/datastore/UserPreferences.kt` | Settings storage |
| `repository/MemoryRepository.kt` | Memory CRUD |
| `repository/NotesRepository.kt` | Notes CRUD |

#### Tools (`tools`) — 27 total

**Communication (4)**
| Tool | File |
|---|---|
| `send_whatsapp` | `communication/WhatsAppTool.kt` |
| `make_call` | `communication/CallTool.kt` |
| `send_sms` | `communication/SmsTool.kt` |
| `gmail` | `communication/GmailTool.kt` |

**System (3)**
| Tool | File |
|---|---|
| `flashlight` | `system/FlashlightTool.kt` |
| `open_app` | `system/AppLauncherTool.kt` |
| `device_setting` | `system/DeviceSettingsTool.kt` |

**Media (3)**
| Tool | File |
|---|---|
| `play_spotify` | `media/SpotifyTool.kt` |
| `play_youtube` | `media/YouTubeTool.kt` |
| `set_wallpaper` | `media/WallpaperTool.kt` |

**Information (5)**
| Tool | File |
|---|---|
| `web_search` | `information/WebSearchTool.kt` |
| `weather` | `information/WeatherTool.kt` |
| `stock_price` | `information/StockTool.kt` |
| `deep_research` | `information/DeepResearchTool.kt` |
| `location` | `information/LocationTool.kt` |

**Productivity (3)**
| Tool | File |
|---|---|
| `calendar` | `productivity/CalendarTool.kt` |
| `set_alarm` | `productivity/AlarmTool.kt` |
| `save_note` | `productivity/NotesTool.kt` |

**Memory (2)**
| Tool | File |
|---|---|
| `remember` | `memory/MemoryTools.kt` |
| `recall` | `memory/MemoryTools.kt` |

**Automation (1)**
| Tool | File |
|---|---|
| `execute_protocol` | `automation/ProtocolTool.kt` |

**Device (6)**
| Tool | File |
|---|---|
| `screen_vision` | `device/DeviceTools.kt` |
| `camera_vision` | `device/DeviceTools.kt` |
| `biometric_lock` | `device/MoreDeviceTools.kt` |
| `generate_image` | `device/MoreDeviceTools.kt` |
| `self_destruct` | `device/MoreDeviceTools.kt` |
| `forge_widget` | `device/MoreDeviceTools.kt` |

**Registration**
| File | Description |
|---|---|
| `tools/ToolRegistration.kt` | registerAllTools() — all 27 in one call |

#### UI (`ui`)
| File | Description |
|---|---|
| `theme/MaxTheme.kt` | Dark glassmorphic Material 3 theme (#0A0A0F, #FF6600, #00E5FF) |
| `navigation/MaxNavGraph.kt` | Compose navigation (Home, Settings, Notes) |
| `screens/HomeScreen.kt` | Neural Orb, chat bubbles, mic button |
| `screens/SettingsScreen.kt` | API keys, voice, overlay toggles |
| `screens/NotesScreen.kt` | Saved notes list |
| `MainActivity.kt` | Entry point |

## Stats

| Metric | Count |
|---|---|
| Total source files | 40+ |
| Tools | 27 |
| Services | 5 |
| Permissions | 25 |
| Monthly cost | ~$1-5 |

## Quick Start

```bash
git clone https://github.com/coding-king-sk/max-ai-android.git
# Open in Android Studio Hedgehog+ -> Sync Gradle -> Run
```

## Auto Release

```bash
git tag v1.0.0 && git push origin v1.0.0
# GitHub Actions auto-builds + creates release with APKs
```

---

**System Online. Max Activated.** 🧠
