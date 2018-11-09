package com.danger.dangerkt.lib

import com.danger.dangerkt.lib.ExampleCodable
import com.google.gson.Gson


fun main(args: Array<String>) {
    val coddabile = ExampleCodable("cavallo", "ti coddiri")
    val gson = Gson()
    System.out.println(gson.toJson(coddabile))
}