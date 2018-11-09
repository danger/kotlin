package com.danger.dangerkt.lib

data class DSL(
        val danger: DangerDSL
)

data class DangerDSL(
        val github: GitHub,
        val git: Git
)