package pw.seppuku.ecs

import kotlin.reflect.KClass

fun <T : System<*>> Engine.findSystemOrNull(systemClass: KClass<T>): T? =
  findSystemOrNull(systemClass.java)

inline fun <reified T : System<*>> Engine.findSystemOrNull(): T? = findSystemOrNull(T::class.java)

fun <T : System<*>> Engine.findSystem(systemClass: KClass<T>): T = findSystem(systemClass.java)

inline fun <reified T : System<*>> Engine.findSystem(): T = findSystem(T::class.java)
