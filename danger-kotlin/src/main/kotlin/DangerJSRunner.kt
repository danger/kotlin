fun runDangerJS(command: String, args: List<String>) {
    val argsString = args.joinToString(" ")
    val dangerCommandPath = "which danger-$command".exec()
    val dangerJSCall = "$dangerCommandPath --process danger-kotlin $argsString"

    println("Executing " + dangerJSCall)

    dangerJSCall.exec()
}