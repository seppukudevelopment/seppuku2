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

package pw.seppuku.ecs.codegen.impl

import net.bytebuddy.dynamic.scaffold.InstrumentedType
import net.bytebuddy.implementation.Implementation
import net.bytebuddy.implementation.bytecode.ByteCodeAppender
import pw.seppuku.ecs.codegen.graph.EntityComponentGraph

internal class EntityHasComponentImplementation(
  private val entityComponentGraph: EntityComponentGraph
) : Implementation {
  override fun prepare(instrumentedType: InstrumentedType): InstrumentedType = instrumentedType

  override fun appender(implementationTarget: Implementation.Target): ByteCodeAppender =
    EntityHasComponentByteCodeAppender(entityComponentGraph)
}
