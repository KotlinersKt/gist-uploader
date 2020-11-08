package com.kotlinerskt.cli.giskt.commands

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option

class GisktDelete : CliktCommand(
    name = "delete",
    printHelpOnEmptyArgs = true,
) {
    val gistId by option(
        "--id",
        help = """id del gist que quieres borrar (ejemplo: "ab21fd")""",
    )

    override fun run() {
        TODO("Not yet implemented")
    }
}
