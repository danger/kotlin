package systems.danger.kotlin.models.bitbucket

import kotlinx.serialization.decodeFromString
import org.junit.Assert.assertEquals
import org.junit.Test
import systems.danger.kotlin.models.danger.DSL
import systems.danger.kotlin.utils.TestUtils.JSONFiles
import systems.danger.kotlin.utils.TestUtils

class BitBucketCloudParsingTests {

    private val dsl: DSL = TestUtils.Json.decodeFromString(JSONFiles.dangerBitBucketCloudJSON)

    private val bitBucketCloud: BitBucketCloud = dsl.danger.bitBucketCloud

    @Test
    fun testItParsesTheBitBucketPullRequest() {
        with(bitBucketCloud.pullRequest) {
            val expectedUser = BitBucketServerUser(
                null,
                "test",
                null,
                "user@email.com",
                true,
                null,
                BitBucketServerUser.Type.NORMAL
            )
            assertEquals(expectedUser, author.user)

            val expectedProject =
                BitBucketServerProject(1, "PROJ", "Project", false, "NORMAL")
            val expectedRepo = BitBucketServerRepo(
                "Repo",
                "repo",
                "git",
                false,
                true,
                expectedProject
            )
            val expectedHead = BitBucketServerMergeRef(
                "refs/heads/foo",
                "foo",
                "d6725486c38d46a33e76f622cf24b9a388c8d13d",
                expectedRepo
            )
            assertEquals(expectedHead, toRef)

            val expectedBase = BitBucketServerMergeRef(
                "refs/heads/master",
                "master",
                "8942a1f75e4c95df836f19ef681d20a87da2ee20",
                expectedRepo
            )
            assertEquals(expectedBase, fromRef)

            val expectedPartecipant = BitBucketServerUser(
                2,
                "danger",
                "DangerCI",
                "user@email.com",
                true,
                "danger",
                BitBucketServerUser.Type.NORMAL
            )
            assertEquals(1, participants.count())
            assertEquals(expectedPartecipant, participants[0].user)

            assertEquals(false, closed)
            assertEquals(1518863923273, createdAt)
            assertEquals(false, isLocked)
            assertEquals(true, open)
            assertEquals(BitBucketServerPR.State.OPEN, state)
            assertEquals("Pull request title", title)

            val expectedReviewerUser = BitBucketServerUser(
                2,
                "danger",
                "DangerCI",
                "foo@bar.com",
                true,
                "danger",
                BitBucketServerUser.Type.NORMAL
            )
            val expectedReviewer = BitBucketServerReviewer(
                expectedReviewerUser,
                true,
                "8942a1f75e4c95df836f19ef681d20a87da2ee20"
            )
            assertEquals(1, reviewers.count())
            assertEquals(expectedReviewer, reviewers[0])
        }
    }

    @Test
    fun testItParsesTheBitBucketCommits() {
        with(bitBucketCloud.commits) {
            val expectedUser = BitBucketServerUser(
                2,
                "danger",
                "DangerCI",
                "foo@bar.com",
                true,
                "danger",
                BitBucketServerUser.Type.NORMAL
            )
            val expectedParent = BitBucketServerCommitParent(
                "c62ada76533a2de045d4c6062988ba84df140729",
                "c62ada76533"
            )
            val expectedCommit = BitBucketServerCommit(
                "d6725486c38d46a33e76f622cf24b9a388c8d13d",
                "d6725486c38",
                expectedUser,
                1519442341000,
                expectedUser,
                1519442341000,
                "Modify and remove files",
                listOf(expectedParent)
            )
            assertEquals(expectedCommit, first())
            assertEquals(2, count())
        }
    }

    @Test
    fun testItParsesTheBitBucketComments() {
        with(bitBucketServer.comments) {
            val expectedUser = BitBucketServerUser(
                2,
                "danger",
                "DangerCI",
                "foo@bar.com",
                true,
                "danger",
                BitBucketServerUser.Type.NORMAL
            )
            val commentText = "test"
            val expectedProperty =
                BitBucketServerCommentInnerProperties(1, listOf())
            val expectedCommentDetail = BitBucketServerCommentDetail(
                10,
                23,
                commentText,
                expectedUser,
                1518939353345,
                1519449132488,
                listOf(),
                expectedProperty,
                listOf()
            )
            val expectedComment = BitBucketServerComment(
                52,
                1518939353345,
                expectedUser,
                "COMMENTED",
                null,
                null,
                null,
                null,
                "ADDED",
                expectedCommentDetail
            )

            assertEquals(expectedComment.toString(), this[1].toString())
            assertEquals(7, count())
        }
    }

    @Test
    fun testItParsesTheBitBucketMetadata() {
        with(bitBucketCloud.metadata) {
            assertEquals("artsy/emission", repoSlug)
            assertEquals("327", pullRequestID)
        }
    }

    @Test
    fun testItParsesTheBitBucketActivities() {
        with(bitBucketCloud.activities) {
            val expectedUser = BitBucketServerUser(
                1,
                "test",
                "test",
                "foo@bar.com",
                true,
                "test",
                BitBucketServerUser.Type.NORMAL
            )
            val expectedActivity = BitBucketServerActivity(
                61,
                1519442356495,
                expectedUser,
                "RESCOPED",
                null
            )

            assertEquals(expectedActivity, first())
            assertEquals(7, count())
        }
    }

    @Test
    fun testOnBitBucketIsTrue() {
        assertEquals(true, dsl.danger.onBitBucketServer)
    }

    @Test
    fun testOnGitHubIsFalse() {
        assertEquals(false, dsl.danger.onGitHub)
    }

    @Test
    fun testOnGitLabIsFalse() {
        assertEquals(false, dsl.danger.onGitLab)
    }
}
