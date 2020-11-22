package systems.danger.kotlin

import kotlinx.serialization.*

typealias FilePath = String

/**
 * The git specific metadata for a pull request
 *
 * @property modifiedFiles Modified file paths relative to the git root.
 * @property createdFiles Newly created file paths relative to the git root.
 * @property deletedFiles Removed file paths relative to the git root.
 */
@Serializable
data class Git(
    @SerialName("modified_files") val modifiedFiles: List<FilePath>,
    @SerialName("created_files") val createdFiles: List<FilePath>,
    @SerialName("deleted_files") val deletedFiles: List<FilePath>,
    @SerialName("commits") val commits: List<GitCommit>
)

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
@Serializable
data class GitCommit(
    val sha: String? = null,
    val author: GitCommitAuthor,
    val committer: GitCommitAuthor,
    val message: String,
    val parents: List<String>? = null,
    val url: String
)

/**
 * A platform agnostic reference to a git commit.
 *
 * @property name The display name for the author.
 * @property email The email for the author.
 * @property date The ISO8601 date string for the commit.
 */
@Serializable
data class GitCommitAuthor(
        val name: String,
        val email: String,
        val date: String
)