package systems.danger.kotlin.models.gitlab

import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromString
import org.junit.Assert.*
import org.junit.Test
import systems.danger.kotlin.models.danger.DSL
import systems.danger.kotlin.utils.TestUtils
import systems.danger.kotlin.utils.TestUtils.JSONFiles

class GitLabMilestoneParsingTests {

    @Test
    fun testItParsesTheGitLabMilestoneWithNullDates() {
        val dslWithNullDates: DSL = TestUtils.Json.decodeFromString(JSONFiles.gitlabWithNullMilestoneDatesJSON)
        val gitLabWithNullDates: GitLab = dslWithNullDates.danger.gitlab

        with(gitLabWithNullDates.mergeRequest.milestone) {
            assertNotNull(this)
            assertEquals(1L, this!!.id)
            assertEquals(2L, this.iid)
            assertEquals(1000L, this.projectID)
            assertEquals("Test Milestone", this.title)
            assertEquals("Test Description", this.description)
            assertEquals(GitLabMilestoneState.CLOSED, this.state)
            assertNull(this.dueDate)
            assertNull(this.startDate)
            assertEquals(Instant.fromEpochMilliseconds(1554933465346), this.createdAt)
            assertEquals(Instant.fromEpochMilliseconds(1554933465346), this.updatedAt)
            assertEquals("https://gitlab.com/milestone", this.webUrl)
        }
    }
}
