package systems.danger.kotlin.models.bitbucket

import kotlinx.serialization.Serializable

/**
 * The pull request and repository metadata
 * @property pullRequestId The PR's ID
 * @property repoSlug The complete repo slug including project slug.
 */
@Serializable
data class BitBucketMetadata internal constructor(
    val pullRequestID: String,
    val repoSlug: String
)