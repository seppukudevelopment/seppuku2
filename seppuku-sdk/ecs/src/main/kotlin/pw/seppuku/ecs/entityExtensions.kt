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

package pw.seppuku.ecs

import kotlin.reflect.KClass

inline fun <T : Any> Entity.hasComponent(componentClass: KClass<T>): Boolean =
  hasComponent(componentClass.java)

inline fun <reified T : Any> Entity.hasComponent(): Boolean = hasComponent(T::class.java)

inline fun <T : Any> Entity.findComponentOrNull(componentClass: KClass<T>): T? =
  findComponentOrNull(componentClass.java)

inline fun <reified T : Any> Entity.findComponentOrNull(): T? = findComponentOrNull(T::class.java)

inline fun <T : Any> Entity.findComponent(componentClass: KClass<T>): T =
  findComponent(componentClass.java)

inline fun <reified T : Any> Entity.findComponent(): T = findComponent(T::class.java)
