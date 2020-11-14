@file:UseSerializers(DateSerializer::class)

package systems.danger.kotlin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import systems.danger.kotlin.serializers.DateSerializer
import java.util.*

@Serializable
data class GitLab(
    @SerialName("mr")
    val mergeRequest: GitLabMergeRequest,
    val metadata: GitLabMetadata
)

@Serializable
data class GitLabDiffRefs(
    @SerialName("base_sha")
    val baseSha: String,
    @SerialName("head_sha")
    val headSha: String,
    @SerialName("start_sha")
    val startSha: String
)

@Serializable
data class GitLabUserMergeData(
    @SerialName("can_merge")
    val canMerge: Boolean
)

@Serializable
data class GitLabMergeRequest(
    @SerialName("allow_collaboration")
    val allowCollaboration: Boolean?,
    @SerialName("allow_maintainer_to_push")
    val allowMaintainerToPush: Boolean?,
    @SerialName("approvals_before_merge")
    val approvalsBeforeMerge: Int?,
    val assignee: GitLabUser?,
    val author: GitLabUser,
    @SerialName("changes_count")
    val changesCount: String,
    @SerialName("closed_at")

    val closedAt: Date? = null,
    @SerialName("closed_by")
    val closedBy: GitLabUser?,
    val description: String,
    @SerialName("diff_refs")
    val diffRefs: GitLabDiffRefs,
    val downvotes: Int,
    @SerialName("first_deployed_to_production_at")

    val firstDeployedToProductionAt: Date? = null,
    @SerialName("force_remove_source_branch")
    val forceRemoveSourceBranch: Boolean,
    val id: Int,
    val iid: Int,
    @SerialName("latest_build_finished_at")

    val latestBuildFinishedAt: Date? = null,
    @SerialName("latest_build_started_at")

    val latestBuildStartedAt: Date? = null,
    val labels: Array<String>,
    @SerialName("merge_commit_sha")
    val mergeCommitSha: String? = null,
    @SerialName("merged_at")
    val mergedAt: Date? = null,
    @SerialName("merged_by")
    val mergedBy: GitLabUser?,
    @SerialName("merge_when_pipeline_succeeds")
    val mergeOnPipelineSuccess: Boolean,
    val milestone: GitLabMilestone? = null,
    val pipeline: GitLabPipeline,
    @SerialName("project_id")
    val projectId: String,
    val sha: String,
    @SerialName("should_remove_source_branch")
    val shouldRemoveSourceBranch: Boolean? = null,
    @SerialName("source_branch")
    val sourceBranch: String,
    @SerialName("source_project_id")
    val sourceProjectId: String,
    val state: GitLabMergeRequestState,
    val subscribed: Boolean,
    @SerialName("target_branch")
    val targetBranch: String,
    @SerialName("target_project_id")
    val targetProjectId: String,
    val timeStats: GitLabMergeRequestTimeStats? = null,
    val title: String,
    val upvotes: Int,
    @SerialName("user")
    private val userMergeData: GitLabUserMergeData,
    @SerialName("user_notes_count")
    val userNotesCount: Int,
    @SerialName("web_url")
    val webUrl: String,
    @SerialName("work_in_progress")
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

@Serializable
data class GitLabMergeRequestTimeStats(
    @SerialName("human_time_estimate")
    val humanTimeEstimate: Int?,
    @SerialName("human_time_spent")
    val humanTimeSpent: Int?,
    @SerialName("time_estimate")
    val timeEstimate: Int,
    @SerialName("total_time_spent")
    val totalTimeSpent: Int
)

@Serializable
data class GitLabMetadata(
    val pullRequestID: String,
    val repoSlug: String
)

@Serializable
enum class GitLabMergeRequestState {
    @SerialName("closed")
    closed,

    @SerialName("locked")
    locked,

    @SerialName("merged")
    merged,

    @SerialName("opened")
    opened
}

@Serializable
data class GitLabMilestone(
    @SerialName("created_at")

    val createdAt: Date,
    val description: String,
    @SerialName("due_date")

    val dueDate: Date,
    val id: Int,
    val iid: Int,
    @SerialName("project_id")
    val projectID: Int,
    @SerialName("start_date")

    val startDate: Date,
    val state: GitLabMilestoneState,
    val title: String,
    @SerialName("updated_at")

    val updatedAt: Date,
    @SerialName("web_url")
    val webUrl: String
)

@Serializable
enum class GitLabMilestoneState {
    @SerialName("active")
    active,

    @SerialName("closed")
    closed
}

@Serializable
data class GitLabPipeline(
    val id: Int,
    val ref: String,
    val sha: String,
    val status: GitLabPipelineStatus,
    @SerialName("web_url")
    val webUrl: String
)

@Serializable
enum class GitLabPipelineStatus {
    @SerialName("cancelled")
    cancelled,

    @SerialName("failed")
    failed,

    @SerialName("pending")
    pending,

    @SerialName("running")
    running,

    @SerialName("skipped")
    skipped,

    @SerialName("success")
    success
}

@Serializable
data class GitLabUser(
    @SerialName("avatar_url")
    val avatarUrl: String?,
    val id: Int,
    val name: String,
    val state: GitLabUserState,
    val username: String,
    @SerialName("web_url")
    val webUrl: String
)

@Serializable
enum class GitLabUserState {
    @SerialName("active")
    active,

    @SerialName("blocked")
    blocked
}