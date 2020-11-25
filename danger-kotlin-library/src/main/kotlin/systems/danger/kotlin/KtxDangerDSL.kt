package systems.danger.kotlin

import systems.danger.kotlin.models.bitbucket.BitBucketServer
import systems.danger.kotlin.models.danger.DangerDSL
import systems.danger.kotlin.models.git.Git
import systems.danger.kotlin.models.github.GitHub
import systems.danger.kotlin.models.gitlab.GitLab

// extensions over [DangerDSL] object

/**
 * Execute the block only if danger is running on GitHub
 * Example code:
 * ```
 * danger(args) {
 *     onGitHub {
 *         ...
 *     }
 * }
 * ```
 *
 * @param onGitHub the block
 * @receiver the [GitHub] descriptor
 */
inline fun DangerDSL.onGitHub(onGitHub: GitHub.() -> Unit) {
    if (this.onGitHub) {
        github.run(onGitHub)
    }
}

/**
 * Execute the block only if danger is running on GitLab
 * Example code:
 * ```
 * danger(args) {
 *     onGitLab {
 *         ...
 *     }
 * }
 * ```
 *
 * @param onGitLab the block
 * @receiver the [GitLab] descriptor
 */
inline fun DangerDSL.onGitLab(onGitLab: GitLab.() -> Unit) {
    if (this.onGitLab) {
        gitlab.run(onGitLab)
    }
}

/**
 * Execute the block only if danger is running on BitBucket
 * Example code:
 * ```
 * danger(args) {
 *     onBitBucket {
 *         ...
 *     }
 * }
 * ```
 *
 * @param onBitBucket the block
 * @receiver the [BitBucketServer] descriptor
 */
inline fun DangerDSL.onBitBucket(onBitBucket: BitBucketServer.() -> Unit) {
    if (this.onBitBucketServer) {
        bitBucketServer.run(onBitBucket)
    }
}

/**
 * Execute a [Git] block
 * Example code:
 * ```
 * danger(args) {
 *     onGit {
 *         ...
 *     }
 * }
 * ```
 *
 * @param onGit the block
 * @receiver the [Git] descriptor
 */
inline fun DangerDSL.onGit(onGit: Git.() -> Unit) {
    git.run(onGit)
}
