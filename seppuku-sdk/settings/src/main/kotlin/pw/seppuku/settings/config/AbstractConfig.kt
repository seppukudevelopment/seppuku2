package pw.seppuku.settings.config

import java.io.File

abstract class AbstractConfig(
    override val file: File,
    override val pathToValueMap: MutableMap<String, Any?> = mutableMapOf()
) : Config