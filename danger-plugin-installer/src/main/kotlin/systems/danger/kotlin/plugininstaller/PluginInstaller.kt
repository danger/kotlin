package systems.danger.kotlin.plugininstaller

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

open class PluginInstallerExtension {
    var outputJar: String? = null
}

@SuppressWarnings("unused")
class PluginInstaller : Plugin<Project> {
    override fun apply(target: Project) {
        val extension = target.extensions.create("dangerPlugin", PluginInstallerExtension::class.java)
        val dangerLibDir = File("/usr/local/lib/danger/libs")

        target.tasks.create("installDangerPlugin") {
            it.doLast {
                extension.outputJar?.let { outputJar ->
                    val compiledJar = File(outputJar).takeIf { jar -> jar.isFile && jar.exists() }
                    compiledJar?.let { jar ->
                        jar.run {
                            copyTo(File(dangerLibDir, jar.name), true)
                        }
                    }
                }
            }
        }
    }
}