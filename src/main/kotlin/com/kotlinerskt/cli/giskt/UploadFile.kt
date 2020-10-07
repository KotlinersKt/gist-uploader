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

fun createGisktFile(gistFileName: File): GisktFile {
    val fileName = gistFileName.name
    val content = gistFileName.readLines().joinToString("\n")
    return GisktFile(fileName, content)
}

data class GisktFile(val name: String, val content: String)