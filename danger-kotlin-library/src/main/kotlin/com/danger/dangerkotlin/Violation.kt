package com.danger.dangerkotlin

class Violation(message: String) {
    val message: String
    var file: String? = null
    var line: Int? = null

    init {
        this.message = message
    }

    constructor(message: String, file: String, line: Int): this(message) {
        this.file = file
        this.line = line
    }
}