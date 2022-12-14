package pw.seppuku.ecs

interface Engine : Iterable<Entity> {

  fun <T : System<*>> findSystemOrNull(systemClass: Class<T>): T?

  fun <T : System<*>> findSystem(systemClass: Class<T>): T =
    findSystemOrNull(systemClass)
      ?: error("Could not find system '${systemClass.simpleName}' in engine '${this::class.simpleName}")

  fun addSystem(system: System<*>)

  fun removeSystem(system: System<*>)

  fun addEntity(entity: Entity)

  fun removeEntity(entity: Entity)
}
