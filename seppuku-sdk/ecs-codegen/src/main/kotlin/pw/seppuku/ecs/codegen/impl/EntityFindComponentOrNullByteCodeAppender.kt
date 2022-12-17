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

import net.bytebuddy.description.method.MethodDescription
import net.bytebuddy.implementation.Implementation
import net.bytebuddy.implementation.bytecode.ByteCodeAppender
import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size
import net.bytebuddy.jar.asm.Label
import net.bytebuddy.jar.asm.MethodVisitor
import pw.seppuku.ecs.codegen.dsl.aconst_null
import pw.seppuku.ecs.codegen.dsl.aload
import pw.seppuku.ecs.codegen.dsl.areturn
import pw.seppuku.ecs.codegen.dsl.invokevirtual
import pw.seppuku.ecs.codegen.dsl.label
import pw.seppuku.ecs.codegen.graph.EntityComponentGraph

internal class EntityFindComponentOrNullByteCodeAppender(
  private val entityComponentGraph: EntityComponentGraph
) : ByteCodeAppender {
  override fun apply(
    methodVisitor: MethodVisitor,
    implementationContext: Implementation.Context,
    instrumentedMethod: MethodDescription
  ): Size = methodVisitor.run {
    entityComponentGraph.reversed().forEach {
      val exit = Label()

      aload(1)
      it.matchWithExit(this, exit) {
        aload(0)
        invokevirtual(it.componentOwner, it.componentGetterName, it.componentClass)
        areturn()
      }

      label(exit)
    }

    aconst_null()
    areturn()

    return Size(0, 0)
  }
}
