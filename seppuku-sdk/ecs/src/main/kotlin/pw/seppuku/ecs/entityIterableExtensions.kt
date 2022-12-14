@file:Suppress("NOTHING_TO_INLINE")

package pw.seppuku.ecs

import kotlin.reflect.KClass

typealias ComponentPredicate<T> = T.() -> Boolean

inline fun <T : Any> Iterable<Entity>.hasComponent(
  componentClass: Class<T>,
  componentPredicate: ComponentPredicate<T>
) = filter { it.findComponentOrNull(componentClass)?.componentPredicate() == true }

inline fun <T : Any> Iterable<Entity>.hasComponent(
  componentClass: KClass<T>,
  componentPredicate: ComponentPredicate<T>
) = hasComponent(componentClass.java, componentPredicate)

inline fun <reified T : Any> Iterable<Entity>.hasComponent(componentPredicate: ComponentPredicate<T>) =
  hasComponent(T::class.java, componentPredicate)

inline fun <T : Any> Iterable<Entity>.hasOptionalComponent(
  componentClass: Class<T>,
  componentPredicate: ComponentPredicate<T?>
) = filter {
  it.findComponentOrNull(componentClass).componentPredicate()
}

inline fun <T : Any> Iterable<Entity>.hasOptionalComponent(
  componentClass: KClass<T>,
  componentPredicate: ComponentPredicate<T?>
) = hasOptionalComponent(componentClass.java, componentPredicate)

inline fun <reified T : Any> Iterable<Entity>.hasOptionalComponent(componentPredicate: ComponentPredicate<T?>) =
  hasOptionalComponent(T::class.java, componentPredicate)

inline fun <T : Any> Iterable<Entity>.mapComponent(componentClass: Class<T>): Iterable<T> =
  mapNotNull { it.findComponentOrNull(componentClass) }

inline fun <T : Any> Iterable<Entity>.mapComponent(componentClass: KClass<T>): Iterable<T> =
  mapComponent(componentClass.java)

inline fun <reified T : Any> Iterable<Entity>.mapComponent(): Iterable<T> =
  mapComponent(T::class.java)

inline fun <T : Any> Iterable<Entity>.mapOptionalComponent(componentClass: Class<T>): Iterable<T?> =
  map { it.findComponentOrNull(componentClass) }

inline fun <T : Any> Iterable<Entity>.mapOptionalComponent(componentClass: KClass<T>): Iterable<T?> =
  mapOptionalComponent(componentClass.java)

inline fun <reified T : Any> Iterable<Entity>.mapOptionalComponent(): Iterable<T?> =
  mapOptionalComponent(T::class.java)
