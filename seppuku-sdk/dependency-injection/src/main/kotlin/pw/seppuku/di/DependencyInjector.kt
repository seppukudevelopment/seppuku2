package pw.seppuku.di

import kotlin.reflect.KClass

interface DependencyInjector {
  fun bind(dependencyClassToDependencyProviderPair: Pair<KClass<*>, DependencyProvider<out Any>>) =
    bind(
      dependencyClassToDependencyProviderPair.first, dependencyClassToDependencyProviderPair.second
    )

  fun bind(
    dependencyClass: KClass<*>,
    dependencyProvider: DependencyProvider<out Any>
  )

  fun <T : Any> getOrNull(dependencyClass: KClass<T>): T?

  fun <T : Any> createOrNull(dependencyClass: KClass<T>): T?

  fun <T : Any> get(dependencyClass: KClass<T>): T = getOrNull(dependencyClass)
    ?: error("Could not find dependency '${dependencyClass.simpleName}' in injector '${this::class.simpleName}")

  fun <T : Any> create(dependencyClass: KClass<T>): T = createOrNull(dependencyClass)
    ?: error("Could not create dependency consumer '${dependencyClass.simpleName}'")
}
