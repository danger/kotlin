package com.danger.dangerkotlin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

typealias FilePath = String

/**
 * The git specific metadata for a pull request
 *
 * @property modifiedFiles Modified file paths relative to the git root.
 * @property createdFiles Newly created file paths relative to the git root.
 * @property deletedFiles Removed file paths relative to the git root.
 */
@JsonClass(generateAdapter = true)
data class Git(
    @Json(name = "modified_files") val modifiedFiles: Array<FilePath>,
    @Json(name = "created_files") val createdFiles: Array<FilePath>,
    @Json(name = "deleted_files") val deletedFiles: Array<FilePath>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Git

        if (!modifiedFiles.contentEquals(other.modifiedFiles)) return false
        if (!createdFiles.contentEquals(other.createdFiles)) return false
        if (!deletedFiles.contentEquals(other.deletedFiles)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = modifiedFiles.contentHashCode()
        result = 31 * result + createdFiles.contentHashCode()
        result = 31 * result + deletedFiles.contentHashCode()
        return result
    }
}

/**
 * A platform agnostic reference to a git commit.
 *
 * @property sha The SHA for the commit.
 * @property author Who wrote the commit.
 * @property committer Who shipped the code.
 * @property message The message for the commit.
 * @property parents SHAs for the commit's parents.
 * @property url The URL for the commit.
 */
@JsonClass(generateAdapter = true)
data class GitCommit(
    val sha: String?,
    val author: GitCommitAuthor,
    val committer: GitCommitAuthor,
    val message: String,
    val parents: Array<String>?,
    val url: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GitCommit

        if (sha != other.sha) return false
        if (author != other.author) return false
        if (committer != other.committer) return false
        if (message != other.message) return false
        if (parents != null) {
            if (other.parents == null) return false
            if (!parents.contentEquals(other.parents)) return false
        } else if (other.parents != null) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sha.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + committer.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + (parents?.contentHashCode() ?: 0)
        result = 31 * result + url.hashCode()
        return result
    }
}

/**
 * A platform agnostic reference to a git commit.
 *
 * @property name The display name for the author.
 * @property email The email for the author.
 * @property date The ISO8601 date string for the commit.
 */
@JsonClass(generateAdapter = true)
data class GitCommitAuthor(
        val name: String,
        val email: String,
        val date: String
)