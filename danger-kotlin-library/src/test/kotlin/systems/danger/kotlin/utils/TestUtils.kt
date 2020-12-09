package systems.danger.kotlin.utils

import kotlinx.serialization.json.Json

object TestUtils {

    val Json: Json by lazy {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    object JSONFiles {
        val githubDangerJSON by lazy {
            loadJSON("githubDangerJSON.json")
        }

        val githubWithSomeNullsAttributeDangerJSON by lazy {
            loadJSON("githubWithSomeNullsAttributeDangerJSON.json")
        }

        val githubWithClosedMilestoneDangerJSON by lazy {
            loadJSON("githubWithClosedMilestoneDangerJSON.json")
        }

        val dangerBitBucketServerJSON by lazy {
            loadJSON("bitbucketServerDangerJSON.json")
        }

        val gitlabJSON by lazy {
            loadJSON("gitlabDangerJSON.json")
        }

        private fun loadJSON(named: String): String {
            return this.javaClass.classLoader.getResource(named).readText()
        }
    }
}