package pw.seppuku.settings.config.factories

import pw.seppuku.settings.config.Config
import pw.seppuku.settings.config.ConfigFactory
import java.io.File
import kotlin.reflect.KClass

abstract class CascadingConfigFactory<T : Config>(
  private val configClass: KClass<T>,
  private val directory: String,
  private val fileExtension: String? = null
) : ConfigFactory {

  init {
    File(directory).mkdirs()
  }

  override fun create(path: String): Config =
    configClass.constructors.first().call(
      File(buildFilePath(path))
    )

  private fun buildFilePath(path: String): String =
    "$directory/${path.replace(".", "/")}" +
      if (fileExtension != null) ".$fileExtension" else ""
}
