// Dangerfile.df.kts
/*
 * Use external dependencies using the following annotations:
 */
@file:Repository("https://repo.maven.apache.org")
@file:DependsOn("org.apache.commons:commons-text:1.6")

import org.apache.commons.text.WordUtils
import systems.danger.kotlin.*

// register plugin MyDangerPlugin

danger(args) {

    val allSourceFiles = git.modifiedFiles + git.createdFiles
    val changelogChanged = allSourceFiles.contains("CHANGELOG.md")
    val sourceChanges = allSourceFiles.firstOrNull { it.contains("src") }

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

    //No Java files check
    git.createdFiles.filter {
        it.endsWith(".java")
    }.forEach {
        // Using apache commons-text dependency to be sure the dependency resolution always works
        warn(WordUtils.capitalize("please consider to create new files in Kotlin"), it, 1)
    }
}
