package com.danger.dangerkt.runner

data class DSL(
        val danger: DangerDSL
)

data class DangerDSL(
        val github: GitHub,
        val git: Git
)