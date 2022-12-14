package pw.seppuku.ecs

abstract class AbstractSystem<T>(
    private val entityPredicate: EntityPredicate,
) : System<T> {

    protected abstract fun onEntityAddedToSystem(entity: Entity)

    protected abstract fun onEntityRemovedFromSystem(entity: Entity)

    final override fun onEntityAddedToEngine(entity: Entity) {
        if (entity.entityPredicate()) {
            onEntityAddedToSystem(entity)
        }
    }

    final override fun onEntityRemovedFromEngine(entity: Entity) {
        if (entity.entityPredicate()) {
            onEntityRemovedFromSystem(entity)
        }
    }
}