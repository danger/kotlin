package com.danger.dangerkotlin

import com.squareup.moshi.Json


data class DSL(
        val danger: DangerDSL
)

data class DangerDSL(
        val github: GitHub?,
        @Json(name ="bitbucket_server")
        val bitBucketServer: BitBucketServer?,
        val git: Git
) {
        val onGitHub
                get() = github != null
        val onBitBucketServer
                get() = bitBucketServer != null
}
