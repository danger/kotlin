package systems.danger.kts

import org.jetbrains.kotlin.mainKts.*
import org.jetbrains.kotlin.script.util.CompilerOptions
import org.jetbrains.kotlin.script.util.DependsOn
import org.jetbrains.kotlin.script.util.Import
import org.jetbrains.kotlin.script.util.Repository
import java.io.File
import java.security.MessageDigest
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.ScriptingHostConfiguration
import kotlin.script.experimental.jvm.compilationCache
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.CompiledScriptJarsCache
import kotlin.script.experimental.jvmhost.jsr223.configureProvidedPropertiesFromJsr223Context
import kotlin.script.experimental.jvmhost.jsr223.importAllBindings
import kotlin.script.experimental.jvmhost.jsr223.jsr223

@Suppress("unused")
@KotlinScript(
    fileExtension = "df.kts",
    compilationConfiguration = DangerFileScriptDefinition::class,
    evaluationConfiguration = MainKtsEvaluationConfiguration::class
)
abstract class DangerFileScript(val args: Array<String>)

const val DF_COMPILED_SCRIPTS_CACHE_DIR_ENV_VAR = "DANGER_KOTLIN_DF_KTS_COMPILED_SCRIPTS_CACHE_DIR"
const val DF_COMPILED_SCRIPTS_CACHE_DIR_PROPERTY = "dangerkotlin.df.kts.compiled.scripts.cache.dir"

object DangerFileScriptDefinition  : ScriptCompilationConfiguration(
    {
        defaultImports(DependsOn::class, Repository::class, Import::class, CompilerOptions::class)
        jvm {
            dependenciesFromClassContext(DangerFileScriptDefinition::class, "danger-kotlin", "kotlin-stdlib", "kotlin-reflect")
        }
        refineConfiguration {
            onAnnotations(DependsOn::class, Repository::class, Import::class, CompilerOptions::class, handler = MainKtsConfigurator())
            beforeCompiling(::configureProvidedPropertiesFromJsr223Context)
        }
        ide {
            acceptedLocations(ScriptAcceptedLocation.Everywhere)
        }
        jsr223 {
            importAllBindings(true)
        }
        hostConfiguration(ScriptingHostConfiguration {
            jvm {
                val cacheExtSetting = System.getProperty(DF_COMPILED_SCRIPTS_CACHE_DIR_PROPERTY)
                    ?: System.getenv(DF_COMPILED_SCRIPTS_CACHE_DIR_ENV_VAR)
                val cacheBaseDir = when {
                    cacheExtSetting == null -> System.getProperty("java.io.tmpdir")?.let { File(it) }
                        ?.takeIf { it.exists() && it.isDirectory }
                        ?.let { File(it, "df.kts.compiled.cache").apply { mkdir() } }
                    cacheExtSetting.isBlank() -> null
                    else -> File(cacheExtSetting)
                }?.takeIf { it.exists() && it.isDirectory }
                if (cacheBaseDir != null)
                    compilationCache(
                        CompiledScriptJarsCache { script, scriptCompilationConfiguration ->
                            File(cacheBaseDir, compiledScriptUniqueName(script, scriptCompilationConfiguration) + ".jar")
                        }
                    )
            }
        })
    })

private fun compiledScriptUniqueName(script: SourceCode, scriptCompilationConfiguration: ScriptCompilationConfiguration): String {
    val digestWrapper = MessageDigest.getInstance("MD5")
    digestWrapper.update(script.text.toByteArray())
    scriptCompilationConfiguration.notTransientData.entries
        .sortedBy { it.key.name }
        .forEach {
            digestWrapper.update(it.key.name.toByteArray())
            digestWrapper.update(it.value.toString().toByteArray())
        }
    return digestWrapper.digest().toHexString()
}

private fun ByteArray.toHexString(): String = joinToString("", transform = { "%02x".format(it) })


