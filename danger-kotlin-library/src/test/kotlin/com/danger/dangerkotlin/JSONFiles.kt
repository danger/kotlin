package com.danger.dangerkotlin

class JSONFiles {
    val dangerJSON
        get() = loadJSON("dangerDSL.json")
    val dangerBitBucketServerJSON
        get() = loadJSON("bitbucketServerDangerDSL.json")

    private fun loadJSON(named: String): String {
        return this.javaClass.classLoader.getResource(named).readText()
    }
}