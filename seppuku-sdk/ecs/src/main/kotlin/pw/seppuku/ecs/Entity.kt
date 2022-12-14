package pw.seppuku.ecs

interface Entity {

  fun <T : Any> hasComponent(componentClass: Class<T>): Boolean = false

  fun <T : Any> findComponentOrNull(componentClass: Class<T>): T? = null

  fun <T : Any> findComponent(componentClass: Class<T>): T =
    findComponentOrNull(componentClass)
      ?: error("Could not find component '${componentClass.simpleName}' in entity '${this::class.simpleName}")
}
