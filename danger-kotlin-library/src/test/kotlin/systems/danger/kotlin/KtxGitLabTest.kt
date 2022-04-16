package systems.danger.kotlin

import kotlinx.serialization.decodeFromString
import org.junit.Assert.assertEquals
import org.junit.Test
import systems.danger.kotlin.models.danger.DSL
import systems.danger.kotlin.models.gitlab.GitLab
import systems.danger.kotlin.utils.TestUtils

/**
 * Tests for [GitLab] extensions
 */
internal class GitLabKtxTest {

    companion object {
        private const val filePath = "static/source/swift/guides/getting_started.html.slim"
        private const val projectUrl = "https://gitlab.com/danger-systems/danger.systems"
        private const val diffUrl = "$projectUrl/merge_requests/182/diffs#diff-content-6b0f4e17f37f5ea51f169e6e1eaa04a0151b17d1"
        private const val blobUrl = "$projectUrl/blob/621bc3348549e51c5bd6ea9f094913e9e4667c7b/$filePath"
    }

    private val dsl: DSL
        get() = TestUtils.Json.decodeFromString(TestUtils.JSONFiles.gitlabJSON)

    private val gitLab: GitLab
        get() = dsl.danger.gitlab

    @Test
    fun testProjectUrlAreCorrect() = assertEquals(projectUrl, gitLab.projectUrl)

    @Test
    fun testWebUrlAreCorrect() = assertEquals(blobUrl, gitLab.toWebUrl(filePath))

    @Test
    fun testWebDiffUrlWithAnchorAreCorrect() = assertEquals(diffUrl, gitLab.toWebDiffUrl(filePath))
}
