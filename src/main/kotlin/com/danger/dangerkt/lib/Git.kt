package com.danger.dangerkt.lib

import com.google.gson.annotations.SerializedName

typealias File = String

data class Git(
    @SerializedName("modified_files") val modifiedFiles: Array<File>,
    @SerializedName("created_files") val createdFiles: Array<File>,
    @SerializedName("deleted_files") val deletedFiles: Array<File>
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

data class GitCommit(
    val sha: String,
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

data class GitCommitAuthor(
        val name: String,
        val email: String,
        val date: String
)