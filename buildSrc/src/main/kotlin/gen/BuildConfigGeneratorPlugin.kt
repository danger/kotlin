package gen

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import javax.inject.Inject

class BuildConfigGeneratorPlugin : Plugin<Project> {
    private companion object {
        private const val BLOCK_NAME = "buildConfig"
        private const val PACKAGE_NAME = "com.danger.runner"
        private const val FILENAME = "BuildConfig"
    }

    private val buildConfigBuilder = FileSpec.builder(PACKAGE_NAME, FILENAME)

    private fun generateBuildConfig(project: Project, to: File) {
        buildConfigBuilder
            .addType(
                TypeSpec.objectBuilder(FILENAME)
                    .addProperties(
                        project.extensions
                            .getByType(BuildConfigExtension::class.java).fields.toPropertySpecList()
                    )
                    .build()
            )
            .build()
            .writeTo(to)
    }

    override fun apply(project: Project) {
        val extension = project.extensions.create(BLOCK_NAME, BuildConfigExtension::class.java, project)
        configure(project, extension)
        project.task("generateBuildConfig").doFirst {
            generateBuildConfig(project, File(project.buildDir, "generated/source/main/kotlin"))
        }
    }

    private fun configure(project: Project, extension: BuildConfigExtension) {

    }

    open class BuildConfigExtension @Inject constructor(project: Project){
        val fields = mutableMapOf<String, FieldSpec>()

        @SuppressWarnings("unused")
        fun field(name: String, value: String) {
            fields[name] = FieldSpec(name, value)
        }
    }

    data class FieldSpec(
        val name: String,
        val value: String
    )

    fun MutableMap<String, FieldSpec>.toPropertySpecList() = this.values.map {
        PropertySpec.builder(it.name, String::class)
            .initializer("%S", it.value)
            .build()
    }
}