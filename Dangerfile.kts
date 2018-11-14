#!/usr/bin/env kscript

//DEPS com.danger:danger-kotlin-library:1.0-SNAPSHOT

import com.danger.dangerkotlin.*

val danger = Danger(args)

val allSourceFiles = danger.git.modifiedFiles + danger.git.createdFiles

val changelogChanged = allSourceFiles.contains("CHANGELOG.md")
val sourceChanges = allSourceFiles.firstOrNull { it.contains("src")  }
val isTrivial = danger.github.pullRequest.title.contains("#trivial")

if (!isTrivial && !changelogChanged && sourceChanges != null) {
    warn("""
     Any changes to library code should be reflected in the Changelog.
     Please consider adding a note there and adhere to the [Changelog Guidelines](https://github.com/Moya/contributors/blob/master/Changelog%20Guidelines.md).
    """)
}

if (danger.git.createdFiles.size + danger.git.modifiedFiles.size - danger.git.deletedFiles.size > 10) {
    warn("Big PR, try to keep changes smaller if you can")
}

if (danger.github.pullRequest.title.contains("WIP" ,false)) {
    warn("PR is classed as Work in Progress")
}