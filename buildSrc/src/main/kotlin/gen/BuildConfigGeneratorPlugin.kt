package gen

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class BuildConfigGeneratorPlugin : Plugin<Project> {
    private companion object {
        private const val PACKAGE_NAME = "com.danger.config"
        private const val FILENAME = "BuildConfig"
    }

    private val buildConfigBuilder = FileSpec.builder(PACKAGE_NAME, FILENAME)

    private val properties = listOf(
        PropertySpec.builder("VERSION", String::class.java)
            .initializer("%S", "1.0.0")
            .build()
    )

    private val buildConfigClassType = TypeSpec.objectBuilder(FILENAME)
        .addProperties(properties)
        .build()

    fun generateBuildConfig(to: File) {
        buildConfigBuilder
            .addType(buildConfigClassType)
            .build()
            .writeTo(to)
    }

    override fun apply(project: Project) {
        project.task("generateBuildConfig").doFirst {
            generateBuildConfig(File(project.buildDir, "generated/source/main/kotlin"))
        }
    }
}