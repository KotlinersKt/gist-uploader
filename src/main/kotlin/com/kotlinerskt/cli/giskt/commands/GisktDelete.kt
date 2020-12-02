package com.kotlinerskt.cli.giskt.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.kotlinerskt.cli.giskt.SecretToken
import com.kotlinerskt.cli.giskt.github.GistId
import com.kotlinerskt.cli.giskt.network.GithubClient
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class GisktDelete : CliktCommand(
    name = "delete",
    printHelpOnEmptyArgs = true,
) {
    private val gistId by option(
        "--id",
        help = """id del gist que quieres borrar (ejemplo: "ab21fd")""",
    ).required()

    override fun run() = runBlocking {
        val confirmation = confirm(
            text = "seguro que quieres borrar tu gist?",
        ) ?: return@runBlocking
        if (!confirmation) {
            echo(message = "sabia decision :clap:")
            return@runBlocking
        }

        val reconfirmation = confirm(
            text = "ultima oportunidad, estas apunto de borrar tu gist. seguro?",
        ) ?: return@runBlocking
        if (!reconfirmation) {
            echo(message = "sabiamos que tomarias la decision correcta")
            return@runBlocking
        }

        echo(message = "eliminando...")

        delay(2_000)

        val finalConfirmation = confirm(
            text = "aun no hemos terminado de eliminar tu gist, aun puedes arrepentirte, deseas continuar?",
        ) ?: return@runBlocking
        if (!finalConfirmation) {
            echo(message = "justo a tiempo")
            return@runBlocking
        }

        val response = GithubClient(SecretToken.TOKEN).deleteGistkt(GistId(gistId))
        if (response?.status == HttpStatusCode.NoContent) {
            echo(message = "gist eliminado")
        } else {
            echo(
                message = "por suerte algo fallo y no se pudo eliminar el gist",
                err = true,
            )
        }
    }
}
