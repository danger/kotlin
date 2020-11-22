package systems.danger.kotlin

import kotlinx.serialization.decodeFromString
import org.junit.Assert.*
import org.junit.Test
import systems.danger.kotlin.utils.TestUtils.JSONFiles
import systems.danger.kotlin.utils.TestUtils
import java.util.*

class GitLabParsingTests {
    private val dsl: DSL
        get() = TestUtils.Json.decodeFromString(JSONFiles.gitlabJSON)
    private val gitLab: GitLab
        get() = dsl.danger.gitlab

    @Test
    fun testItParsesTheGitLabMergeRequest() {
        with(gitLab.mergeRequest) {
            assertEquals(false, allowCollaboration)
            assertEquals(false, allowMaintainerToPush)
            assertEquals(1, approvalsBeforeMerge)
            val orta = GitLabUser(
                "https://secure.gravatar.com/avatar/f116cb3be23153ec08b94e8bd4dbcfeb?s=80&d=identicon", 377669, "Orta",
                GitLabUserState.ACTIVE, "orta", "https://gitlab.com/orta"
            )
            assertEquals(orta, assignee)
            val fmeloni = GitLabUser(
                "https://secure.gravatar.com/avatar/3d90e967de2beab6d44cfadbb4976b87?s=80&d=identicon",
                3331525,
                "Franco Meloni",
                GitLabUserState.ACTIVE,
                "f-meloni",
                "https://gitlab.com/f-meloni"
            )
            assertEquals(fmeloni, author)
            assertEquals(false, canMerge)
            assertEquals("1", changesCount)
            assertEquals(null, closedAt)
            assertEquals(null, closedBy)
            assertEquals("Updating it to avoid problems like https://github.com/danger/swift/issues/221", description)
            val expectedDiffRefs = GitLabDiffRefs(
                "ef28580bb2a00d985bffe4a4ce3fe09fdb12283f",
                "621bc3348549e51c5bd6ea9f094913e9e4667c7b",
                "ef28580bb2a00d985bffe4a4ce3fe09fdb12283f"
            )
            assertEquals(expectedDiffRefs, diffRefs)
            assertEquals(0, downvotes)
            assertEquals(Date(1554942622492), firstDeployedToProductionAt)
            assertEquals(true, forceRemoveSourceBranch)
            assertEquals(27469633, id)
            assertEquals(182, iid)
            assertEquals(Date(1554942802492), latestBuildFinishedAt)
            assertEquals(Date(1554942022492), latestBuildStartedAt)
            assertEquals(listOf<String>(), labels)
            assertEquals("377a24fb7a0f30364f089f7bca67752a8b61f477", mergeCommitSha)
            assertEquals(Date(1554943042492), mergedAt)
            assertEquals(orta, mergedBy)
            assertEquals(false, mergeOnPipelineSuccess)
            val expectedMilestone = GitLabMilestone(
                Date(1554933465346),
                "Test Description",
                Date(1560124800000),
                1,
                2,
                1000,
                Date(1554933465346),
                GitLabMilestoneState.CLOSED,
                "Test Milestone",
                Date(1554933465346),
                "https://gitlab.com/milestone"
            )
            assertEquals(expectedMilestone, milestone)
            val expectedPipeline = GitLabPipeline(
                50,
                "ef28580bb2a00d985bffe4a4ce3fe09fdb12283f",
                "621bc3348549e51c5bd6ea9f094913e9e4667c7b",
                GitLabPipelineStatus.SUCCESS,
                "https://gitlab.com/danger-systems/danger.systems/pipeline/621bc3348549e51c5bd6ea9f094913e9e4667c7b"
            )
            assertEquals(expectedPipeline, pipeline)
            assertEquals("1620437", projectId)
            assertEquals("621bc3348549e51c5bd6ea9f094913e9e4667c7b", sha)
            assertEquals(null, shouldRemoveSourceBranch)
            assertEquals("patch-2", sourceBranch)
            assertEquals("10132593", sourceProjectId)
            assertEquals(GitLabMergeRequestState.MERGED, state)
            assertEquals(false, subscribed)
            assertEquals("master", targetBranch)
            assertEquals("1620437", targetProjectId)
            assertEquals(null, timeStats)
            assertEquals("Update getting_started.html.slim", title)
            assertEquals(0, upvotes)
            assertEquals(0, userNotesCount)
            assertEquals("https://gitlab.com/danger-systems/danger.systems/merge_requests/182", webUrl)
            assertEquals(false, workInProgress)
        }
    }

    @Test
    fun testItParsesTheGitLabMetadata() {
        with(gitLab.metadata) {
            assertEquals("182", pullRequestID)
            assertEquals("danger-systems/danger.systems", repoSlug)
        }
    }

    @Test
    fun testOnGitHubIsFalse() {
        assertEquals(false, dsl.danger.onGitHub)
    }

    @Test
    fun testOnBitBucketIsFalse() {
        assertEquals(false, dsl.danger.onBitBucketServer)
    }

    @Test
    fun testOnGitLabIsTrue() {
        assertEquals(true, dsl.danger.onGitLab)
    }
}