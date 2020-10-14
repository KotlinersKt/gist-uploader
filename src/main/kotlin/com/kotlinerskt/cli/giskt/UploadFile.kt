package com.kotlinerskt.cli.giskt

import com.kotlinerskt.cli.giskt.github.GistContent
import com.kotlinerskt.cli.giskt.github.GistFiles
import com.kotlinerskt.cli.giskt.github.GistRequest
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun GistRequest.uploadFile() {
    println("Gist to upload $this")

    val json = Json.encodeToString(this)
    println("Json serialization $json")

    //TODO networkt call
}

fun GisktFile.createGistRequest(isPublic: Boolean, description: String): GistRequest {
    val files: GistFiles = mapOf<String, GistContent>(
        name to GistContent(content)
    )

    return GistRequest(
        public = isPublic,
        files = files,
        description = description
    )
}

fun List<GisktFile>.createGistRequest(isPublic: Boolean, description: String): GistRequest {
    val files: GistFiles = map { it.name to GistContent(it.content) }.toMap()

    return GistRequest(
        public = isPublic,
        files = files,
        description = description
    )
}

fun createGisktFile(gistFileName: File): GisktFile {
    val fileName = gistFileName.name
    val content = gistFileName.readLines().joinToString("\n")
    return GisktFile(fileName, content)
}

@Serializable
data class GisktFile(val name: String, val content: String)