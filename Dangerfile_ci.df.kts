// Dangerfile.df.kts
/*
 * Use external dependencies using the following annotations:
 */
@file:Repository("https://repo.maven.apache.org")
@file:DependsOn("org.apache.commons:commons-text:1.6")

//Testing plugin
@file:DependsOn("danger-kotlin-sample-plugin-sample.jar")

import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import kotlinx.datetime.toDateTimePeriod
import org.apache.commons.text.WordUtils
import systems.danger.kotlin.*
import systems.danger.kotlin.models.danger.DangerDSL
import systems.danger.samples.plugin.SamplePlugin
import kotlin.time.Duration

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
    val current = Clock.System.now()
    runBlocking {
        expensiveCheck(1000)
        expensiveCheck(3000)
        expensiveCheck(2000)
        expensiveCheck(5000)
    }
    val after = Clock.System.now()
    val runningTime = after.minus(current)
    message("Coroutines checks terminated in ${runningTime.toDateTimePeriod().seconds} seconds.")
}

suspend fun DangerDSL.expensiveCheck(runForMillis: Long) {
    // Example expensive check
    Thread.sleep(runForMillis)
}
