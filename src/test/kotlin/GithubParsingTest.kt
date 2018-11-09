import com.danger.dangerkt.lib.*
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class GithubParsingTest {
    private val gson = Gson()
    private val dsl
        get() = gson.fromJson(dangerJSON, DSL::class.java)

    @Test
    fun testItParsesTheGithubPullRequest() {
        val github = dsl.danger.github

        Assert.assertNotNull(github)

        Assert.assertEquals("Xcode updates", github.pullRequest.title)
        Assert.assertEquals(609, github.pullRequest.number)
        Assert.assertEquals(GitHubPullRequestState.CLOSED, github.pullRequest.state)
        Assert.assertEquals("I don't want to spend much time on this project, but I do want to keep it up to date with current tools so that we can deploy any fixes quickly. \n" +
                "\n" + "I tried updating to CocoaPods 1.0, but things went quickly awry. It's a large job, but not necessary for now. \n", github.pullRequest.body)
        Assert.assertEquals("Tue Jul 26 20:57:30 BST 2016", github.pullRequest.createdAt.toString())
        Assert.assertEquals("Wed Aug 17 16:26:15 BST 2016", github.pullRequest.updatedAt.toString())
        Assert.assertEquals("Wed Aug 17 16:26:14 BST 2016", github.pullRequest.closedAt.toString())
        Assert.assertEquals("Wed Aug 17 16:26:14 BST 2016", github.pullRequest.mergedAt.toString())
        Assert.assertEquals(false, github.pullRequest.isLocked)
        Assert.assertEquals(true, github.pullRequest.isMerged)
        Assert.assertEquals(15, github.pullRequest.commitCount)
        Assert.assertEquals(8, github.pullRequest.commentCount)
        Assert.assertEquals(11, github.pullRequest.reviewCommentCount)
        Assert.assertEquals(205, github.pullRequest.additions)
        Assert.assertEquals(111, github.pullRequest.deletions)
        Assert.assertEquals(56, github.pullRequest.changedFiles)
        Assert.assertNull(github.pullRequest.milestone)

        val expectedUser = GitHubUser(498212, "ashfurrow", GitHubUserType.USER)
        Assert.assertEquals(expectedUser, github.pullRequest.user)

        val expectedAssegnee = GitHubUser(49038, "orta", GitHubUserType.USER)
        Assert.assertEquals(expectedAssegnee, github.pullRequest.assignee)
        Assert.assertEquals(expectedAssegnee, github.pullRequest.assignees[0])

        val artsyUser = GitHubUser(546231, "artsy", GitHubUserType.ORGANIZATION)
        val eidolonRepo = GitHubRepo(22613546, "eidolon", "artsy/eidolon", false, "The Artsy Auction Kiosk App", false, "https://github.com/artsy/eidolon")

        val expectedHead = GitHubMergeRef("artsy:xcode-update", "xcode-update",
            "d769f276e066d79169a8bfa5795c8a4853f942f3", artsyUser, eidolonRepo)
        Assert.assertEquals(expectedHead, github.pullRequest.head)

        val expectedBase = GitHubMergeRef("artsy:master", "master", "68c8db83776c1942145f530159a3fffddb812577", artsyUser, eidolonRepo)
        Assert.assertEquals(expectedBase, github.pullRequest.base)
    }
}