package systems.danger.kotlin

import systems.danger.kotlin.models.bitbucket.BitBucketServer
import systems.danger.kotlin.models.danger.DangerDSL
import systems.danger.kotlin.models.git.Git
import systems.danger.kotlin.models.github.GitHub
import systems.danger.kotlin.models.gitlab.GitLab

// extensions over [DangerDSL] object

inline fun DangerDSL.onGitHub(onGitHub: GitHub.() -> Unit) {
    if (this.onGitHub) {
        github.run(onGitHub)
    }
}

inline fun DangerDSL.onGitLab(onGitLab: GitLab.() -> Unit) {
    if (this.onGitLab) {
        gitlab.run(onGitLab)
    }
}

inline fun DangerDSL.onBitBucket(onBitBucket: BitBucketServer.() -> Unit) {
    if (this.onBitBucketServer) {
        bitBucketServer.run(onBitBucket)
    }
}

inline fun DangerDSL.onGit(onGit: Git.() -> Unit) {
    git.run(onGit)
}
