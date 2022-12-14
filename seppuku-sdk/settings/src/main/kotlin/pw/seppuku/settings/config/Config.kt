package pw.seppuku.settings.config

import java.io.File

interface Config {

    val file: File
    val pathToValueMap: MutableMap<String, Any?>

    fun writeToFile()

    fun readFromFile()

    fun exists(): Boolean = file.exists()

    fun contains(path: String) =
        pathToValueMap.containsKey(path)

    fun <T : Any> write(path: String, value: T?) {
        pathToValueMap[path] = value
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> read(path: String): T? =
        pathToValueMap[path] as T?
}