package systems.danger.kotlin

import com.squareup.moshi.Json
import java.util.*

data class GitLab(
    @Json(name="mr")
    val mergeRequest: GitLabMergeRequest,
    val metadata: GitLabMetadata
)

data class GitLabDiffRefs (
    @Json(name="base_sha")
    val baseSha: String,
    @Json(name="head_sha")
    val headSha: String,
    @Json(name="start_sha")
    val startSha: String
)

data class GitLabUserMergeData(
    @Json(name="can_merge")
    val canMerge: Boolean
)

data class GitLabMergeRequest(
    @Json(name="allow_collaboration")
    val allowCollaboration: Boolean?,
    @Json(name="allow_maintainer_to_push")
    val allowMaintainerToPush: Boolean?,
    @Json(name="approvals_before_merge")
    val approvalsBeforeMerge: Int?,
    val assignee: GitLabUser?,
    val author: GitLabUser,
    @Json(name="changes_count")
    val changesCount: String,
    @Json(name="closed_at")
    val closedAt: Date?,
    @Json(name="closed_by")
    val closedBy: GitLabUser?,
    val description: String,
    @Json(name="diff_refs")
    val diffRefs: GitLabDiffRefs,
    val downvotes: Int,
    @Json(name="first_deployed_to_production_at")
    val firstDeployedToProductionAt: Date?,
    @Json(name="force_remove_source_branch")
    val forceRemoveSourceBranch: Boolean,
    val id: Int,
    val iid: Int,
    @Json(name="latest_build_finished_at")
    val latestBuildFinishedAt: Date?,
    @Json(name="latest_build_started_at")
    val latestBuildStartedAt: Date?,
    val labels: Array<String>,
    @Json(name="merge_commit_sha")
    val mergeCommitSha: String?,
    @Json(name="merged_at")
    val mergedAt: Date?,
    @Json(name="merged_by")
    val mergedBy: GitLabUser?,
    @Json(name="merge_when_pipeline_succeeds")
    val mergeOnPipelineSuccess: Boolean,
    val milestone: GitLabMilestone?,
    val pipeline: GitLabPipeline,
    @Json(name="project_id")
    val projectId: String,
    val sha: String,
    @Json(name="should_remove_source_branch")
    val shouldRemoveSourceBranch: Boolean?,
    @Json(name="source_branch")
    val sourceBranch: String,
    @Json(name="source_project_id")
    val sourceProjectId: String,
    val state: GitLabMergeRequestState,
    val subscribed: Boolean,
    @Json(name="target_branch")
    val targetBranch: String,
    @Json(name="target_project_id")
    val targetProjectId: String,
    val timeStats: GitLabMergeRequestTimeStats?,
    val title: String,
    val upvotes: Int,
    @Json(name="user")
    private val userMergeData: GitLabUserMergeData,
    @Json(name="user_notes_count")
    val userNotesCount: Int,
    @Json(name="web_url")
    val webUrl: String,
    @Json(name="work_in_progress")
    val workInProgress: Boolean
) {
    val canMerge: Boolean
        get() = this.userMergeData.canMerge

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitLabMergeRequest

        if (allowCollaboration != other.allowCollaboration) return false
        if (allowMaintainerToPush != other.allowMaintainerToPush) return false
        if (approvalsBeforeMerge != other.approvalsBeforeMerge) return false
        if (assignee != other.assignee) return false
        if (author != other.author) return false
        if (changesCount != other.changesCount) return false
        if (closedAt != other.closedAt) return false
        if (closedBy != other.closedBy) return false
        if (description != other.description) return false
        if (diffRefs != other.diffRefs) return false
        if (downvotes != other.downvotes) return false
        if (firstDeployedToProductionAt != other.firstDeployedToProductionAt) return false
        if (forceRemoveSourceBranch != other.forceRemoveSourceBranch) return false
        if (id != other.id) return false
        if (iid != other.iid) return false
        if (latestBuildFinishedAt != other.latestBuildFinishedAt) return false
        if (latestBuildStartedAt != other.latestBuildStartedAt) return false
        if (!labels.contentEquals(other.labels)) return false
        if (mergeCommitSha != other.mergeCommitSha) return false
        if (mergedAt != other.mergedAt) return false
        if (mergedBy != other.mergedBy) return false
        if (mergeOnPipelineSuccess != other.mergeOnPipelineSuccess) return false
        if (milestone != other.milestone) return false
        if (pipeline != other.pipeline) return false
        if (projectId != other.projectId) return false
        if (sha != other.sha) return false
        if (shouldRemoveSourceBranch != other.shouldRemoveSourceBranch) return false
        if (sourceBranch != other.sourceBranch) return false
        if (sourceProjectId != other.sourceProjectId) return false
        if (state != other.state) return false
        if (subscribed != other.subscribed) return false
        if (targetBranch != other.targetBranch) return false
        if (targetProjectId != other.targetProjectId) return false
        if (timeStats != other.timeStats) return false
        if (userMergeData != other.userMergeData) return false
        if (title != other.title) return false
        if (upvotes != other.upvotes) return false
        if (userNotesCount != other.userNotesCount) return false
        if (webUrl != other.webUrl) return false
        if (workInProgress != other.workInProgress) return false

        return true
    }

    override fun hashCode(): Int {
        var result = allowCollaboration.hashCode()
        result = 31 * result + allowMaintainerToPush.hashCode()
        result = 31 * result + approvalsBeforeMerge.hashCode()
        result = 31 * result + (assignee?.hashCode() ?: 0)
        result = 31 * result + author.hashCode()
        result = 31 * result + changesCount.hashCode()
        result = 31 * result + closedAt.hashCode()
        result = 31 * result + closedBy.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + diffRefs.hashCode()
        result = 31 * result + downvotes
        result = 31 * result + (firstDeployedToProductionAt?.hashCode() ?: 0)
        result = 31 * result + forceRemoveSourceBranch.hashCode()
        result = 31 * result + id
        result = 31 * result + iid
        result = 31 * result + latestBuildFinishedAt.hashCode()
        result = 31 * result + latestBuildStartedAt.hashCode()
        result = 31 * result + labels.contentHashCode()
        result = 31 * result + (mergeCommitSha?.hashCode() ?: 0)
        result = 31 * result + (mergedAt?.hashCode() ?: 0)
        result = 31 * result + (mergedBy?.hashCode() ?: 0)
        result = 31 * result + mergeOnPipelineSuccess.hashCode()
        result = 31 * result + (milestone?.hashCode() ?: 0)
        result = 31 * result + pipeline.hashCode()
        result = 31 * result + projectId.hashCode()
        result = 31 * result + sha.hashCode()
        result = 31 * result + (shouldRemoveSourceBranch?.hashCode() ?: 0)
        result = 31 * result + sourceBranch.hashCode()
        result = 31 * result + sourceProjectId.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + subscribed.hashCode()
        result = 31 * result + targetBranch.hashCode()
        result = 31 * result + targetProjectId.hashCode()
        result = 31 * result + timeStats.hashCode()
        result = 31 * result + userMergeData.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + upvotes
        result = 31 * result + userNotesCount
        result = 31 * result + webUrl.hashCode()
        result = 31 * result + workInProgress.hashCode()
        return result
    }
}

