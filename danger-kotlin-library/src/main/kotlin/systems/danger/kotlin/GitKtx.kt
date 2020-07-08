package systems.danger.kotlin

// extensions over [Git] object

/**
 * Changed lines in this PR
 */
val Git.changedLines: PullRequestChangedLines
    get() {
        return if (commits.isNotEmpty()) {
            val commandRawOutput = UtilsImpl.exec("git diff --numstat $headSha $baseSha")
            val additionDeletionPairs = commandRawOutput.lines()
                .filter { it.isNotEmpty() }
                .map { line ->
                    val parts = line.split("\\s+".toRegex())
                    (parts[0].toIntOrNull() ?: 0) to (parts[1].toIntOrNull() ?: 0)
                }
            val additions = additionDeletionPairs.fold(0) { acc, (_, addition) -> acc + addition }
            val deletions = additionDeletionPairs.fold(0) { acc, (deletion, _) -> acc + deletion }
            PullRequestChangedLines(additions, deletions)
        } else {
            PullRequestChangedLines(0, 0)
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
 * Reference to a SHA of head commit of this PR
 */
val Git.headSha: String?
    get() = commits.firstOrNull()?.sha

/**
 * Reference to a SHA of base commit of this PR
 */
val Git.baseSha: String?
    get() = commits.lastOrNull()?.sha?.let { "$it^1" }

/**
 * Wrapper for number of additions and deletions in currently processed Pull (or Merge) Request
 */
data class PullRequestChangedLines(
    val additions: Int,
    val deletions: Int
)