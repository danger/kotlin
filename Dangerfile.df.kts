// Dangerfile.df.kts
// Using external dependencies:
@file:Repository("https://repo.maven.apache.org")
//@file:DependsOn("systems.danger.exampleplugin:example:1.0")
@file:DependsOn("org.apache.commons:commons-text:1.6")

import systems.danger.kotlin.*
import org.jetbrains.kotlin.script.util.*
import org.apache.commons.text.WordUtils

val danger = Danger(args)

val allSourceFiles = danger.git.modifiedFiles + danger.git.createdFiles

val changelogChanged = allSourceFiles.contains("CHANGELOG.md")
val sourceChanges = allSourceFiles.firstOrNull { it.contains("src") }
val isTrivial = danger.github.pullRequest.title.contains("#trivial")

if (!isTrivial && !changelogChanged && sourceChanges != null) {
    warn("Any changes to library code should be reflected in the Changelog.\n\nPlease consider adding a note there and adhere to the [Changelog Guidelines](https://github.com/Moya/contributors/blob/master/Changelog%20Guidelines.md).")
}

if ((danger.github.pullRequest.additions ?: 0) - (danger.github.pullRequest.deletions ?: 0) > 300) {
    warn("Big PR, try to keep changes smaller if you can")
}

if (danger.github.pullRequest.title.contains("WIP", false)) {
    warn("PR is classed as Work in Progress")
}

danger.git.createdFiles.filter {
    it.endsWith(".java")
}.forEach {
    warn("Please consider to create new files in Kotlin", it, 1)
}

warn(WordUtils.capitalize("test"))
