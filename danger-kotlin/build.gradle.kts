plugins {
    kotlin("multiplatform")
}

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    val targetOS: String by project
    val buildTarget = if (project.hasProperty("targetOS")) {
        when (val osName = targetOS) {
            "macosX64" -> macosX64("runner")
            "linuxX64" -> linuxX64("runner")
            "macosArm64" -> macosArm64("runner")
            "mingwX64" -> mingwX64("runner")
            else -> throw GradleException("OS '$osName' is not supported.")
        }
    } else {
        when (val osName = System.getProperty("os.name")) {
            "Mac OS X" -> macosX64("runner")
            "Linux" -> linuxX64("runner")
            "Mac OS X Apple silicon" -> macosArm64("runner")
            "Windows 11" -> mingwX64("runner")
            else -> throw GradleException("OS '$osName' is not supported.")
        }
    }

    buildTarget.apply {
        binaries {
            executable()
        }
    }

    sourceSets {
        val runnerMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val runnerTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}
