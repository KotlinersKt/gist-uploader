package com.kotlinerskt.cli.giskt.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.kotlinerskt.cli.giskt.SecretToken
import com.kotlinerskt.cli.giskt.network.GithubClient
import io.ktor.client.statement.*
import io.ktor.util.*
import kotlinx.coroutines.runBlocking

class GisktList : CliktCommand(name = "list") {
    @KtorExperimentalAPI
    override fun run() {
        runBlocking {
            val githubClient = GithubClient(SecretToken.TOKEN)
            echo(
                    githubClient.listGiskts()?.readText()
            )
        }
    }
}