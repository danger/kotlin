#!/usr/bin/env kscript

//DEPS com.danger:danger-kt:1.0-SNAPSHOT
@file:CompilerOpts("-jvm-target 1.8")

import com.danger.dangerkt.lib.*

val danger = Danger(args)

fail("Failure")

