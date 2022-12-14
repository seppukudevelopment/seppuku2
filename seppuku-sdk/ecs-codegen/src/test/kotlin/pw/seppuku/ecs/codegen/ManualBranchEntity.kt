package pw.seppuku.ecs.codegen

import pw.seppuku.ecs.Entity

class ManualBranchEntity(
  private val velocity: Velocity = Velocity(0f, 0f, 0f)
) : Entity {
  @Suppress("UNCHECKED_CAST")
  override fun <T : Any> findComponentOrNull(componentClass: Class<T>): T? =
    if (componentClass == Velocity::class.java) {
      velocity as T?
    } else {
      null
    }
}
