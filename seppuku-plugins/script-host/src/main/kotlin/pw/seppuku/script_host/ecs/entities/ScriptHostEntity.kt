package pw.seppuku.script_host.ecs.entities

import kotlinx.coroutines.runBlocking
import pw.seppuku.common_systems.ecs.components.minecraft.client.MinecraftClientInit
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.di.DependencyInjector
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Engine
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.codegen.EntityCodeGenerator
import pw.seppuku.script_host.ecs.ScriptEntity
import pw.seppuku.script_host.ecs.ScriptSystem
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf
import kotlin.script.experimental.api.*
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.JvmScriptCompiler

abstract class ScriptHostEntity(
  private val dependencyInjector: DependencyInjector,
  private val engine: Engine,
  private val entityCodeGenerator: EntityCodeGenerator,
) : Entity {

  @Component val humanIdentifier = HumanIdentifier("script_host")

  @Component val scriptCompilationConfiguration = ScriptCompilationConfiguration {
    jvm {
      dependenciesFromCurrentContext(wholeClasspath = true)
      defaultImports(ScriptEntity::class, ScriptSystem::class)
    }
  }

  @Component val scriptEvaluationConfiguration = ScriptEvaluationConfiguration {}

  @Component val minecraftClientInit = MinecraftClientInit {
    runBlocking { Path("seppuku", "scripts", "test.kts").loadScript() }
  }

  @Suppress("UNCHECKED_CAST") private suspend fun Path.loadScript() {
    val compiledScript = compileScript().valueOr { failure ->
      return failure.reports.filter { it.isError() }.forEach {
        println(it.message)
      }
    }

    val compiledScriptClass =
      compiledScript.getClass(scriptEvaluationConfiguration).valueOr { failure ->
        return failure.reports.filter { it.isError() }.forEach {
          println(it.message)
        }
      }

    if (compiledScriptClass.isSubclassOf(ScriptEntity::class)) {
      compiledScriptClass as KClass<out ScriptEntity>
      val scriptClassEntityImplementation =
        entityCodeGenerator.generateImplementationClass(compiledScriptClass)
      val scriptClassEntity = dependencyInjector.create(scriptClassEntityImplementation)
      engine.addEntity(scriptClassEntity)
    } else if (compiledScriptClass.isSubclassOf(ScriptSystem::class)) {
      compiledScriptClass as KClass<out ScriptSystem<*>>
      val scriptClassSystem = dependencyInjector.create(compiledScriptClass)
      engine.addSystem(scriptClassSystem)
    } else {
      compiledScriptClass.createInstance()
    }
  }

  private suspend fun Path.compileScript(): ResultWithDiagnostics<CompiledScript> =
    toFile().compileScript()

  private suspend fun File.compileScript(): ResultWithDiagnostics<CompiledScript> =
    toScriptSource().compileScript()

  private suspend fun SourceCode.compileScript(): ResultWithDiagnostics<CompiledScript> =
    JvmScriptCompiler()(this, scriptCompilationConfiguration)
}
