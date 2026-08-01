<div align="center">

# Max AI 🧠

### Voice-First Android AI Assistant

**Speak naturally. Max executes it.**

A voice-first AI assistant for Android, inspired by IRIS AI. Powered by **Gemini 2.0 Flash Live API** with real-time WebRTC audio, on-device wake word detection, and 36 action tools.

---

[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-7F52FF?logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-BOM%202025.07-4285F4?logo=android)](https://developer.android.com/compose)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-29%20(Android%2010)-34A853?logo=android)](https://developer.android.com)
[![License](https://img.shields.io/badge/License-MIT-10b981)](LICENSE)

</div>

---

## ⚡ Overview

Max AI is not a chatbot. It is a **Voice-First Mobile AI Assistant** that listens to your spoken commands in real-time and executes real actions on your phone — sending WhatsApp messages, making calls, controlling settings, searching the web, analyzing your screen, and more.

> **Say "Hey Max, Papa ko WhatsApp kar ke bolo ghar pahunch gaya" → Done.**

---

## ✨ Core Features

| Category | Capabilities |
|---|---|
| 🎤 **Voice Pipeline** | Wake word ("Hey Max"), Gemini Live API WebRTC, real-time bidirectional audio |
| 💬 **Communication** | WhatsApp auto-send, Phone calls, SMS, Gmail read/draft/send |
| ⚙️ **System Control** | App launcher, Flashlight, WiFi, Bluetooth, Brightness, DND, Volume |
| 👁️ **Screen Vision** | MediaProjection capture + Gemini Vision OCR, Camera vision |
| 📅 **Productivity** | Calendar read/write, Alarms, Reminders, Notes |
| 🧠 **Memory** | Persistent memory (Room DB), Recall facts, Command history |
| 🌐 **Web & Info** | Web search (Tavily), Deep research agent, Weather, Stock prices |
| 🎵 **Media** | Spotify control, YouTube playback, AI wallpaper generation |
| 🗺️ **Location** | Live location, Maps, Directions, Route navigation |
| 🪄 **Overlay** | Floating glassmorphic dock, Quick-access mini panel |
| 🔐 **Security** | Biometric lock (fingerprint/face), PIN fallback, Android Keystore encryption |
| 🔄 **Automation** | Custom JSON protocols, Multi-step macro workflows |

---

## 🏗️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin 2.1 |
| UI | Jetpack Compose + Material 3 |
| Animations | Compose Animation + Lottie + Canvas |
| Wake Word | Porcupine (Picovoice) — on-device |
| Real-time Voice | Gemini Live API (WebRTC) |
| STT Fallback | Android SpeechRecognizer + whisper.cpp (offline) |
| LLM | Gemini 2.0 Flash (primary) + Groq (fallback) |
| Vision | Gemini Vision API + ML Kit (on-device OCR) |
| TTS | Gemini native audio + Android TTS |
| Web Search | Tavily API |
| Database | Room + DataStore |
| DI | Hilt |
| HTTP | Ktor Client |
| Image Loading | Coil 3 |

---

## 🌐 Dual-Mode (Online + Offline)

Max AI auto-detects connectivity and switches seamlessly:

| Mode | STT | LLM | Actions |
|---|---|---|---|
| 🌐 **Online** | Gemini Live API (WebRTC) | Gemini 2.0 Flash | All 36 tools |
| 📴 **Offline** | whisper.cpp (hi-IN) | Local intent matcher | Top 20 commands |

**Offline commands that work without internet:** calls, SMS, flashlight, WiFi/BT, alarm, notes, memory, app launch, settings.

---

## 🚀 Quick Start

### Prerequisites
- Android Studio Hedgehog+
- JDK 17
- Android SDK 29+
- Gemini API Key ([get one free](https://aistudio.google.com/apikey))

### Setup

```bash
git clone https://github.com/coding-king-sk/max-ai-android.git
cd max-ai-android
```

Open in Android Studio, sync Gradle, and run on device/emulator.

### API Keys

Enter your Gemini API key in **Settings → API Keys** within the app. Keys are stored encrypted in Android Keystore — never leave your device.

Required:
- `GEMINI_API_KEY`

Optional:
- `GROQ_API_KEY` (faster fallback LLM)
- `TAVILY_API_KEY` (web search)
- `SPOTIFY_CLIENT_ID` / `SPOTIFY_CLIENT_SECRET`

---

## 📦 Downloads

| Channel | Link |
|---|---|
| GitHub Releases | [Latest APK](https://github.com/coding-king-sk/max-ai-android/releases) |
| F-Droid | Coming soon |
| Google Play | Coming soon |

---

## 🗓️ Roadmap

- [x] Build plan finalized
- [ ] Week 1-3: Foundation + Voice Pipeline + Phase 1 Tools
- [ ] Week 4-6: Screen Vision + Media + Calendar + Web
- [ ] Week 7-8: Communication + Overlay + Protocols + Polish
- [ ] Week 9-10: Testing + Optimization + Release

---

## 📜 License

MIT License. Free & Open Source.

---

<div align="center">

**System Online. Max Activated.** 🧠

Made with ❤️ by [Vibe Coder](https://github.com/coding-king-sk)

</div>
