package systems.danger.kotlin.models.github

import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Test
import systems.danger.kotlin.models.danger.DSL
import systems.danger.kotlin.models.git.GitCommit
import systems.danger.kotlin.models.git.GitCommitAuthor
import systems.danger.kotlin.utils.TestUtils.JSONFiles
import systems.danger.kotlin.utils.TestUtils

class GitHubParsingTests {
    private val dsl: DSL = TestUtils.Json.decodeFromString(JSONFiles.githubDangerJSON)
    private val github = dsl.danger.github

    @Test
    fun testItParsesTheGithubPullRequest() {
        with(github.pullRequest) {
            assertEquals("Xcode updates", title)
            assertEquals(609, number)
            assertEquals(GitHubPullRequestState.CLOSED, state)
            assertEquals(
                "I don't want to spend much time on this project, but I do want to keep it up to date with current tools so that we can deploy any fixes quickly. \n" +
                        "\n" + "I tried updating to CocoaPods 1.0, but things went quickly awry. It's a large job, but not necessary for now. \n",
                body
            )
            assertEquals(1469563050000, createdAt.toEpochMilliseconds())
            assertEquals(1471447575000, updatedAt.toEpochMilliseconds())
            assertEquals(1471447574000, closedAt?.toEpochMilliseconds())
            assertEquals(1471447574000, mergedAt?.toEpochMilliseconds())
            assertEquals(false, isLocked)
            assertEquals(true, isMerged)
            assertEquals(15, commitCount)
            assertEquals(8, commentCount)
            assertEquals(11, reviewCommentCount)
            assertEquals(205, additions)
            assertEquals(111, deletions)
            assertEquals(56, changedFiles)
            assertEquals("https://github.com/artsy/eidolon/pull/609", htmlURL)
            assertNull(milestone)


            val expectedUser = GitHubUser(
                498212,
                "ashfurrow",
                GitHubUserType.USER,
                "https://avatars3.githubusercontent.com/u/498212?v=4"
            )
            assertEquals(expectedUser, user)

            val expectedAssegnee = GitHubUser(
                49038,
                "orta",
                GitHubUserType.USER,
                "https://avatars2.githubusercontent.com/u/49038?v=4"
            )
            assertEquals(expectedAssegnee, assignee)
            assertEquals(expectedAssegnee, assignees.first())

            val artsyUser = GitHubUser(
                546231,
                "artsy",
                GitHubUserType.ORGANIZATION,
                "https://avatars3.githubusercontent.com/u/546231?v=4"
            )
            val eidolonRepo = GitHubRepo(
                22613546,
                "eidolon",
                "artsy/eidolon",
                false,
                "The Artsy Auction Kiosk App",
                false,
                "https://github.com/artsy/eidolon"
            )

            val expectedHead = GitHubMergeRef(
                "artsy:xcode-update", "xcode-update",
                "d769f276e066d79169a8bfa5795c8a4853f942f3", artsyUser, eidolonRepo
            )
            assertEquals(expectedHead, head)

            val expectedBase = GitHubMergeRef(
                "artsy:master",
                "master",
                "68c8db83776c1942145f530159a3fffddb812577",
                artsyUser,
                eidolonRepo
            )
            assertEquals(expectedBase, base)
        }
    }

    @Test
    fun testItParsesCorrectlyTheCommits() {
        val commits = github.commits

        assertEquals(15, commits.size)

        val commit = commits.first()

        val expectedUser = GitHubUser(
            498212,
            "ashfurrow",
            GitHubUserType.USER,
            "https://avatars3.githubusercontent.com/u/498212?v=4"
        )
        val expectedGitAuthor = GitCommitAuthor(
            "Ash Furrow",
            "ash@ashfurrow.com",
            "2016-07-26T19:54:16Z"
        )
        val expectedGitCommitter = GitCommitAuthor(
            "Ash Furrow",
            "ash@ashfurrow.com",
            "2016-07-26T19:55:00Z"
        )
        val expectedCommit = GitCommit(
            null,
            expectedGitAuthor,
            expectedGitCommitter,
            "[Xcode] Updates for compatibility with Xcode 7.3.1.",
            listOf(),
            "https://api.github.com/repos/artsy/eidolon/git/commits/93ae30cf2aee4241c442fb3242543490998cffdb"
        )
        val expectedGitHubCommit = GitHubCommit(
            "93ae30cf2aee4241c442fb3242543490998cffdb",
            "https://api.github.com/repos/artsy/eidolon/commits/93ae30cf2aee4241c442fb3242543490998cffdb",
            expectedUser,
            expectedCommit,
            expectedUser
        )

        assertEquals(expectedGitHubCommit, commit)
    }

    @Test
    fun testItCorrectlyParsesTheIssue() {
        with(github.issue) {
            assertEquals(2190001234, id)
            assertEquals(609, number)
            assertEquals("Xcode updates", title)
            assertEquals(
                "I don't want to spend much time on this project, but I do want to keep it up to date with current tools so that we can deploy any fixes quickly. \n" +
                        "\n" + "I tried updating to CocoaPods 1.0, but things went quickly awry. It's a large job, but not necessary for now. \n",
                body
            )
            assertEquals(GitHubIssueState.CLOSED, state)
            assertEquals(false, isLocked)
            assertEquals(8, commentCount)
            assertEquals(Instant.fromEpochMilliseconds(1469563050000), createdAt)
            assertEquals(Instant.fromEpochMilliseconds(1471447574000), updatedAt)
            assertEquals(null, closedAt)
            assertTrue(labels.isEmpty())

            val expectedCreator = GitHubUser(
                1,
                "octocat",
                GitHubUserType.USER,
                "https://github.com/images/error/octocat_happy.gif"
            )
            val expectedMilestone = GitHubMilestone(
                1002604,
                1,
                GitHubMilestoneState.OPEN,
                "v1.0",
                "Tracking milestone for version 1.0",
                expectedCreator,
                4,
                8,
                Instant.fromEpochMilliseconds(1302466171000),
                Instant.fromEpochMilliseconds(1393873090000),
                Instant.fromEpochMilliseconds(1360675321000),
                Instant.fromEpochMilliseconds(1349825941000)
            )
            assertEquals(expectedMilestone, milestone)
        }
    }

    @Test
    fun testItParsesTheRequestedReviewersCorrectly() {
        with(github.requestedReviewers) {

            val expectedUser = GitHubUser(
                1,
                "octocat",
                GitHubUserType.USER,
                "https://github.com/images/error/octocat_happy.gif"
            )
            assertEquals(expectedUser, users.first())

            val expectedTeam = GitHubTeam(1, "Justice League")
            assertEquals(expectedTeam, teams.first())
        }
    }

    @Test
    fun testItParsesTheMilestonesWithSomeNullAttributes() {
        val dsl: DSL = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }.decodeFromString(JSONFiles.githubWithSomeNullsAttributeDangerJSON)
        val github = dsl.danger.github

        with(github.issue.milestone!!) {
            assertNull(dueOn)
            assertNull(closedAt)
            assertNull(description)
        }
    }

    @Test
    fun testOnGitHubIsTrue() {
        assertEquals(true, dsl.danger.onGitHub)
    }

    @Test
    fun testOnBitBucketIsFalse() {
        assertEquals(false, dsl.danger.onBitBucketServer)
    }

    @Test
    fun testOnGitLabIsFalse() {
        assertEquals(false, dsl.danger.onGitLab)
    }
}
