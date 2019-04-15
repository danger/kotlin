package com.danger.dangerkotlin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

/**
 * The GitHub metadata for your pull request.
 */
@JsonClass(generateAdapter = true)
data class GitHub(
    val issue: GitHubIssue,
    @Json(name = "pr") val pullRequest: GitHubPR,
    val commits: Array<GitHubCommit>,
    val reviews: Array<GitHubReview>,
    @Json(name = "requested_reviewers") val requestedReviewers: GitHubRequestedReviewers
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitHub

        if (issue != other.issue) return false
        if (pullRequest != other.pullRequest) return false
        if (!commits.contentEquals(other.commits)) return false
        if (!reviews.contentEquals(other.reviews)) return false
        if (requestedReviewers != other.requestedReviewers) return false

        return true
    }

    override fun hashCode(): Int {
        var result = issue.hashCode()
        result = 31 * result + pullRequest.hashCode()
        result = 31 * result + commits.contentHashCode()
        result = 31 * result + reviews.contentHashCode()
        result = 31 * result + requestedReviewers.hashCode()
        return result
    }
}

enum class GitHubPullRequestState(val value: String) {
   @Json(name = "closed")
    CLOSED("closed"),
   @Json(name = "open")
    OPEN("open"),
   @Json(name = "merged")
    MERGED("merged"),
   @Json(name = "locked")
    LOCKED("locked")
}

/**
 * The GitHub pull request
 *
 * @property number The number of the pull request.
 * @property title The title of the pull request.
 * @property body The markdown body message of the pull request.
 * @property user The user who submitted the pull request.
 * @property assignee The user who submitted the pull request.
 * @property assignees The users who are assigned to the pull request.
 * @property createdAt The date for when the pull request was created.
 * @property updatedAt The date for when the pull request was updated.
 * @property closedAt The date for when the pull request was closed.
 * @property mergedAt The date for when the pull request was merged.
 * @property head The merge reference for the _other_ repo.
 * @property base The merge reference for the _this_ repo.
 * @property state The state for the pull request: open, closed, locked, merged.
 * @property isLocked A boolean indicating if the pull request has been locked to contributors only.
 * @property isMerged A boolean indicating if the pull request has been merged.
 * @property commitCount The number of commits in the pull request.
 * @property commentCount The number of comments in the pull request.
 * @property reviewCommentCount The number of review-specific comments in the pull request.
 * @property additions The number of added lines in the pull request.
 * @property deletions The number of deleted lines in the pull request.
 * @property changedFiles The number of files changed in the pull request.
 * @property milestone The milestone of the pull request
 * @property htmlURL The link back to this PR as user-facing
 */
@JsonClass(generateAdapter = true)
data class GitHubPR(
        val number: Int,
        val title: String,
        val body: String,
        val user: GitHubUser,
        val assignee: GitHubUser?,
        val assignees: Array<GitHubUser>,
        @Json(name = "created_at") val createdAt: Date,
        @Json(name = "updated_at") val updatedAt: Date,
        @Json(name = "closed_at") val closedAt: Date,
        @Json(name = "merged_at") val mergedAt: Date,
        val head: GitHubMergeRef,
        val base: GitHubMergeRef,
        val state: GitHubPullRequestState,
        @Json(name = "locked") val isLocked: Boolean,
        @Json(name = "merged") val isMerged: Boolean?,
        @Json(name = "commits") val commitCount: Int?,
        @Json(name = "comments") val commentCount: Int?,
        @Json(name = "review_comments") val reviewCommentCount: Int?,
        val additions: Int?,
        val deletions: Int?,
        @Json(name = "changed_files") val changedFiles: Int?,
        val milestone: GitHubMilestone?,
        @Json(name = "html_url") val htmlURL: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitHubPR

        if (number != other.number) return false
        if (title != other.title) return false
        if (body != other.body) return false
        if (user != other.user) return false
        if (assignee != other.assignee) return false
        if (!assignees.contentEquals(other.assignees)) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (closedAt != other.closedAt) return false
        if (mergedAt != other.mergedAt) return false
        if (head != other.head) return false
        if (base != other.base) return false
        if (state != other.state) return false
        if (isLocked != other.isLocked) return false
        if (isMerged != other.isMerged) return false
        if (commitCount != other.commitCount) return false
        if (commentCount != other.commentCount) return false
        if (reviewCommentCount != other.reviewCommentCount) return false
        if (additions != other.additions) return false
        if (deletions != other.deletions) return false
        if (changedFiles != other.changedFiles) return false
        if (milestone != other.milestone) return false
        if (htmlURL != other.htmlURL) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number
        result = 31 * result + title.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + (assignee?.hashCode() ?: 0)
        result = 31 * result + assignees.contentHashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + closedAt.hashCode()
        result = 31 * result + mergedAt.hashCode()
        result = 31 * result + head.hashCode()
        result = 31 * result + base.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + isLocked.hashCode()
        result = 31 * result + (isMerged?.hashCode() ?: 0)
        result = 31 * result + (commitCount ?: 0)
        result = 31 * result + (commentCount ?: 0)
        result = 31 * result + (reviewCommentCount ?: 0)
        result = 31 * result + (additions ?: 0)
        result = 31 * result + (deletions ?: 0)
        result = 31 * result + (changedFiles ?: 0)
        result = 31 * result + (milestone?.hashCode() ?: 0)
        result = 31 * result + htmlURL.hashCode()
        return result
    }
}

