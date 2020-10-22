package com.kotlinerskt.cli.giskt.github

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GistResponse(

		@SerialName("commits_url")
		val commitsUrl: String? = null,

		@SerialName("comments")
		val comments: Int? = null,

		@SerialName("forks_url")
		val forksUrl: String? = null,

		@SerialName("git_push_url")
		val gitPushUrl: String? = null,

		@SerialName("created_at")
		val createdAt: String? = null,

		@SerialName("description")
		val description: String? = null,

		@SerialName("url")
		val url: String? = null,

		@SerialName("updated_at")
		val updatedAt: String? = null,

		@SerialName("html_url")
		val htmlUrl: String? = null,

		@SerialName("git_pull_url")
		val gitPullUrl: String? = null,

		@SerialName("comments_url")
		val commentsUrl: String? = null,

		@SerialName("id")
		val id: String? = null,

		@SerialName("node_id")
		val nodeId: String? = null
)
