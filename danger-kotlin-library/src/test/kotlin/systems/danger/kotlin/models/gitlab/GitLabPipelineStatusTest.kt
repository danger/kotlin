package systems.danger.kotlin.models.gitlab

import kotlinx.serialization.decodeFromString
import org.junit.Assert.assertEquals
import org.junit.Test
import systems.danger.kotlin.models.danger.DSL
import systems.danger.kotlin.utils.TestUtils
import systems.danger.kotlin.utils.TestUtils.JSONFiles

class GitLabPipelineStatusTest {

    @Test
    fun testItParsesGitLabCancelledPipeline() {
        val dsl: DSL = TestUtils.Json.decodeFromString(JSONFiles.gitlabWithCancelledPipelineJSON)
        val gitLab = dsl.danger.gitlab
        assertEquals(GitLabPipelineStatus.CANCELLED, gitLab.mergeRequest.pipeline.status)
    }
}