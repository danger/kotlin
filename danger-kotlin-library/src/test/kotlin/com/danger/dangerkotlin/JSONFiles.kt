package com.danger.dangerkotlin

class JSONFiles {

    val dangerJSON = this.javaClass.classLoader.getResource("dangerDSL.json").readText()

}