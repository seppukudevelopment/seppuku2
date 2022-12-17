/*
 *     Copyright (C) 2022  Seppuku Development
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
