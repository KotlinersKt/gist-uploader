package com.kotlinerskt.cli.giskt.network

import com.kotlinerskt.cli.giskt.github.GistId
import com.kotlinerskt.cli.giskt.github.GistRequest
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

data class GithubCredentials(val rawToken: String) {
    val bearerToken: String
        get() = "Bearer $rawToken"
}

@KtorExperimentalAPI
class GithubClient(private val credentials: GithubCredentials) {

    private val client = HttpClient(CIO) {
        Json {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    isLenient = false
                    ignoreUnknownKeys = false
                    allowSpecialFloatingPointValues = true
                    useArrayPolymorphism = false
                    prettyPrint = true
                }
            )
            acceptContentTypes = listOf(
                ContentType.parse("application/vnd.any+json"),
                ContentType.parse("application/json"),
            )
        }
        Logging {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    suspend fun createGiskt(gisktRequest: GistRequest): HttpResponse? = client.safeRequest {
        post(baseUrl) {
            headers {
                append("Accept", "application/vnd.github.v3+json")
                append("Content-Type", "application/json")
                append("Authorization", credentials.bearerToken)
            }
            body = gisktRequest
        }
    }

    suspend fun listGiskts(): HttpResponse? = client.safeRequest {
        get(baseUrl) {
            headers {
                append("Accept", "application/vnd.github.v3+json")
                append("Authorization", credentials.bearerToken)
            }
        }
    }

    suspend fun deleteGistkt(gistId: GistId): HttpResponse? = client.safeRequest {
        delete("$baseUrl/${gistId.id}") {
            headers {
                append("Authorization", credentials.bearerToken)
            }
        }
    }

    private suspend fun HttpClient.safeRequest(request: suspend HttpClient.() -> HttpResponse): HttpResponse? {
        return try {
            request()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {
        const val baseUrl = "https://api.github.com/gists"
        operator fun invoke(token: String) = GithubClient(
            GithubCredentials(token)
        )
    }
}
