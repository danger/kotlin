package com.danger.dangerkt.runner

import java.io.File
import javax.script.ScriptEngineManager

val dangerFile = File("./resources/DangerFile.kts")

fun main(args: Array<String>) {

    with(ScriptEngineManager().getEngineByExtension("kts")) {
        eval(dangerFile.readText())
    }

}