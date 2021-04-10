---
title: Plugin Developement
subtitle: How to build a plugin for Danger Kotlin
layout: guide_kt
order: 2
blurb: How do i make a plugin for Danger Kotlin?
---

## Introduction

Danger provides an sdk for the plugin development. This sdk works with the principle of dependency inversion,
that means you will be able to use the danger kotlin interfaces without import danger directly in your project.

Everything you need is just IntelliJ IDEA and the kotlin environment setup on your machine.

## Setup

**Step 1: Create a new project**. From IntelliJ IDEA create a new Project `File -> New -> Project` and choose a `Gradle` project with `Java` and `Koltin/JVM`

**Step 2: Add the sdk as dependency**. In your `build.gradle` add the following dependency:
```groovy
dependencies {
    implementation "systems.danger:danger-kotlin-sdk:1.2"
}
```

**Step 3: Create your main plugin class**. Create your plugin class as follow:
```kotlin
package com.test.myawesomeplugin

import systems.danger.kotlin.sdk.DangerPlugin

object MyAwesomeDangerPlugin : DangerPlugin() {
    override val id: String
            get() = this.javaClass.name
    
    fun helloPlugin() {
        context.message("Hello Danger Plugin!")
    }
}
```

**Step 4: Build your plugin**. You can copy manually your compiled jar into `/usr/local/lib/danger/libs` or alternatively use the gradle plugin as follow:
```groovy
buildscript {
    repositories {
        mavenLocal()
    }

    dependencies {
        classpath "systems.danger:danger-plugin-installer:0.1-alpha"
    }
}

apply plugin: 'danger-kotlin-plugin-installer'

group 'com.test'
version '0.0.1'

dangerPlugin {
    outputJar = "${buildDir}/libs/my-awesome-plugin-0.0.1.jar"
}
```
then invoke `./gradlew build` and `./gradlew installDangerPlugin`.

**Step 5: Try your plugin with Danger**. Create your `Dangerfile.df.kts` and import your plugin.
```kotlin
@file:DependsOn("my-awesome-plugin-0.0.1.jar")

import com.test.myawesomeplugin.MyAwesomePlugin
import systems.danger.kotlin.*

register plugin MyAwesomePlugin

val danger = Danger(args)

MyAwesomePlugin.helloPlugin()
``` 
If danger is setup correctly you should see a message on your PR: `Hello Danger Plugin!`

**Step 5: Maven publication**. Publish your plugin to maven central and add your plugin in [awesome-danger]. Then your `Dangerfile.df.kts` will be like:
```kotlin
@file:DependsOn("com.test:my-awesome-plugin:version")
//You can add more than one maven repository
@file:Repository("http://url.to.maven.repo/repository")

import com.test.myawesomeplugin.MyAwesomePlugin
import systems.danger.kotlin.*

register plugin MyAwesomePlugin

val danger = Danger(args)

MyAwesomePlugin.helloPlugin()
```

## Resources

Some plugins can be found in [awesome-danger] and an example plugin is also available [here]

[awesome-danger]: https://github.com/danger/awesome-danger#kotlin-danger-kotlin
[here]: https://github.com/danger/kotlin/danger-kotlin-sample-plugin
