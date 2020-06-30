package systems.danger.kotlin

import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import systems.danger.kotlin.shell.ShellExecutor
import systems.danger.kotlin.shell.ShellExecutorFactory

/**
 * Tests for [Git] extensions
 */
internal class GitKtxTest {

    class MockShellExecutor : ShellExecutor {

        private val diffCommandOutput = """
        0       1       features/search/build.gradle
        3       10      features/search/src/main/java/com/sampleapp/search/di/RepositoryModule.kt
        2       4       features/search/src/main/java/com/sampleapp/search/model/ApiInteractor.kt
        2       4       features/search/src/main/java/com/sampleapp/search/model/RecommendedPublishersRepository.kt
        1       3       features/search/src/main/java/com/sampleapp/search/model/RecommendedTopicsRepository.kt
    """.trimIndent()

        override fun execute(command: String, arguments: List<String>): String {
            return diffCommandOutput
        }
    }

    private val basicGit = Git(
        modifiedFiles = emptyArray(),
        createdFiles = emptyArray(),
        deletedFiles = emptyArray(),
        commits = listOf(
            GitCommit(
                sha = "commit1",
                author = GitCommitAuthor("John Doe", "john@doe.com", "now"),
                committer = GitCommitAuthor("John Doe", "john@doe.com", "now"),
                message = "Random message",
                parents = null,
                url = ""
            )
        )
    )

    private val expectedResult = PullRequestChangedLines(22, 8)

    @Before
    fun setup() {
        ShellExecutorFactory.set(MockShellExecutor())
    }

    @Test
    fun testItCorrectlyParseDiff() {
        assertEquals(expectedResult, basicGit.changedLines)
    }

    @Test
    fun testAdditionsAreCorrect() {
        assertEquals(expectedResult.additions, basicGit.additions)
    }

    @Test
    fun testDeletionsAreCorrect() {
        assertEquals(expectedResult.deletions, basicGit.deletions)
    }

    @Test
    fun testLinesOfCodeAreCorrect() {
        assertEquals(expectedResult.deletions + expectedResult.additions, basicGit.linesOfCode)
    }

    @Test
    fun testNoChangedLinesForNoCommits() {
        val gitWOCommits = basicGit.copy(commits = emptyList())
        assertEquals(PullRequestChangedLines(0, 0), gitWOCommits.changedLines)
    }
}