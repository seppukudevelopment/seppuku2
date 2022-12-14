package pw.seppuku.di

import pw.seppuku.di.internal.DependencyResolver
import kotlin.reflect.KClass
import kotlin.reflect.full.starProjectedType

abstract class AbstractDependencyInjector(
  private val dependencyClassToDependencyProviderMap: MutableMap<KClass<*>, DependencyProvider<out Any>>
) : DependencyInjector {
  final override fun bind(
    dependencyClass: KClass<*>,
    dependencyProvider: DependencyProvider<out Any>
  ) {
    dependencyClassToDependencyProviderMap[dependencyClass] = dependencyProvider
  }

  @Suppress("UNCHECKED_CAST")
  final override fun <T : Any> getOrNull(dependencyClass: KClass<T>): T? =
    DependencyResolver.resolveDependency(
      dependencyClass.starProjectedType.classifier, dependencyClassToDependencyProviderMap
    ) as? T

  final override fun <T : Any> createOrNull(dependencyClass: KClass<T>): T? =
    DependencyResolver.createBySuitableConstructor(
      dependencyClass, dependencyClassToDependencyProviderMap
    )
}
