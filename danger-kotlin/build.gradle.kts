plugins {
    kotlin("multiplatform")
}

kotlin {
    /* Targets configuration omitted. 
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    val hostOs = System.getProperty("os.name")
    val hostTarget = when {
        hostOs == "Mac OS X" -> macosX64("runner")
        hostOs == "Linux" -> linuxX64("runner")
        else -> throw GradleException("Host OS '$hostOs' is not supported.")
    }

    hostTarget.apply {
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