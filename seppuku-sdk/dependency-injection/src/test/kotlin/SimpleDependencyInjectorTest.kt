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

import pw.seppuku.di.DependencyProvider
import pw.seppuku.di.injectors.SimpleDependencyInjector
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SimpleDependencyInjectorTest {

  private val dependencyInjector = SimpleDependencyInjector()

  @Test
  fun `getOrNull should return x for bound dependency type`() {
    val dependencyType = String::class

    val dependencyProvider = DependencyProvider { "string" }
    dependencyInjector.bind(dependencyType, dependencyProvider)

    val dependency = dependencyInjector.getOrNull(dependencyType)
    assertEquals(dependencyProvider.provide(), dependency)
  }

  @Test
  fun `getOrNull should return null for unbound dependency type`() {
    val dependencyType = String::class

    val dependency = dependencyInjector.getOrNull(dependencyType)

    assertNull(dependency)
  }
}
