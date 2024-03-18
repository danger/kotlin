plugins {
    kotlin("multiplatform")
}

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */

    val targetOS: String by project
    val osName = if (project.hasProperty("targetOS")) {
        targetOS
    } else {
        System.getProperty("os.name")
    }

    val buildTarget = when (osName) {
        "Mac OS X" -> macosX64("runner")
        "Linux" -> linuxX64("runner")
        "Mac OS X Apple silicon" -> macosArm64("runner")
        "Windows 11" -> mingwX64("runner")
        else -> throw GradleException("OS '$osName' is not supported.") as Throwable
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