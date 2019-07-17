import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    groovy
    kotlin("jvm") version "1.3.41"
}

version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation(kotlin("stdlib-jdk8"))
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}