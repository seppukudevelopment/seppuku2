package pw.seppuku.ecs.systems

import pw.seppuku.ecs.AbstractSystem
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.EntityPredicate

abstract class EntitySystem<T>(
  entityPredicate: EntityPredicate,
  private val entities: MutableList<Entity> = mutableListOf()
) : AbstractSystem<T>(entityPredicate), Iterable<Entity> {
  final override fun onEntityAddedToSystem(entity: Entity) {
    entities.add(entity)
  }

  final override fun onEntityRemovedFromSystem(entity: Entity) {
    entities.remove(entity)
  }

  final override fun iterator(): Iterator<Entity> = entities.iterator()
}
