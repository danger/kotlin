package systems.danger.kotlin.models.bitbucket

import kotlinx.datetime.Instant
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
            val expectedUser = BitBucketCloud.User(
                uuid = "test",
                accountId = "test",
                displayName = "Foo Bar",
                nickname = "foo.bar"
            )
            assertEquals(expectedUser, author)

            val expectedDestination = BitBucketCloud.MergeRef(
                branch = BitBucketCloud.MergeRef.Branch(
                    name = "destination",
                ),
                commit = BitBucketCloud.MergeRef.Commit(
                    hash = "test"
                ),
                repository = BitBucketCloud.Repo(
                    uuid = "test",
                    name = "test",
                    fullName = "test"
                )
            )
            assertEquals(expectedDestination, destination)

            val expectedSource = BitBucketCloud.MergeRef(
                branch = BitBucketCloud.MergeRef.Branch(
                    name = "source",
                ),
                commit = BitBucketCloud.MergeRef.Commit(
                    hash = "test"
                ),
                repository = BitBucketCloud.Repo(
                    uuid = "test",
                    name = "test",
                    fullName = "test"
                )
            )
            assertEquals(expectedSource, source)

            val expectedParticipant = BitBucketCloud.PullRequest.Participant(
                approved = false,
                role = BitBucketCloud.PullRequest.Participant.Role.REVIEWER,
                user = BitBucketCloud.User(
                    uuid = "danger",
                    accountId = "danger",
                    displayName = "Danger",
                    nickname = "danger"
                )
            )
            assertEquals(1, participants.count())
            assertEquals(expectedParticipant, participants[0])

            assertEquals(Instant.parse("2022-04-07T15:19:56.209142+00:00"), createdOn)
            assertEquals(Instant.parse("2022-04-09T12:51:23.384574+00:00"), updatedOn)
            assertEquals(BitBucketCloud.PullRequest.State.OPEN, state)
            assertEquals("test", title)

            val expectedReviewer = BitBucketCloud.User(
                uuid = "danger",
                accountId = "danger",
                displayName = "Danger",
                nickname = "danger"
            )
            assertEquals(1, reviewers.count())
            assertEquals(expectedReviewer, reviewers.first())
        }
    }

    @Test
    fun testItParsesTheBitBucketCommits() {
        with(bitBucketCloud.commits) {
            val expectedCommit = BitBucketCloud.Commit(
                hash = "test",
                author = BitBucketCloud.Commit.Author(
                    raw = "test",
                    user = BitBucketCloud.User(
                        uuid = "test",
                        accountId = "test",
                        displayName = "Foo Bar",
                        nickname = "foo.bar"
                    )
                ),
                date = Instant.parse("2022-04-07T15:18:09+00:00"),
                message = "test"
            )
            assertEquals(1, count())
            assertEquals(expectedCommit, first())
        }
    }

    @Test
    fun testItParsesTheBitBucketComments() {
        with(bitBucketCloud.comments) {
            val expectedComment = BitBucketCloud.Comment(
                id = 1,
                content = BitBucketCloud.Content(
                    html = "test",
                    markup = "markdown",
                    raw = "test"
                ),
                createdOn = Instant.parse("2022-04-07T15:25:25.184212+00:00"),
                updatedOn = Instant.parse("2022-04-07T15:25:25.184261+00:00"),
                deleted = false,
                user = BitBucketCloud.User(
                    uuid = "danger",
                    accountId = "danger",
                    displayName = "Danger",
                    nickname = "danger"
                )
            )

            assertEquals(1, count())
            assertEquals(expectedComment, first())
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
            val expectedActivity = BitBucketCloud.Activity(
                comment = BitBucketCloud.Comment(
                    id = 1,
                    content = BitBucketCloud.Content(
                        html = "test",
                        markup = "markdown",
                        raw = "test"
                    ),
                    createdOn = Instant.parse("2022-04-07T15:25:25.184212+00:00"),
                    updatedOn = Instant.parse("2022-04-07T15:25:25.184261+00:00"),
                    deleted = false,
                    user = BitBucketCloud.User(
                        uuid = "danger",
                        accountId = "danger",
                        displayName = "Danger",
                        nickname = "danger"
                    )
                )
            )

            assertEquals(1, count())
            assertEquals(expectedActivity, first())
        }
    }

    @Test
    fun testOnBitBucketIsTrue() {
        assertEquals(true, dsl.danger.onBitBucketCloud)
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
