package pw.seppuku.ecs

interface System<T> {
  val process: T

  fun onEntityAddedToEngine(entity: Entity)

  fun onEntityRemovedFromEngine(entity: Entity)
}
