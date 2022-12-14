package pw.seppuku.settings.config.configs

import pw.seppuku.settings.config.AbstractConfig
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class ObjectConfig(file: File) : AbstractConfig(file) {

  override fun writeToFile() {
    val serializer = ObjectOutputStream(file.outputStream())
    serializer.writeShort(OBJECT_CONFIG_VERSION)
    serializer.writeObject(pathToValueMap)
    serializer.flush()
    serializer.close()
  }

  @Suppress("UNCHECKED_CAST")
  override fun readFromFile() {
    val deserializer = ObjectInputStream(file.inputStream())
    deserializer.readShort()

    val deserializedPathToValueMap = deserializer.readObject() as MutableMap<String, Any?>
    pathToValueMap.clear()
    pathToValueMap.putAll(deserializedPathToValueMap)

    deserializer.close()
  }

  companion object {
    private const val OBJECT_CONFIG_VERSION: Int = 1
  }
}
