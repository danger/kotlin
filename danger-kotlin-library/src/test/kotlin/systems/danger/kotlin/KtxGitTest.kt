package systems.danger.kotlin

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import systems.danger.kotlin.models.git.Git
import systems.danger.kotlin.models.git.GitCommit
import systems.danger.kotlin.models.git.GitCommitAuthor
import systems.danger.kotlin.tools.shell.ShellExecutorFactory

/**
 * Tests for [Git] extensions
 */
internal class GitKtxTest {

    companion object {
        private val diffCommandOutput = """
        0       1       features/search/build.gradle
        3       10      features/search/src/main/java/com/sampleapp/search/di/RepositoryModule.kt
        2       4       features/search/src/main/java/com/sampleapp/search/model/ApiInteractor.kt
        2       4       features/search/src/main/java/com/sampleapp/search/model/RecommendedPublishersRepository.kt
        1       3       features/search/src/main/java/com/sampleapp/search/model/RecommendedTopicsRepository.kt
        """.trimIndent()
    }

    private val basicGit = Git(
        modifiedFiles = emptyList(),
        createdFiles = emptyList(),
        deletedFiles = emptyList(),
        commits = listOf(
            GitCommit(
                sha = "commit1",
                author = GitCommitAuthor("John Doe", "john@doe.com", "2024-11-28T13:41:53Z"),
                committer = GitCommitAuthor("John Doe", "john@doe.com", "2024-12-04T09:15:23Z"),
                message = "Random message",
                parents = listOf(),
                url = ""
            ),
            GitCommit(
                sha = "commit2",
                author = GitCommitAuthor("John Doe", "john@doe.com", "2024-11-28T13:54:45Z"),
                committer = GitCommitAuthor("John Doe", "john@doe.com", "2024-12-04T09:15:23Z"),
                message = "Random message",
                parents = listOf(),
                url = ""
            )
        )
    )

    private val expectedResult = PullRequestChangedLines(8, 22, diffCommandOutput)

    @Before
    fun setup() {
        ShellExecutorFactory.set(mockk {
            every { execute(any(), any()) } returns diffCommandOutput
        })
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

    @Test
    fun testBaseShaIsCorrect() {
        assertEquals("commit1^1", basicGit.baseSha)
    }

    @Test
    fun testHeadShaIsCorrect() {
        assertEquals("commit2", basicGit.headSha)
    }
}