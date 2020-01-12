package systems.danger.kts

import org.jetbrains.kotlin.mainKts.MainKtsConfigurator
import org.jetbrains.kotlin.mainKts.MainKtsEvaluationConfiguration
import org.jetbrains.kotlin.script.util.DependsOn
import org.jetbrains.kotlin.script.util.Import
import org.jetbrains.kotlin.script.util.Repository
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm
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

object DangerFileScriptDefinition : ScriptCompilationConfiguration(
    {
        defaultImports(DependsOn::class, Repository::class, Import::class)
        jvm {
            dependenciesFromClassContext(
                DangerFileScriptDefinition::class,
                "kotlin-stdlib",
                "kotlin-reflect",
                "danger-kotlin"
            )
        }
        refineConfiguration {
            onAnnotations(DependsOn::class, Repository::class, Import::class, handler = MainKtsConfigurator())
            beforeCompiling { context ->
                configureProvidedPropertiesFromJsr223Context(
                    ScriptConfigurationRefinementContext(
                        context.script,
                        context.compilationConfiguration,
                        context.collectedData
                    )
                )
            }
        }
        ide {
            acceptedLocations(ScriptAcceptedLocation.Everywhere)
        }
        jsr223 {
            importAllBindings(true)
        }
    })