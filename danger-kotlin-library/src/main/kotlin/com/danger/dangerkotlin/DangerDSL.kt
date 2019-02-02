package com.danger.dangerkotlin

import com.google.gson.annotations.SerializedName

data class DSL(
        val danger: DangerDSL
)

data class DangerDSL(
        val github: GitHub,
        @SerializedName("bitbucket_server")
        val bitBucketServer: BitBucketServer,
        val git: Git
) {
        fun onGithub() : Boolean {
                return github != null
        }

        fun onBitBucketServer() : Boolean {
                return bitBucketServer != null
        }
}