data class GitLabMergeRequestTimeStats(
    @Json(name="human_time_estimate")
    val humanTimeEstimate: Int?,
    @Json(name="human_time_spent")
    val humanTimeSpent: Int?,
    @Json(name="time_estimate")
    val timeEstimate: Int,
    @Json(name="total_time_spent")
    val totalTimeSpent: Int
)

data class GitLabMetadata(
    val pullRequestID: String,
    val repoSlug: String
)

enum class GitLabMergeRequestState {
    @Json(name = "closed")
    closed,
    @Json(name = "locked")
    locked,
    @Json(name = "merged")
    merged,
    @Json(name = "opened")
    opened
}

data class GitLabMilestone(
    @Json(name="created_at")
    val createdAt: Date,
    val description: String,
    @Json(name="due_date")
    val dueDate: Date,
    val id: Int,
    val iid: Int,
    @Json(name="project_id")
    val projectID: Int,
    @Json(name="start_date")
    val startDate: Date,
    val state: GitLabMilestoneState,
    val title: String,
    @Json(name="updated_at")
    val updatedAt: Date,
    @Json(name="web_url")
    val webUrl: String
)

enum class GitLabMilestoneState {
    @Json(name = "active")
    active,
    @Json(name = "closed")
    closed
}

data class GitLabPipeline(
        val id: Int,
        val ref: String,
        val sha: String,
        val status: GitLabPipelineStatus,
        @Json(name="web_url")
    val webUrl: String
)

enum class GitLabPipelineStatus {
    @Json(name = "cancelled")
    cancelled,
    @Json(name = "failed")
    failed,
    @Json(name = "pending")
    pending,
    @Json(name = "running")
    running,
    @Json(name = "skipped")
    skipped,
    @Json(name = "success")
    success
}

data class GitLabUser(
    @Json(name="avatar_url")
    val avatarUrl: String?,
    val id: Int,
    val name: String,
    val state: GitLabUserState,
    val username: String,
    @Json(name="web_url")
    val webUrl: String
)

enum class GitLabUserState {
    @Json(name = "active")
    active,
    @Json(name = "blocked")
    blocked
}