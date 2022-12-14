@file:Suppress("NOTHING_TO_INLINE")

package pw.seppuku.ecs.codegen.graph

import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.jvmErasure

internal typealias EntityComponentGraph = List<EntityComponent>

internal fun <T : Entity> buildEntityComponentGraph(entityClass: KClass<T>): EntityComponentGraph =
  buildEntityComponentGraphNoSuper(entityClass) + entityClass.allSuperclasses.map {
    buildEntityComponentGraphNoSuper(it)
  }.flatten()

private fun buildEntityComponentGraphNoSuper(entityClass: KClass<*>): EntityComponentGraph =
  entityClass.memberProperties.filter { it.javaField?.declaringClass == entityClass.java }
    .filter(::hasComponentAnnotation)
    .mapNotNull {
      EntityComponent(
        it.returnType.jvmErasure,
        entityClass,
        it.javaField?.name ?: return@mapNotNull null,
        it.javaGetter?.name ?: return@mapNotNull null
      )
    }

private inline fun hasComponentAnnotation(property: KProperty1<*, *>): Boolean =
  property.hasAnnotation<Component>() or property.getter.hasAnnotation<Component>()
