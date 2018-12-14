fun runDangerJS(command: String, args: List<String>) {
    val argsString = args.joinToString(" ")
    val dangerJSCall = "danger-$command --process danger-kotlin $argsString"

    println("Executing " + dangerJSCall)

    dangerJSCall.exec()
}