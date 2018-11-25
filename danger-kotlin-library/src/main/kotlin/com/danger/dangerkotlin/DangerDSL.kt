package com.danger.dangerkotlin

import com.google.gson.annotations.SerializedName

data class DSL(
        val danger: DangerDSL
)

data class DangerDSL(
        val github: GitHub,
        @SerializedName("bitbucket_server")
        val bitbucketServer: BitBucketServer,
        val git: Git
)