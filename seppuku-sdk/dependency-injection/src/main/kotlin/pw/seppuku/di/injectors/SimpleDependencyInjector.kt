package pw.seppuku.di.injectors

import pw.seppuku.di.AbstractDependencyInjector
import pw.seppuku.di.DependencyProvider
import kotlin.reflect.KClass

class SimpleDependencyInjector(
    dependencyTypeToDependencyProvider: MutableMap<KClass<*>, DependencyProvider<out Any>> = mutableMapOf()
) : AbstractDependencyInjector(dependencyTypeToDependencyProvider)