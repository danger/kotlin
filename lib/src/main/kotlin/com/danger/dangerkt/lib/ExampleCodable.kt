package com.danger.dangerkt.lib

import com.google.gson.annotations.SerializedName

data class ExampleCodable(
    @SerializedName("animal") val animal: String,
    @SerializedName("greetings") val greetings: String
)