package systems.danger.kts

import org.jetbrains.kotlin.mainKts.*
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.*
import kotlin.script.experimental.dependencies.DependsOn
import kotlin.script.experimental.dependencies.Repository
import kotlin.script.experimental.jvm.dependenciesFromClassContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.jsr223.configureProvidedPropertiesFromJsr223Context
import kotlin.script.experimental.jvmhost.jsr223.importAllBindings
import kotlin.script.experimental.jvmhost.jsr223.jsr223

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
                handler = MainKtsConfigurator()
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
