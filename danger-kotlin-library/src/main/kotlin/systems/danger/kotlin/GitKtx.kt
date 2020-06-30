package systems.danger.kotlin

// extensions over [Git] object

/**
 * Changed lines in this PR
 */
val Git.changedLines: PullRequestChangedLines
    get() {
        // TODO replace with more generic way to process commands
        val utils = Utils()
        val commandRawOutput = utils.exec("git diff --numstat $headSha $baseSha")
        val additionDeletionPairs = commandRawOutput.lines()
            .filter { it.isNotEmpty() }
            .map { line ->
                val parts = line.split("\\s+".toRegex())
                parts[0].toInt() to parts[1].toInt()
            }
        val additions = additionDeletionPairs.fold(0) { acc, (addition, _) -> acc + addition }
        val deletions = additionDeletionPairs.fold(0) { acc, (_, deletion) -> acc + deletion }
        return PullRequestChangedLines(additions, deletions)
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
    get() = commits.first().sha

/**
 * Reference to a SHA of base commit of this PR
 */
val Git.baseSha: String?
    get() = "${commits.last().sha}^1"

/**
 * Wrapper for number of additions and deletions in currently processed Pull (or Merge) Request
 */
data class PullRequestChangedLines(
    val additions: Int,
    val deletions: Int
)