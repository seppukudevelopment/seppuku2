@file:Suppress("NOTHING_TO_INLINE")

package pw.seppuku.ecs

import kotlin.reflect.KClass

inline fun <T : Any> Entity.hasComponent(componentClass: KClass<T>): Boolean =
  hasComponent(componentClass::class.java)

inline fun <reified T : Any> Entity.hasComponent(): Boolean = hasComponent(T::class.java)

inline fun <T : Any> Entity.findComponentOrNull(componentClass: KClass<T>): T? =
  findComponentOrNull(componentClass.java)

inline fun <reified T : Any> Entity.findComponentOrNull(): T? = findComponentOrNull(T::class.java)

inline fun <T : Any> Entity.findComponent(componentClass: KClass<T>): T =
  findComponent(componentClass.java)

inline fun <reified T : Any> Entity.findComponent(): T = findComponent(T::class.java)
