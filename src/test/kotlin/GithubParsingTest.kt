import com.danger.dangerkt.lib.*
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test
import java.util.*

class GithubParsingTest {
    private val gson = Gson()
    private val dsl
        get() = gson.fromJson(dangerJSON, DSL::class.java)
    private val github
        get() = dsl.danger.github

    @Test
    fun testItParsesTheGithubPullRequest() {
        val pullRequest = github.pullRequest
        Assert.assertEquals("Xcode updates", pullRequest.title)
        Assert.assertEquals(609, pullRequest.number)
        Assert.assertEquals(GitHubPullRequestState.CLOSED, github.pullRequest.state)
        Assert.assertEquals("I don't want to spend much time on this project, but I do want to keep it up to date with current tools so that we can deploy any fixes quickly. \n" +
                "\n" + "I tried updating to CocoaPods 1.0, but things went quickly awry. It's a large job, but not necessary for now. \n", pullRequest.body)
        Assert.assertEquals("Tue Jul 26 20:57:30 BST 2016", pullRequest.createdAt.toString())
        Assert.assertEquals("Wed Aug 17 16:26:15 BST 2016", pullRequest.updatedAt.toString())
        Assert.assertEquals("Wed Aug 17 16:26:14 BST 2016", pullRequest.closedAt.toString())
        Assert.assertEquals("Wed Aug 17 16:26:14 BST 2016", pullRequest.mergedAt.toString())
        Assert.assertEquals(false, pullRequest.isLocked)
        Assert.assertEquals(true, pullRequest.isMerged)
        Assert.assertEquals(15, pullRequest.commitCount)
        Assert.assertEquals(8, pullRequest.commentCount)
        Assert.assertEquals(11, pullRequest.reviewCommentCount)
        Assert.assertEquals(205, pullRequest.additions)
        Assert.assertEquals(111, pullRequest.deletions)
        Assert.assertEquals(56, pullRequest.changedFiles)
        Assert.assertNull(pullRequest.milestone)

        val expectedUser = GitHubUser(498212, "ashfurrow", GitHubUserType.USER)
        Assert.assertEquals(expectedUser, pullRequest.user)

        val expectedAssegnee = GitHubUser(49038, "orta", GitHubUserType.USER)
        Assert.assertEquals(expectedAssegnee, pullRequest.assignee)
        Assert.assertEquals(expectedAssegnee, pullRequest.assignees.first())

        val artsyUser = GitHubUser(546231, "artsy", GitHubUserType.ORGANIZATION)
        val eidolonRepo = GitHubRepo(22613546, "eidolon", "artsy/eidolon", false, "The Artsy Auction Kiosk App", false, "https://github.com/artsy/eidolon")

        val expectedHead = GitHubMergeRef("artsy:xcode-update", "xcode-update",
            "d769f276e066d79169a8bfa5795c8a4853f942f3", artsyUser, eidolonRepo)
        Assert.assertEquals(expectedHead, pullRequest.head)

        val expectedBase = GitHubMergeRef("artsy:master", "master", "68c8db83776c1942145f530159a3fffddb812577", artsyUser, eidolonRepo)
        Assert.assertEquals(expectedBase, pullRequest.base)
    }

    @Test
    fun testItParsesCorrectlyTheCommits() {
        val commits = github.commits

        Assert.assertEquals(15, commits.size)

        val commit = commits.first()

        val expectedUser = GitHubUser(498212, "ashfurrow", GitHubUserType.USER)
        val expectedGitAuthor = GitCommitAuthor("Ash Furrow", "ash@ashfurrow.com", "2016-07-26T19:54:16Z")
        val expectedGitCommitter = GitCommitAuthor("Ash Furrow", "ash@ashfurrow.com", "2016-07-26T19:55:00Z")
        val expectedCommit = GitCommit(null, expectedGitAuthor, expectedGitCommitter, "[Xcode] Updates for compatibility with Xcode 7.3.1.", null, "https://api.github.com/repos/artsy/eidolon/git/commits/93ae30cf2aee4241c442fb3242543490998cffdb")
        val expectedGitHubCommit = GitHubCommit("93ae30cf2aee4241c442fb3242543490998cffdb", "https://api.github.com/repos/artsy/eidolon/commits/93ae30cf2aee4241c442fb3242543490998cffdb", expectedUser, expectedCommit, expectedUser)

        Assert.assertEquals(expectedGitHubCommit, commit)
    }

    @Test
    fun testItCorrectlyParsesTheIssue() {
        val issue = github.issue

        Assert.assertEquals(167696965, issue.id)
        Assert.assertEquals(609, issue.number)
        Assert.assertEquals("Xcode updates", issue.title)
        Assert.assertEquals("I don't want to spend much time on this project, but I do want to keep it up to date with current tools so that we can deploy any fixes quickly. \n" +
                "\n" + "I tried updating to CocoaPods 1.0, but things went quickly awry. It's a large job, but not necessary for now. \n", issue.body)
        Assert.assertEquals(GitHubIssueState.CLOSED, issue.state)
        Assert.assertEquals(false, issue.isLocked)
        Assert.assertEquals(8, issue.commentCount)
        Assert.assertEquals(Date(1469563050000), issue.createdAt)
        Assert.assertEquals(Date(1471447574000), issue.updatedAt)
        Assert.assertEquals(Date(1471447574000), issue.closedAt)
        Assert.assertTrue(issue.labels.isEmpty())

        val expectedCreator = GitHubUser(1, "octocat", GitHubUserType.USER)
        val expectedMilestone = GitHubMilestone(1002604, 1, GitHubMilestoneState.OPEN, "v1.0", "Tracking milestone for version 1.0", expectedCreator, 4, 8, Date(1302466171000), Date(1393873090000), Date(1360675321000), Date(1349825941000))
        Assert.assertEquals(expectedMilestone, issue.milestone)
    }

    @Test
    fun testItParsesTheRequestedReviewersCorrectly() {
        val requestedReviewers = github.requestedReviewers

        val expectedUser = GitHubUser(1, "octocat", GitHubUserType.USER)
        Assert.assertEquals(expectedUser, requestedReviewers.users.first())

        val expectedTeam = GitHubTeam(1, "Justice League")
        Assert.assertEquals(expectedTeam, requestedReviewers.teams.first())
    }
}