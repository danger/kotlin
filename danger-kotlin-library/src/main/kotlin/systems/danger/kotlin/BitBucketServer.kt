package systems.danger.kotlin

import kotlinx.serialization.*

/**
 * The BitBucket server data for your pull request.
 * @property metadata The pull request and repository metadata
 * @property pullRequest The pull request metadata
 * @property commits The commits associated with the pull request
 * @property comments The comments on the pull request
 * @property activities The activities such as OPENING, CLOSING, MERGING or UPDATING a pull request
 */
@Serializable
data class BitBucketServer(
    val metadata: BitBucketServerMetadata,
    @SerialName("pr")
    val pullRequest: BitBucketServerPR,
    val commits: List<BitBucketServerCommit>,
    val comments: List<BitBucketServerComment>,
    val activities: List<BitBucketServerActivity>
)

/**
 * Defines and activity such as OPENING, CLOSING, MERGING or UPDATING a pull request
 * @property id The activity's ID
 * @property createdAt Date activity created as number of milli seconds since the unix epoch
 * @property user The user that triggered the activity.
 * @property action The action the activity describes (e.g. "COMMENTED").
 * @property commentAction In case the action was "COMMENTED" it will state the command specific action (e.g. "CREATED").
 */
@Serializable
data class BitBucketServerActivity(
    val id: Int,
    @SerialName("createdDate")
    val createdAt: Long,
    val user: BitBucketServerUser,
    val action: String,
    val commentAction: String? = null
)

/**
 * The pull request and repository metadata
 * @property pullRequestId The PR's ID
 * @property repoSlug The complete repo slug including project slug.
 */
@Serializable
data class BitBucketServerMetadata internal constructor(
    val pullRequestID: String,
    val repoSlug: String
)

@Serializable
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
@Serializable
data class BitBucketServerComment(
    val id: Int,
    @SerialName("createdDate")
    val createdAt: Long,
    val user: BitBucketServerUser,
    val action: String,
    val fromHash: String? = null,
    val previousFromHash: String? = null,
    val toHash: String? = null,
    val previousToHash: String? = null,
    val commentAction: String? = null,
    val comment: BitBucketServerCommentDetail? = null
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

@Serializable
data class BitBucketServerCommentDetail(
    val id: Int,
    val version: Int,
    val text: String,
    val author: BitBucketServerUser,
    @SerialName("createdDate")
    val createdAt: Long,
    @SerialName("updatedDate")
    val updatedAt: Long,
    val comments: List<BitBucketServerCommentDetail>,
    val properties: BitBucketServerCommentInnerProperties,
    val tasks: List<BitBucketServerCommentTask>
)

/**
 * Task associated with a comment
 * @property id The tasks ID
 * @property createdAt Date activity created as number of milliseconds since the unix epoch
 * @property text The text of the task
 * @property state The state of the task (e.g. "OPEN")
 * @property author The author of the comment
 */
@Serializable
data class BitBucketServerCommentTask(
    val id: Int,
    @SerialName("createdDate")
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
@Serializable
data class BitBucketServerCommentInnerProperties(
    val repositoryId: Int,
    val issues: List<String> = listOf()
)

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
@Serializable
data class BitBucketServerCommit(
    val id: String,
    val displayId: String,
    val author: BitBucketServerUser,
    val authorTimestamp: Long,
    val committer: BitBucketServerUser? = null,
    val committerTimestamp: Long,
    val message: String,
    val parents: List<BitBucketServerCommitParent>
)

/**
 * A commit's parent
 * @property id The SHA for the commit
 * @property displayId The shortened SHA for the commit
 */
@Serializable
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
@Serializable
data class BitBucketServerPR(
    val id: Int,
    val version: Int,
    val title: String,
    val description: String? = null,
    val state: State,
    val open: Boolean,
    val closed: Boolean,
    @SerialName("createdDate")
    val createdAt: Long,
    @SerialName("updatedDate")
    val updatedAt: Long,
    val fromRef: BitBucketServerMergeRef,
    val toRef: BitBucketServerMergeRef,
    @SerialName("locked")
    val isLocked: Boolean,
    val author: BitBucketServerParticipant,
    val reviewers: List<BitBucketServerReviewer>,
    val participants: List<BitBucketServerParticipant>
) {
    @Serializable
    enum class State {
        OPEN, MERGED, SUPERSEDED, DECLINED
    }
}

@Serializable
data class BitBucketServerParticipant(
    val user: BitBucketServerUser
)

/**
 * The BitBucketServer PR data
 * @property user The BitBucket Server user.
 * @property approved The approval status.
 * @property lastReviewedCommit The commit SHA for the latest commit that was reviewed.
 */
@Serializable
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
@Serializable
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
@Serializable
data class BitBucketServerRepo(
    val name: String? = null,
    val slug: String,
    val scmId: String,
    @SerialName("public")
    val isPublic: Boolean,
    @SerialName("forkable")
    val isForkable: Boolean,
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
@Serializable
data class BitBucketServerProject(
    val id: Int,
    val key: String,
    val name: String,
    @SerialName("public")
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
@Serializable
data class BitBucketServerUser(
    val id: Int? = null,
    val name: String,
    val displayName: String? = null,
    val emailAddress: String,
    val active: Boolean = false,
    val slug: String? = null,
    val type: Type = Type.NORMAL
) {
    @Serializable
    enum class Type {
        NORMAL, SERVICE
    }
}