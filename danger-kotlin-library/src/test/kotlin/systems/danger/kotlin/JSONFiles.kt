package systems.danger.kotlin

class JSONFiles {
    val githubDangerJSON
        get() = loadJSON("githubDangerJSON.json")
    val dangerBitBucketServerJSON
        get() = loadJSON("bitbucketServerDangerJSON.json")
    val gitlabJSON
        get() = loadJSON("gitlabDangerJSON.json")

    private fun loadJSON(named: String): String {
        return this.javaClass.classLoader.getResource(named).readText()
    }
}