/**
 * A GitHub team
 *
 * @property id The UUID for the team.
 * @property name The team name.
 */
@JsonClass(generateAdapter = true)
data class GitHubTeam(
    val id: Int,
    val name: String
)

/**
 * Represents the payload for a PR's requested reviewers value.
 *
 * @property users The list of users of whom a review has been requested..
 * @property teams The list of teams of whom a review has been requested.
 */
@JsonClass(generateAdapter = true)
data class GitHubRequestedReviewers(
    val users: Array<GitHubUser>,
    val teams: Array<GitHubTeam>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitHubRequestedReviewers

        if (!users.contentEquals(other.users)) return false
        if (!teams.contentEquals(other.teams)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = users.contentHashCode()
        result = 31 * result + teams.contentHashCode()
        return result
    }
}

/**
 * Represents a branch in PR
 *
 * @property label The human display name for the merge reference, e.g. "artsy:master".
 * @property ref The reference point for the merge, e.g. "master".
 * @property sha The reference point for the merge, e.g. "704dc55988c6996f69b6873c2424be7d1de67bbe".
 * @property user The user that owns the merge reference e.g. "artsy".
 * @property repo The repo from which the reference comes from.
 */
@JsonClass(generateAdapter = true)
data class GitHubMergeRef(
    val label: String,
    val ref: String,
    val sha: String,
    val user: GitHubUser,
    val repo: GitHubRepo
)

/**
 *  Represents the payload for a repo.
 *
 * @property id Generic UUID.
 * @property name The name of the repo, e.g. "danger-swift".
 * @property fullName The full name of the owner + repo, e.g. "Danger/danger-swift"
 * @property isPrivate A boolean stating whether the repo is publicly accessible.
 * @property description A textual description of the repo.
 * @property isFork A boolean stating whether the repo is a fork.
 * @property htmlURL The root web URL for the repo, e.g. https://github.com/artsy/emission
 */
@JsonClass(generateAdapter = true)
data class GitHubRepo(
    val id: Int,
    val name: String,
    @Json(name = "full_name") val fullName: String,
    @Json(name = "private") val isPrivate: Boolean,
    val description: String?,
    @Json(name = "fork") val isFork: Boolean,
    @Json(name = "html_url") val htmlURL: String
)

enum class GitHubReviewState(val value: String) {
    @Json(name = "APPROVED")
    APPROVED("APPROVED"),
    @Json(name = "CHANGES_REQUESTED")
    CHANGES_REQUESTED("CHANGES_REQUESTED"),
    @Json(name = "COMMENTED")
    COMMENTED("COMMENTED"),
    @Json(name = "PENDING")
    PENDING("PENDING"),
    @Json(name = "DISMISSED")
    DISMISSED("DISMISSED")
}

/**
 * Represents the payload for a PR review.
 *
 * @property user The user who has completed the review or has been requested to review.
 * @property id The id for the review (if a review was made).
 * @property body The body of the review (if a review was made).
 * @property commitId The commit ID the review was made on (if a review was made).
 * @property state The state of the review (if a review was made).
 */
@JsonClass(generateAdapter = true)
data class GitHubReview(
    val user: GitHubUser,
    val id: Int?,
    val body: String?,
    @Json(name = "commit_id") val commitId: String?,
    val state: GitHubReviewState?
)

/**
 * A GitHub specific implementation of a git commit.
 *
 * @property sha The SHA for the commit.
 * @property url The URL for the commit on GitHub.
 * @property author The GitHub user who wrote the code.
 * @property commit The raw commit metadata.
 * @property committer The GitHub user who shipped the code.
 */
@JsonClass(generateAdapter = true)
data class GitHubCommit(
    val sha: String,
    val url: String,
    val author: GitHubUser?,
    val commit: GitCommit,
    val committer: GitHubUser?
)

enum class GitHubIssueState(val value: String) {
    @Json(name = "closed")
    CLOSED("closed"),
    @Json(name = "open")
    OPEN("open"),
    @Json(name = "locked")
    LOCKED("locked")
}

