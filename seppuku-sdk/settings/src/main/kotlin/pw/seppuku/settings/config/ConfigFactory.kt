package pw.seppuku.settings.config

interface ConfigFactory {

    fun create(path: String): Config
}