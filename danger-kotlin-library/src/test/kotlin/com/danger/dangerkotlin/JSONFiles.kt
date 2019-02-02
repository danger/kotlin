package com.danger.dangerkotlin

class JSONFiles {
    val githubDangerJSON
        get() = loadJSON("githubDangerJSON.json")
    val dangerBitBucketServerJSON
        get() = loadJSON("bitbucketServerDangerDSL.json")

    private fun loadJSON(named: String): String {
        return this.javaClass.classLoader.getResource(named).readText()
    }
}
