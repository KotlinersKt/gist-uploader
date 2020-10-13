package com.kotlinerskt.cli.giskt.github

import kotlinx.serialization.Serializable

typealias GistFiles = Map<String, GistContent>

@Serializable
data class GistRequest(
    val public: Boolean,
    val files: GistFiles,
    val description: String? = null,
)

@Serializable
data class GistContent(val content: String)