package pw.seppuku.ecs

import kotlin.reflect.KClass

fun archetype(vararg componentTypes: Class<*>): Archetype =
  Archetype(*componentTypes)

fun archetype(vararg componentTypes: KClass<*>): Archetype =
  archetype(*componentTypes.map { it.java }.toTypedArray())