/**
 * The GitHub pull request
 *
 * @property number The number of the pull request.
 * @property title The title of the pull request.
 * @property body The markdown body message of the pull request.
 * @property user The user who submitted the pull request.
 * @property assignee The user who submitted the pull request.
 * @property assignees The users who are assigned to the pull request.
 * @property createdAt The date for when the pull request was created.
 * @property updatedAt The date for when the pull request was updated.
 * @property closedAt The date for when the pull request was closed.
 * @property mergedAt The date for when the pull request was merged.
 * @property head The merge reference for the _other_ repo.
 * @property base The merge reference for the _this_ repo.
 * @property state The state for the pull request: open, closed, locked, merged.
 * @property isLocked A boolean indicating if the pull request has been locked to contributors only.
 * @property isMerged A boolean indicating if the pull request has been merged.
 * @property commitCount The number of commits in the pull request.
 * @property commentCount The number of comments in the pull request.
 * @property reviewCommentCount The number of review-specific comments in the pull request.
 * @property additions The number of added lines in the pull request.
 * @property deletions The number of deleted lines in the pull request.
 * @property changedFiles The number of files changed in the pull request.
 * @property milestone The milestone of the pull request
 */
@JsonClass(generateAdapter = true)
data class GitHubIssue(
    val id: Int,
    val number: Int,
    val title: String,
    val user: GitHubUser,
    val state: GitHubIssueState,
    @Json(name = "locked") val isLocked: Boolean,
    val body: String,
    @Json(name = "comments") val commentCount: Int,
    val assignee: GitHubUser?,
    val assignees: Array<GitHubUser>,
    val milestone: GitHubMilestone,
    @Json(name = "created_at") val createdAt: Date,
    @Json(name = "updated_at") val updatedAt: Date,
    @Json(name = "closed_at") val closedAt: Date,
    val labels: Array<GitHubIssueLabel>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitHubIssue

        if (id != other.id) return false
        if (number != other.number) return false
        if (title != other.title) return false
        if (user != other.user) return false
        if (state != other.state) return false
        if (isLocked != other.isLocked) return false
        if (body != other.body) return false
        if (commentCount != other.commentCount) return false
        if (assignee != other.assignee) return false
        if (!assignees.contentEquals(other.assignees)) return false
        if (milestone != other.milestone) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (closedAt != other.closedAt) return false
        if (!labels.contentEquals(other.labels)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + number
        result = 31 * result + title.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + isLocked.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + commentCount
        result = 31 * result + (assignee?.hashCode() ?: 0)
        result = 31 * result + assignees.contentHashCode()
        result = 31 * result + milestone.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + closedAt.hashCode()
        result = 31 * result + labels.contentHashCode()
        return result
    }
}

/**
 * @property id The id number of this label.
 * @property url The URL that links to this label.
 * @property name The name of the label.
 * @property color TThe color associated with this label.
 */
@JsonClass(generateAdapter = true)
data class GitHubIssueLabel(
        val id: Int,
        val url: String,
        val name: String,
        val color: String
)

enum class GitHubUserType(val value: String) {
    @Json(name = "User")
    USER("User"),
    @Json(name = "Organization")
    ORGANIZATION("Organization")
}

/**
 * A GitHub user account.
 *
 * @property id The UUID for the user organization.
 * @property login The handle for the user or organization.
 * @property type The type of user: user or organization.
 */
@JsonClass(generateAdapter = true)
data class GitHubUser(
    val id: Int,
    val login: String,
    val type: GitHubUserType
)

enum class GitHubMilestoneState(val value: String) {
    @Json(name = "close")
    CLOSE("close"),
    @Json(name = "open")
    OPEN("open"),
    @Json(name = "all")
    ALL("all")
}

/**
 * A GitHub milestone
 *
 * @property id The id number of this milestone
 * @property number The number of this milestone
 * @property state The state of this milestone: open, closed, all.
 * @property title The title of this milestone.
 * @property description The description of this milestone.
 * @property creator The user who created this milestone.
 * @property openIssues The number of open issues in this milestone.
 * @property closedIssues The number of closed issues in this milestone.
 * @property createdAt The date  for when this milestone was created.
 * @property updatedAt The date for when the milestone was update.
 * @property closedAt The date for when the milestone was closed.
 * @property dueOn The date for the due of this milestone.
 */
@JsonClass(generateAdapter = true)
data class GitHubMilestone(
    val id: Int,
    val number: Int,
    val state: GitHubMilestoneState,
    val title: String,
    val description: String,
    val creator: GitHubUser,
    @Json(name = "open_issues") val openIssues: Int,
    @Json(name = "closed_issues") val closedIssues: Int,
    @Json(name = "created_at") val createdAt: Date,
    @Json(name = "updated_at") val updatedAt: Date,
    @Json(name = "closed_at") val closedAt: Date,
    @Json(name = "due_on") val dueOn: Date
)
