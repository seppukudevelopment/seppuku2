package pw.seppuku.di.internal

import pw.seppuku.di.DependencyProvider
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KFunction

internal object DependencyResolver {

    internal fun resolveDependency(
        dependencyClassifier: KClassifier?,
        dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
    ): Any? = dependencyTypeToDependencyProvider[dependencyClassifier]?.provide()

    internal fun <T : Any> createBySuitableConstructor(
        dependencyClass: KClass<T>, dependencyClassToDependencyProviderMap: Map<KClass<*>, DependencyProvider<out Any>>
    ): T? {
        val suitableConstructor =
            findSuitableConstructor(dependencyClass, dependencyClassToDependencyProviderMap) ?: return null
        val dependencies =
            resolveDependencies(suitableConstructor, dependencyClassToDependencyProviderMap) ?: return null
        return suitableConstructor.call(*dependencies)
    }

    private fun <T : Any> findSuitableConstructor(
        dependencyClass: KClass<T>, dependencyClassToDependencyProviderMap: Map<KClass<*>, DependencyProvider<out Any>>
    ): KFunction<T>? =
        dependencyClass.constructors.associateBy { resolveDependencies(it, dependencyClassToDependencyProviderMap) }
            .filterKeys { it != null }.toSortedMap(Comparator.comparingInt { it!!.size })
            .firstNotNullOfOrNull { it.value }

    private fun <T : Any> resolveDependencies(
        constructor: KFunction<T>, dependencyClassToDependencyProviderMap: Map<KClass<*>, DependencyProvider<out Any>>
    ): Array<Any?>? = constructor.parameters.map {
        val dependency = resolveDependency(it.type.classifier, dependencyClassToDependencyProviderMap)
        if (dependency == null && !it.type.isMarkedNullable) return@resolveDependencies null

        dependency
    }.toTypedArray()
}