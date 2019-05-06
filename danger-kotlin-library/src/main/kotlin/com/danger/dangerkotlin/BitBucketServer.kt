package com.danger.dangerkotlin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The BitBucket server data for your pull request.
 * @property metadata The pull request and repository metadata
 * @property pullRequest The pull request metadata
 * @property commits The commits associated with the pull request
 * @property comments The comments on the pull request
 * @property activities The activities such as OPENING, CLOSING, MERGING or UPDATING a pull request
*/
@JsonClass(generateAdapter = true)
data class BitBucketServer(
        val metadata: BitBucketServerMetadata,
        @Json(name = "pr")
        val pullRequest: BitBucketServerPR,
        val commits: Array<BitBucketServerCommit>,
        val comments: Array<BitBucketServerComment>,
        val activities: Array<BitBucketServerActivity>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BitBucketServer

        if (metadata != other.metadata) return false
        if (pullRequest != other.pullRequest) return false
        if (!commits.contentEquals(other.commits)) return false
        if (!comments.contentEquals(other.comments)) return false
        if (!activities.contentEquals(other.activities)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = metadata.hashCode()
        result = 31 * result + pullRequest.hashCode()
        result = 31 * result + commits.contentHashCode()
        result = 31 * result + comments.contentHashCode()
        result = 31 * result + activities.contentHashCode()
        return result
    }
}

/**
 * Defines and activity such as OPENING, CLOSING, MERGING or UPDATING a pull request
 * @property id The activity's ID
 * @property createdAt Date activity created as number of mili seconds since the unix epoch
 * @property user The user that triggered the activity.
 * @property action The action the activity describes (e.g. "COMMENTED").
 * @property commentAction In case the action was "COMMENTED" it will state the command specific action (e.g. "CREATED").
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerActivity(
        val id: Int,
        @Json(name = "createdDate")
        val createdAt: Long,
        val user: BitBucketServerUser,
        val action: String,
        val commentAction: String?
)

/**
 * The pull request and repository metadata
 * @property pullRequestId The PR's ID
 * @property repoSlug The complete repo slug including project slug.
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerMetadata internal constructor(
    val pullRequestID: String,
    val repoSlug: String
)

@JsonClass(generateAdapter = true)
internal data class BitBucketServerEnv(
    val pr: String,
    val repo: String
)

/**
 * A comment on the pull request
 * @property id The comment's id.
 * @property createdAt Date comment created as number of mili seconds since the unix epoch.
 * @property user The comment's author.
 * @property action The action the user did (e.g. "COMMENTED").
 * @property fromHash The SHA to which the comment was created.
 * @property previousFromHash The previous SHA to which the comment was created.
 * @property toHash The next SHA after the comment was created.
 * @property previousToHash The SHA to which the comment was created.
 * @property commentAction Action the user did (e.g. "ADDED") if it is a new task.
 * @property comment Detailed data of the comment.
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerComment(
    val id: Int,
    @Json(name = "createdDate")
    val createdAt: Long,
    val user: BitBucketServerUser,
    val action: String,
    val fromHash: String?,
    val previousFromHash: String?,
    val toHash: String?,
    val previousToHash: String?,
    val commentAction: String?,
    val comment: BitBucketServerCommentDetail?
)

/**
 * Detailed data of the comment.
 * @property id The comment's id.
 * @property version The comment's version.
 * @property text The comment content.
 * @property author The author of the comment.
 * @property createdAt Date comment created as number of milliseconds since the unix epoch.
 * @property updatedAt Date comment updated as number of milliseconds since the unix epoch.
 * @property comments Replys to the comment
 * @property properties Properties associated with the comment
 * @property tasks Tasks associated with the comment
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerCommentDetail(
    val id: Int,
    val version: Int,
    val text: String,
    val author: BitBucketServerUser,
    @Json(name = "createdDate")
    val createdAt: Long,
    @Json(name = "updatedDate")
    val updatedAt: Long,
    val comments: Array<BitBucketServerCommentDetail>,
    val properties: BitBucketServerCommentInnerProperties,
    val tasks: Array<BitBucketServerCommentTask>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BitBucketServerCommentDetail

        if (id != other.id) return false
        if (version != other.version) return false
        if (text != other.text) return false
        if (author != other.author) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (!comments.contentEquals(other.comments)) return false
        if (properties != other.properties) return false
        if (!tasks.contentEquals(other.tasks)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + version
        result = 31 * result + text.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + comments.contentHashCode()
        result = 31 * result + properties.hashCode()
        result = 31 * result + tasks.contentHashCode()
        return result
    }
}

/**
 * Task associated with a comment
 * @property id The tasks ID
 * @property createdAt Date activity created as number of milliseconds since the unix epoch
 * @property text The text of the task
 * @property state The state of the task (e.g. "OPEN")
 * @property author The author of the comment
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerCommentTask(
        val id: Int,
        @Json(name = "createdDate")
        val createdAt: Long,
        val text: String,
        val state: String,
        val author: BitBucketServerUser
)

/**
 * Properties associated with a comment
 * @property repositoryId The ID of the repo
 * @property issues Slugs of linkd Jira issues
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerCommentInnerProperties(
        val repositoryId: Int,
        val issues: Array<String>?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BitBucketServerCommentInnerProperties

        if (repositoryId != other.repositoryId) return false
        if (issues != null) {
            if (other.issues == null) return false
            if (!issues.contentEquals(other.issues)) return false
        } else {
            return other.issues != null
        }

        return true
    }

    override fun hashCode(): Int {
        var result = repositoryId
        result = 31 * result + (issues?.contentHashCode() ?: 0)
        return result
    }
}

/**
 * A BitBucket server commit
 * @property id The SHA for the commit.
 * @property displayId The shortened SHA for the commit.
 * @property author The author of the commit, assumed to be the person who wrote the code.
 * @property authorTimestamp The UNIX timestamp for when the commit was authored.
 * @property committer The author of the commit, assumed to be the person who commited/merged the code into a project.
 * @property committerTimestamp When the commit was commited to the project
 * @property message The commit's message
 * @property parents The commit's parents
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerCommit(
        val id: String,
        val displayId: String,
        val author: BitBucketServerUser,
        val authorTimestamp: Long,
        val committer: BitBucketServerUser?,
        val committerTimestamp: Long,
        val message: String,
        val parents: Array<BitBucketServerCommitParent>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BitBucketServerCommit

        if (id != other.id) return false
        if (displayId != other.displayId) return false
        if (author != other.author) return false
        if (authorTimestamp != other.authorTimestamp) return false
        if (committer != other.committer) return false
        if (committerTimestamp != other.committerTimestamp) return false
        if (message != other.message) return false
        if (!parents.contentEquals(other.parents)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + displayId.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + authorTimestamp.hashCode()
        result = 31 * result + (committer?.hashCode() ?: 0)
        result = 31 * result + committerTimestamp.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + parents.contentHashCode()
        return result
    }
}

/**
 * A commit's parent
 * @property id The SHA for the commit
 * @property displayId The shortened SHA for the commit
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerCommitParent(
        val id: String,
        val displayId: String
)

/**
 * The BitBucketServer PR data
 * @property id The PR's ID.
 * @property version The API version.
 * @property title Title of the pull request.
 * @property description The description of the PR.
 * @property state The pull request's current status.
 * @property open Is the PR open?
 * @property closed Is the PR closed?
 * @property createdAt Date PR created as number of milliseconds since the unix epoch.
 * @property updatedAt Date PR updated as number of milliseconds since the unix epoch.
 * @property fromRef The PR submittor's reference
 * @property toRef The repo Danger is running on
 * @property isLocked Is the PR locked?
 * @property author The creator of the PR
 * @property reviewers People requested as reviewers
 * @property participants People who have participated in the PR
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerPR(
        val id: Int,
        val version: Int,
        val title: String,
        val description: String?,
        val state: String,
        val open: Boolean,
        val closed: Boolean,
        @Json(name = "createdDate")
        val createdAt: Long,
        @Json(name = "updatedDate")
        val updatedAt: Long,
        val fromRef: BitBucketServerMergeRef,
        val toRef: BitBucketServerMergeRef,
        @Json(name = "locked")
        val isLocked: Boolean,
        val author: BitBucketServerParticipant,
        val reviewers: Array<BitBucketServerReviewer>,
        val participants: Array<BitBucketServerParticipant>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BitBucketServerPR

        if (id != other.id) return false
        if (version != other.version) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (state != other.state) return false
        if (open != other.open) return false
        if (closed != other.closed) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (fromRef != other.fromRef) return false
        if (toRef != other.toRef) return false
        if (isLocked != other.isLocked) return false
        if (author != other.author) return false
        if (!reviewers.contentEquals(other.reviewers)) return false
        if (!participants.contentEquals(other.participants)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + version
        result = 31 * result + title.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + state.hashCode()
        result = 31 * result + open.hashCode()
        result = 31 * result + closed.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + fromRef.hashCode()
        result = 31 * result + toRef.hashCode()
        result = 31 * result + isLocked.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + reviewers.contentHashCode()
        result = 31 * result + participants.contentHashCode()
        return result
    }
}

@JsonClass(generateAdapter = true)
data class BitBucketServerParticipant(
        val user: BitBucketServerUser
)

/**
 * The BitBucketServer PR data
 * @property user The BitBucket Server user.
 * @property approved The approval status.
 * @property lastReviewedCommit The commit SHA for the latest commit that was reviewed.
*/
@JsonClass(generateAdapter = true)
data class BitBucketServerReviewer(
    val user: BitBucketServerUser,
    val approved: Boolean,
    val lastReviewedCommit: String?
)

