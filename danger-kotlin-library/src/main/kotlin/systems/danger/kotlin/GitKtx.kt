package systems.danger.kotlin

// extensions over [Git] object

/**
 * Changed lines in this MR
 */
val Git.changedLines: PullRequestChangedLines
    get() {
        // TODO replace with more generic way to process commands
        val utils = Utils()
        val additionDeletionPairs = commits.mapNotNull { it.sha }
            .map { sha ->
                computeChangedLinesForCommit(utils, sha)
            }.flatten()

        val additions = additionDeletionPairs.fold(0) { acc, (addition, _) -> acc + addition }
        val deletions = additionDeletionPairs.fold(0) { acc, (_, deletion) -> acc + deletion }
        return PullRequestChangedLines(additions, deletions)
    }

private fun computeChangedLinesForCommit(
    utils: Utils,
    sha: String
): List<Pair<Int, Int>> {
    val commandRawOutput = utils.exec("git diff --numstat $sha $sha^1")
    return commandRawOutput.lines()
        .filter { it.isNotEmpty() }
        .map { line ->
            val parts = line.split("\\s+".toRegex())
            parts[0].toInt() to parts[1].toInt()
        }
}

/**
 * Number of changed lines
 */
val Git.linesOfCode: Int
    get() = additions + deletions

/**
 * Number of added lines
 */
val Git.additions: Int
    get() = changedLines.additions

/**
 * Number of deleted lines
 */
val Git.deletions: Int
    get() = changedLines.deletions

/**
 * Wrapper for number of additions and deletions in currently processed Pull (or Merge) Request
 */
data class PullRequestChangedLines(
    val additions: Int,
    val deletions: Int
)