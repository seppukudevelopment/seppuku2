package pw.seppuku.script_host

import net.minecraft.client.MinecraftClient
import java.io.File
import java.nio.file.Path
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.api.SourceCode
import kotlin.script.experimental.api.defaultImports
import kotlin.script.experimental.host.BasicScriptingHost
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate
import kotlin.script.templates.standard.SimpleScriptTemplate

object SeppukuScriptHost {
  private val compilationConfiguration: ScriptCompilationConfiguration by lazy {
    createJvmCompilationConfigurationFromTemplate<SimpleScriptTemplate> {
      defaultImports(MinecraftClient::class)
      jvm {
        dependenciesFromCurrentContext(wholeClasspath = true)
      }
    }
  }

  private val scriptingHost: BasicScriptingHost by lazy {
    BasicJvmScriptingHost()
  }

  fun eval(path: Path): ResultWithDiagnostics<EvaluationResult> = eval(path.toFile())

  private fun eval(file: File): ResultWithDiagnostics<EvaluationResult> =
    eval(file.toScriptSource())

  private fun eval(sourceCode: SourceCode): ResultWithDiagnostics<EvaluationResult> =
    scriptingHost.eval(sourceCode, compilationConfiguration, null)
}
