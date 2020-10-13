package com.kotlinerskt.cli.giskt

import java.io.File

fun GisktFile.uploadFile(isPublic: Boolean) {
    val files = mapOf<String, Map<String, String>>(
            name to mapOf(
                    "content" to content))
    val gist = mapOf<String, Any>("public" to isPublic, "files" to files,
            "description" to "El mejor gist del mundo")
    println("Gist to upload $gist")

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