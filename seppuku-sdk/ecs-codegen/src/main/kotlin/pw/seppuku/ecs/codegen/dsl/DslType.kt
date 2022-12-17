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

package pw.seppuku.ecs.codegen.dsl

import net.bytebuddy.description.type.TypeDescription
import net.bytebuddy.jar.asm.Type
import kotlin.reflect.KClass

internal typealias DslType = Any

internal inline fun DslType.coerce(): Type = when (this) {
  is Type -> this
  is String -> Type.getObjectType(this)
  is Class<*> -> Type.getType(this)
  is KClass<*> -> Type.getType(this.java)
  is TypeDescription -> Type.getType(this.descriptor)
  else -> error("Could not coerce type '${this::class.simpleName}'")
}
