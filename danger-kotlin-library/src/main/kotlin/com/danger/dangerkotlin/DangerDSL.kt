package com.danger.dangerkotlin

import com.squareup.moshi.Json


data class DSL(
        val danger: DangerDSL
)

data class DangerDSL(
        @Json(name ="github")
        private val _github: GitHub?,
        @Json(name ="bitbucket_server")
        private val _bitBucketServer: BitBucketServer?,
        val git: Git
) {
        val github: GitHub
                get() = _github!!
        val bitBucketServer: BitBucketServer
                get() = _bitBucketServer!!

        val onGitHub
                get() = _github != null
        val onBitBucketServer
                get() = _bitBucketServer != null
}
