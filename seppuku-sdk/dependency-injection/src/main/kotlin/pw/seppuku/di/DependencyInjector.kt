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
