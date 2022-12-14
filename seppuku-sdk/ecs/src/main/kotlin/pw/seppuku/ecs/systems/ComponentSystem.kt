package pw.seppuku.ecs.systems

import pw.seppuku.ecs.AbstractSystem
import pw.seppuku.ecs.Entity

abstract class ComponentSystem<T, C : Any>(
  private val componentClass: Class<C>,
  private val components: MutableList<C> = mutableListOf()
) : AbstractSystem<T>({ hasComponent(componentClass) }), Iterable<C> {
  final override fun onEntityAddedToSystem(entity: Entity) {
    components.add(entity.findComponent(componentClass))
  }

  final override fun onEntityRemovedFromSystem(entity: Entity) {
    components.remove(entity.findComponent(componentClass))
  }

  final override fun iterator(): Iterator<C> = components.iterator()
}
