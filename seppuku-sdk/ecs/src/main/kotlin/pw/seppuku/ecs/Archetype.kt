package pw.seppuku.ecs

class Archetype(private vararg val componentClasses: Class<*>) {
  fun buildEntityPredicate(): EntityPredicate = {
    componentClasses.all { hasComponent(it) }
  }
}
