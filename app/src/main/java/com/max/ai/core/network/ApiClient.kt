package com.max.ai.core.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Singleton
class ApiClient {
    private val json = Json { ignoreUnknownKeys = true; isLenient = true }
    private val client = HttpClient(Android) {
        install(ContentNegotiation) { json(json) }
        engine { connectTimeout = 30000; socketTimeout = 30000 }
    }
    suspend fun sendToGemini(text: String, key: String = ""): String = "API response"
    fun close() { client.close() }
}
