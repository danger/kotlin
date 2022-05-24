package systems.danger.kts

import kotlinx.coroutines.runBlocking
import org.jetbrains.kotlin.mainKts.*
import java.io.File
import kotlin.script.dependencies.ScriptContents
import kotlin.script.dependencies.ScriptDependenciesResolver
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.dependencies.*
import kotlin.script.experimental.dependencies.maven.MavenDependenciesResolver
import kotlin.script.experimental.host.FileBasedScriptSource
import kotlin.script.experimental.host.FileScriptSource
import kotlin.script.experimental.impl.internalScriptingRunSuspend
import kotlin.script.experimental.jvm.compat.mapLegacyDiagnosticSeverity
import kotlin.script.experimental.jvm.compat.mapLegacyScriptPosition
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvm.updateClasspath
import kotlin.script.experimental.jvmhost.jsr223.configureProvidedPropertiesFromJsr223Context
import kotlin.script.experimental.jvmhost.jsr223.importAllBindings
import kotlin.script.experimental.jvmhost.jsr223.jsr223
import kotlin.script.experimental.util.filterByAnnotationType

@Suppress("unused")
@KotlinScript(
    fileExtension = "df.kts",
    compilationConfiguration = DangerFileScriptDefinition::class,
    evaluationConfiguration = MainKtsEvaluationConfiguration::class,
    hostConfiguration = MainKtsHostConfiguration::class
)
abstract class DangerFileScript(val args: Array<String>)

object DangerFileScriptDefinition : ScriptCompilationConfiguration(
    {
        defaultImports(DependsOn::class, Repository::class, Import::class, CompilerOptions::class)
        jvm {
            dependenciesFromClassContext(
                DangerFileScriptDefinition::class,
                "danger-kotlin",
                "kotlin-stdlib",
                "kotlin-reflect"
            )
        }
        refineConfiguration {
            onAnnotations(
                DependsOn::class,
                Repository::class,
                Import::class,
                CompilerOptions::class,
                handler = DangerFileKtsConfigurator()
            )
            beforeCompiling(::configureProvidedPropertiesFromJsr223Context)
        }
        ide {
            acceptedLocations(ScriptAcceptedLocation.Everywhere)
        }
        jsr223 {
            importAllBindings(true)
        }
    }
)

class DangerFileKtsConfigurator : RefineScriptCompilationConfigurationHandler {

    private val resolver = CompoundDependenciesResolver(
        FileSystemDependenciesResolver(DANGER_LIBS_FLAT_DIR),
        MavenDependenciesResolver()
    )

    override operator fun invoke(context: ScriptConfigurationRefinementContext): ResultWithDiagnostics<ScriptCompilationConfiguration> =
        processAnnotations(context)

    fun processAnnotations(context: ScriptConfigurationRefinementContext): ResultWithDiagnostics<ScriptCompilationConfiguration> {
        val diagnostics = arrayListOf<ScriptDiagnostic>()

        fun report(severity: ScriptDependenciesResolver.ReportSeverity, message: String, position: ScriptContents.Position?) {
            diagnostics.add(
                ScriptDiagnostic(
                    ScriptDiagnostic.unspecifiedError,
                    message,
                    mapLegacyDiagnosticSeverity(severity),
                    context.script.locationId,
                    mapLegacyScriptPosition(position)
                )
            )
        }

        val annotations = context.collectedData?.get(ScriptCollectedData.collectedAnnotations)?.takeIf { it.isNotEmpty() }
            ?: return context.compilationConfiguration.asSuccess()

        val scriptBaseDir = (context.script as? FileBasedScriptSource)?.file?.parentFile
        val importedSources = linkedMapOf<String, Pair<File, String>>()
        var hasImportErrors = false
        annotations.filterByAnnotationType<Import>().forEach { scriptAnnotation ->
            scriptAnnotation.annotation.paths.forEach { sourceName ->
                val file = (scriptBaseDir?.resolve(sourceName) ?: File(sourceName)).normalize()
                val keyPath = file.absolutePath
                val prevImport = importedSources.put(keyPath, file to sourceName)
                if (prevImport != null) {
                    diagnostics.add(
                        ScriptDiagnostic(
                            ScriptDiagnostic.unspecifiedError, "Duplicate imports: \"${prevImport.second}\" and \"$sourceName\"",
                            sourcePath = context.script.locationId, location = scriptAnnotation.location?.locationInText
                        )
                    )
                    hasImportErrors = true
                }
            }
        }
        if (hasImportErrors) return ResultWithDiagnostics.Failure(diagnostics)
        val compileOptions = annotations.filterByAnnotationType<CompilerOptions>().flatMap {
            it.annotation.options.toList()
        }

        val resolveResult = try {
            @Suppress("DEPRECATION_ERROR")
            (internalScriptingRunSuspend {
                resolver.resolveFromScriptSourceAnnotations(annotations.filter { it.annotation is DependsOn || it.annotation is Repository })
            })

        } catch (e: Throwable) {
            diagnostics.add(e.asDiagnostics(path = context.script.locationId))
            ResultWithDiagnostics.Failure(diagnostics)
        }

        return resolveResult.onSuccess { resolvedClassPath ->
            ScriptCompilationConfiguration(context.compilationConfiguration) {
                updateClasspath(resolvedClassPath)
                if (importedSources.isNotEmpty()) importScripts.append(importedSources.values.map { FileScriptSource(it.first) })
                if (compileOptions.isNotEmpty()) compilerOptions.append(compileOptions)
            }.asSuccess()
        }
    }

    private companion object {
        const val DANGER_DEFAULT_FLAT_DIR = "/usr/local/lib/danger/libs"
        private val DANGER_LIBS_FLAT_DIR = File(DANGER_DEFAULT_FLAT_DIR)
    }
}
