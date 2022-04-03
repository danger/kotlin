package systems.danger.kotlin

import systems.danger.kotlin.models.gitlab.GitLab
import java.security.MessageDigest

// extensions over [GitLab] object

val GitLab.projectUrl: String
    get() = mergeRequest.webUrl.split("/merge_requests").first()

fun GitLab.toWebUrl(filePath: String) = "$projectUrl/blob/${mergeRequest.sha}/${filePath}"

fun GitLab.toWebDiffUrl(filePath: String) = "${mergeRequest.webUrl}/diffs#diff-content-${filePath.sha1}"

val String.sha1: String
    get() = MessageDigest
        .getInstance("SHA-1")
        .digest(toByteArray())
        .joinToString(separator = "") { "%02x".format(it) }
