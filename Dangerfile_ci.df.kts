// Dangerfile.df.kts
/*
 * Use external dependencies using the following annotations:
 */
@file:Repository("https://repo.maven.apache.org/maven2/")
@file:DependsOn("org.apache.commons:commons-text:1.6")

//Testing plugin
@file:DependsOn("danger-kotlin-sample-plugin-sample.jar")
@file:OptIn(kotlin.time.ExperimentalTime::class)

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import org.apache.commons.text.WordUtils
import systems.danger.kotlin.*
import systems.danger.kotlin.models.danger.DangerDSL
import systems.danger.samples.plugin.SamplePlugin

register plugin SamplePlugin

danger(args) {
    val allSourceFiles = git.modifiedFiles + git.createdFiles
    val changelogChanged = allSourceFiles.contains("CHANGELOG.md")
    val sourceChanges = allSourceFiles.firstOrNull { it.contains("src") }

    SamplePlugin.myCustomCheck()

    onGitHub {
        val isTrivial = pullRequest.title.contains("#trivial")

        // Changelog
        if (!isTrivial && !changelogChanged && sourceChanges != null) {
            warn(WordUtils.capitalize("any changes to library code should be reflected in the Changelog.\n\nPlease consider adding a note there and adhere to the [Changelog Guidelines](https://github.com/Moya/contributors/blob/master/Changelog%20Guidelines.md)."))
        }

        // Big PR Check
        if ((pullRequest.additions ?: 0) - (pullRequest.deletions ?: 0) > 300) {
            warn("Big PR, try to keep changes smaller if you can")
        }

        // Work in progress check
        if (pullRequest.title.contains("WIP", false)) {
            warn("PR is classed as Work in Progress")
        }
    }

    onGit {
        //No Java files check
        createdFiles.filter {
            it.endsWith(".java")
        }.forEach {
            // Using apache commons-text dependency to be sure the dependency resolution always works
            warn(WordUtils.capitalize("please consider to create new files in Kotlin"), it, 1)
        }
    }

    // Coroutines checks in parallel test
    val before = Clock.System.now()
    runBlocking {
        async { expensiveCheck("1", 1000) }
        async { expensiveCheck("2", 3000) }
        async { expensiveCheck("3", 2000) }
        async { expensiveCheck("4", 5000) }
    }
    val after = Clock.System.now()
    val runningTime = after.minus(before)
    message("Coroutines checks terminated - runningFor $runningTime")

    if ((fails + warnings).isEmpty()) {
        message(":rocket: No errors or warnings!")
    }
}

suspend fun DangerDSL.expensiveCheck(name: String, runForMillis: Long) {
    // Example expensive check
    delay(runForMillis)
    message("Coroutine $name terminated in $runForMillis ms")
}
