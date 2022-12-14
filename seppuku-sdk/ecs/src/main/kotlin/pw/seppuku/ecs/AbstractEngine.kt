package pw.seppuku.ecs

abstract class AbstractEngine(
    private val systemTypeToSystemMap: MutableMap<Class<out System<*>>, System<*>> = mutableMapOf(),
    private val entities: MutableList<Entity> = mutableListOf(),
) : Engine {
    @Suppress("UNCHECKED_CAST")
    final override fun <T : System<*>> findSystemOrNull(systemClass: Class<T>): T? =
        systemTypeToSystemMap[systemClass] as T?

    final override fun addSystem(system: System<*>) {
        systemTypeToSystemMap[system::class.java] = system
        entities.forEach(system::onEntityAddedToEngine)
    }

    final override fun removeSystem(system: System<*>) {
        systemTypeToSystemMap.remove(system::class.java)
        entities.forEach(system::onEntityRemovedFromEngine)
    }

    final override fun addEntity(entity: Entity) {
        entities.add(entity)
        systemTypeToSystemMap.values.forEach { it.onEntityAddedToEngine(entity) }
    }

    final override fun removeEntity(entity: Entity) {
        entities.remove(entity)
        systemTypeToSystemMap.values.forEach { it.onEntityRemovedFromEngine(entity) }
    }

    final override fun iterator(): Iterator<Entity> = entities.iterator()
}