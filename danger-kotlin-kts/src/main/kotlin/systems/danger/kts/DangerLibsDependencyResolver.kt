package systems.danger.kts

import java.io.File
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.SourceCode
import kotlin.script.experimental.api.asSuccess
import kotlin.script.experimental.dependencies.ExternalDependenciesResolver
import kotlin.script.experimental.dependencies.RepositoryCoordinates
import kotlin.script.experimental.dependencies.impl.makeResolveFailureResult

class DangerLibsDependencyResolver(vararg paths: File) : ExternalDependenciesResolver {

    override fun addRepository(
        repositoryCoordinates: RepositoryCoordinates,
        options: ExternalDependenciesResolver.Options,
        sourceCodeLocation: SourceCode.LocationWithId?
    ): ResultWithDiagnostics<Boolean> {
        return false.asSuccess()
    }

    override suspend fun resolve(
        artifactCoordinates: String,
        options: ExternalDependenciesResolver.Options,
        sourceCodeLocation: SourceCode.LocationWithId?
    ): ResultWithDiagnostics<List<File>> {
        if (!acceptsArtifact(artifactCoordinates)) throw IllegalArgumentException("Path is invalid")

        val messages = mutableListOf<String>()

        for (repo in localRepos) {
            val file = if (repo == null) File(artifactCoordinates) else File(repo, artifactCoordinates)
            when {
                !file.exists() -> messages.add("File '$file' not found in repository ${repo?.absolutePath}")
                !file.isFile && !file.isDirectory -> messages.add("Path '$file' is neither file nor directory")
                else -> {
                    return ResultWithDiagnostics.Success(listOf(file))
                }
            }
        }
        return makeResolveFailureResult(messages, sourceCodeLocation)
    }

    override fun acceptsArtifact(artifactCoordinates: String) = true

    override fun acceptsRepository(repositoryCoordinates: RepositoryCoordinates) = false

    private val localRepos = arrayListOf<File?>(null)

    init {
        for (path in paths) {
            require(path.exists() && path.isDirectory) { "Invalid flat lib directory repository path '$path'" }
        }
        localRepos.addAll(paths)
    }
}