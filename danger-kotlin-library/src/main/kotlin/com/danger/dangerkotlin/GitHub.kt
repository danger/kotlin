package com.danger.dangerkotlin

import com.google.gson.annotations.SerializedName
import java.util.*

data class GitHub(
    val issue: GitHubIssue,
    @SerializedName("pr") val pullRequest: GitHubPR,
    val commits: Array<GitHubCommit>,
    val reviews: Array<GitHubReview>,
    @SerializedName("requested_reviewers") val requestedReviewers: GitHubRequestedReviewers
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
    @SerializedName("closed")
    CLOSED("closed"),
    @SerializedName("open")
    OPEN("open"),
    @SerializedName("merged")
    MERGED("merged"),
    @SerializedName("locked")
    LOCKED("locked")
}

data class GitHubPR(
        val number: Int,
        val title: String,
        val body: String,
        val user: GitHubUser,
        val assignee: GitHubUser?,
        val assignees: Array<GitHubUser>,
        @SerializedName("created_at") val createdAt: Date,
        @SerializedName("updated_at") val updatedAt: Date,
        @SerializedName("closed_at") val closedAt: Date,
        @SerializedName("merged_at") val mergedAt: Date,
        val head: GitHubMergeRef,
        val base: GitHubMergeRef,
        val state: GitHubPullRequestState,
        @SerializedName("locked") val isLocked: Boolean,
        @SerializedName("merged") val isMerged: Boolean?,
        @SerializedName("commits") val commitCount: Int?,
        @SerializedName("comments") val commentCount: Int?,
        @SerializedName("review_comments") val reviewCommentCount: Int?,
        val additions: Int?,
        val deletions: Int?,
        @SerializedName("changed_files") val changedFiles: Int?,
        val milestone: GitHubMilestone?
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
        return result
    }
}

data class GitHubTeam(
    val id: Int,
    val name: String
)

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

data class GitHubMergeRef(
    val label: String,
    val ref: String,
    val sha: String,
    val user: GitHubUser,
    val repo: GitHubRepo
)

data class GitHubRepo(
    val id: Int,
    val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("private") val isPrivate: Boolean,
    val description: String?,
    @SerializedName("fork") val isFork: Boolean,
    @SerializedName("html_url") val htmlURL: String
)

enum class GitHubReviewState(val value: String) {
    @SerializedName("APPROVED")
    APPROVED("APPROVED"),
    @SerializedName("CHANGES_REQUESTED")
    CHANGES_REQUESTED("CHANGES_REQUESTED"),
    @SerializedName("COMMENTED")
    COMMENTED("COMMENTED"),
    @SerializedName("PENDING")
    PENDING("PENDING"),
    @SerializedName("DISMISSED")
    DISMISSED("DISMISSED")
}

data class GitHubReview(
    val user: GitHubUser,
    val id: Int?,
    val body: String?,
    @SerializedName("commit_id") val commitId: String?,
    val state: GitHubReviewState?
)

data class GitHubCommit(
    val sha: String,
    val url: String,
    val author: GitHubUser?,
    val commit: GitCommit,
    val committer: GitHubUser?
)

enum class GitHubIssueState(val value: String) {
    @SerializedName("closed")
    CLOSED("closed"),
    @SerializedName("open")
    OPEN("open"),
    @SerializedName("locked")
    LOCKED("locked")
}

data class GitHubIssue(
    val id: Int,
    val number: Int,
    val title: String,
    val user: GitHubUser,
    val state: GitHubIssueState,
    @SerializedName("locked") val isLocked: Boolean,
    val body: String,
    @SerializedName("comments") val commentCount: Int,
    val assignee: GitHubUser?,
    val assignees: Array<GitHubUser>,
    val milestone: GitHubMilestone,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("updated_at") val updatedAt: Date,
    @SerializedName("closed_at") val closedAt: Date,
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

data class GitHubIssueLabel(
        val id: Int,
        val url: String,
        val name: String,
        val color: String
)

enum class GitHubUserType(val value: String) {
    @SerializedName("User")
    USER("User"),
    @SerializedName("Organization")
    ORGANIZATION("Organization")
}

data class GitHubUser(
    val id: Int,
    val login: String,
    val type: GitHubUserType
)

enum class GitHubMilestoneState(val value: String) {
    @SerializedName("close")
    CLOSE("close"),
    @SerializedName("open")
    OPEN("open"),
    @SerializedName("all")
    ALL("all")
}

data class GitHubMilestone(
    val id: Int,
    val number: Int,
    val state: GitHubMilestoneState,
    val title: String,
    val description: String,
    val creator: GitHubUser,
    @SerializedName("open_issues") val openIssues: Int,
    @SerializedName("closed_issues") val closedIssues: Int,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("updated_at") val updatedAt: Date,
    @SerializedName("closed_at") val closedAt: Date,
    @SerializedName("due_on") val dueOn: Date
)