/**
 * A BitBucketServer branch reference
 * @property id The branch name
 * @property displayId The human readable branch name
 * @property latestCommit The SHA for the latest commit
 * @property repository Info of the associated repository
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerMergeRef(
        val id: String,
        val displayId: String,
        val latestCommit: String,
        val repository: BitBucketServerRepo
)

/**
 * The repository associated to a commit
 * @property name The repo name
 * @property slug The slug for the repo
 * @property scmId The type of SCM tool, probably "git"
 * @property isPublic Is the repo public?
 * @property forkable Can someone fork thie repo?
 * @property project An abtraction for grouping repos
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerRepo(
        val name: String?,
        val slug: String,
        val scmId: String,
        @Json(name = "public")
        val isPublic: Boolean,
        val forkable: Boolean,
        val project: BitBucketServerProject
)

/**
 * An abtraction for grouping repos
 * @property id The project unique id.
 * @property key The project's human readable project key.
 * @property name The name of the project.
 * @property isPublic Is the project publicly available
 * @property type The project's type
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerProject(
    val id: Int,
    val key: String,
    val name: String,
    @Json(name = "public")
    val isPublic: Boolean,
    val type: String
)

/**
 * The BitBucket Server user
 * @property id The unique user ID
 * @property name The name of the user
 * @property displayName The name to use when referencing the user
 * @property emailAddress The email for the user
 * @property active Is the account active
 * @property slug The user's slug for URLs
 * @property type The type of a user, "NORMAL" being a typical user3
 */
@JsonClass(generateAdapter = true)
data class BitBucketServerUser(
        val id: Int?,
        val name: String,
        val displayName: String?,
        val emailAddress: String,
        val active: Boolean?,
        val slug: String?,
        val type: String?
)