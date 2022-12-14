package pw.seppuku.ecs

interface System<T> {

    // Hacky way of getting ""type-safe"" process function for System
    val process: T

    fun onEntityAddedToEngine(entity: Entity)

    fun onEntityRemovedFromEngine(entity: Entity)
}