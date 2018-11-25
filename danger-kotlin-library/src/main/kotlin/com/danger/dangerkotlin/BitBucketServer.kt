package com.danger.dangerkotlin

import com.google.gson.annotations.SerializedName

data class BitBucketServer(
        val metadata: BitBucketServerMetadata,
        @SerializedName("pr")
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

data class BitBucketServerActivity(
        val id: Int,
        val createdDate: Int,
        val user: BitBucketServerUser,
        val action: String,
        val commentAction: String?
)

data class BitBucketServerMetadata(
        val pullRequestID: String,
        val repoSlug: String
)

data class BitBucketServerComment(
    val id: Int,
    val createdDate: Int,
    val user: BitBucketServerUser,
    val action: String,
    val fromHash: String?,
    val previousFromHash: String?,
    val commentAction: String?,
    val comment: BitBucketServerCommentDetail
)

data class BitBucketServerCommentDetail(
    val id: Int,
    val version: Int,
    val text: String,
    val author: BitBucketServerUser,
    val createdAt: Int,
    val updatedAt: Int,
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
        result = 31 * result + createdAt
        result = 31 * result + updatedAt
        result = 31 * result + comments.contentHashCode()
        result = 31 * result + properties.hashCode()
        result = 31 * result + tasks.contentHashCode()
        return result
    }
}

data class BitBucketServerCommentTask(
        val id: Int,
        val createdDate: Int,
        val text: String,
        val state: String,
        val author: BitBucketServerUser
)

data class BitBucketServerCommentInnerProperties(
        val repositoryId: Int,
        val issues: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BitBucketServerCommentInnerProperties

        if (repositoryId != other.repositoryId) return false
        if (!issues.contentEquals(other.issues)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = repositoryId.hashCode()
        result = 31 * result + issues.contentHashCode()
        return result
    }
}

data class BitBucketServerCommit(
        val id: String,
        val displayId: String,
        val author: BitBucketServerUser,
        val authorTimestamp: Int,
        val committer: BitBucketServerUser?,
        val committerTimestamp: Int,
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
        result = 31 * result + authorTimestamp
        result = 31 * result + (committer?.hashCode() ?: 0)
        result = 31 * result + committerTimestamp
        result = 31 * result + message.hashCode()
        result = 31 * result + parents.contentHashCode()
        return result
    }
}

data class BitBucketServerCommitParent(
        val id: String,
        val displayId: String
)

data class BitBucketServerPR(
        val id: Int,
        val version: Int,
        val title: String,
        val description: String?,
        val state: String,
        val open: Boolean,
        val closed: Boolean,
        @SerializedName("createdDate")
        val createdAt: Int,
        @SerializedName("updatedDate")
        val updatedAt: Int,
        @SerializedName("fromRef")
        val head: BitBucketServerMergeRef,
        @SerializedName("toRef")
        val base: BitBucketServerMergeRef,
        @SerializedName("locked")
        val isLocked: Boolean,
        val author: BitBucketServerAuthor,
        val reviewers: Array<BitBucketServerUser>,
        val participants: Array<BitBucketServerUser>
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
        if (head != other.head) return false
        if (base != other.base) return false
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
        result = 31 * result + createdAt
        result = 31 * result + updatedAt
        result = 31 * result + head.hashCode()
        result = 31 * result + base.hashCode()
        result = 31 * result + isLocked.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + reviewers.contentHashCode()
        result = 31 * result + participants.contentHashCode()
        return result
    }
}

data class BitBucketServerAuthor(
        val user: BitBucketServerUser
)



data class BitBucketServerMergeRef(
        val id: String,
        val displayId: String,
        val latestCommit: String,
        val repository: BitBucketServerRepo
)

data class BitBucketServerRepo(
        val name: String?,
        val slug: String,
        val scmId: String,
        @SerializedName("public")
        val isPublic: Boolean,
        val forkable: Boolean,
        val project: BitBucketServerProject
)

data class BitBucketServerProject(
    val id: Int,
    val key: String,
    val name: String,
    @SerializedName("public")
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
data class BitBucketServerUser(
        val id: Int,
        val name: String,
        val displayName: String,
        val emailAddress: String,
        val active: Boolean,
        val slug: String,
        val type: String
)