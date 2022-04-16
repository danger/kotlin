@file:UseSerializers(DateSerializer::class)

package systems.danger.kotlin.models.bitbucket

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import systems.danger.kotlin.models.serializers.DateSerializer

/**
 * The BitBucket server data for your pull request.
 * @property metadata The pull request and repository metadata
 * @property pullRequest The pull request metadata
 * @property commits The commits associated with the pull request
 * @property comments The comments on the pull request
 * @property activities The activities such as OPENING, CLOSING, MERGING or UPDATING a pull request
 */
@Serializable
data class BitBucketCloud(
    val metadata: BitBucketMetadata,
    @SerialName("pr")
    val pullRequest: PullRequest,
    val commits: List<Commit>,
    val comments: List<Comment>,
    val activities: List<Activity>
) {

    /**
     * Defines and activity such as OPENING, CLOSING, MERGING or UPDATING a pull request
     * @property comment The activity's ID
     */
    @Serializable
    data class Activity(
        val comment: Comment? = null
    )

    /**
     * Defines and activity such as OPENING, CLOSING, MERGING or UPDATING a pull request
     * @property comment The activity's ID
     */
    @Serializable
    data class Comment(
        val id: Int,
        val content: Content,
        @SerialName("created_on")
        val createdOn: Instant,
        @SerialName("updated_on")
        val updatedOn: Instant,
        val deleted: Boolean,
        val user: User,
    )

    /**
     * Defines and activity such as OPENING, CLOSING, MERGING or UPDATING a pull request
     * @property comment The activity's ID
     */
    @Serializable
    data class Content(
        val html: String,
        val markup: String,
        val raw: String
    )

    /**
     * Defines and activity such as OPENING, CLOSING, MERGING or UPDATING a pull request
     * @property comment The activity's ID
     */
    @Serializable
    data class User(
        val uuid: String,
        @SerialName("account_id")
        val accountId: String? = null,
        @SerialName("display_name")
        val displayName: String? = null,
        val nickname: String? = null
    )

    @Serializable
    data class Commit(
        val hash: String,
        val author: Author,
        val date: Instant,
        val message: String
    ) {

        @Serializable
        data class Author(
            val raw: String,
            val user: User
        )
    }

    @Serializable
    data class PullRequest(
        val id: Int,
        val title: String,
        val author: User,
        val description: String,
        @SerialName("close_source_branch")
        val closeSourceBranch : Boolean,
        @SerialName("created_on")
        val createdOn: Instant,
        @SerialName("updated_on")
        val updatedOn: Instant,
        @SerialName("task_count")
        val taskCount: Int,
        @SerialName("comment_count")
        val commentCount: Int,
        val participants: List<Participant>,
        val reviewers: List<User>,
        val source: MergeRef,
        val destination: MergeRef,
        val state: State,
        val summary: Content,
    ) {
        @Serializable
        enum class State {
            OPEN, MERGED, SUPERSEDED, DECLINED
        }

        @Serializable
        data class Participant(
            val approved: Boolean,
            val role: Role,
            val user: User
        ) {

            @Serializable
            enum class Role {
                REVIEWER, PARTICIPANT
            }
        }
    }

    @Serializable
    data class MergeRef(
        val branch: Branch,
        val commit: Commit,
        val repository: Repo
    ) {

        @Serializable
        data class Branch(
            val name: String
        )

        @Serializable
        data class Commit(
            val hash: String
        )
    }

    @Serializable
    data class Repo(
        val uuid: String,
        val name: String,
        @SerialName("full_name")
        val fullName: String
    )
}