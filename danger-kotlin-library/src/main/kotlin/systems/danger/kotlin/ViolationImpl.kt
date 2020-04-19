package systems.danger.kotlin

import systems.danger.kotlin.sdk.Violation

class ViolationImpl(override val message: String): Violation {
    override var file: String? = null
    override var line: Int? = null

    constructor(message: String, file: String, line: Int): this(message) {
        this.file = file
        this.line = line
    }
}