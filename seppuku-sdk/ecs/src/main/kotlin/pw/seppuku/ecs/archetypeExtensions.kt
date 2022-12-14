@file:Suppress("NOTHING_TO_INLINE")

package pw.seppuku.ecs

import kotlin.reflect.KClass

inline fun archetype(vararg componentTypes: Class<*>): Archetype = Archetype(*componentTypes)

inline fun archetype(vararg componentTypes: KClass<*>): Archetype =
  archetype(*componentTypes.map { it.java }.toTypedArray())
