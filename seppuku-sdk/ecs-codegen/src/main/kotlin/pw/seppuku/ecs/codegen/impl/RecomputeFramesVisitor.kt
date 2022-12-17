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

import net.bytebuddy.asm.AsmVisitorWrapper
import net.bytebuddy.description.field.FieldDescription
import net.bytebuddy.description.field.FieldList
import net.bytebuddy.description.method.MethodList
import net.bytebuddy.description.type.TypeDescription
import net.bytebuddy.implementation.Implementation
import net.bytebuddy.jar.asm.ClassVisitor
import net.bytebuddy.jar.asm.ClassWriter
import net.bytebuddy.pool.TypePool

internal object RecomputeFramesVisitor : AsmVisitorWrapper {
  override fun mergeWriter(flags: Int): Int = flags or ClassWriter.COMPUTE_FRAMES

  override fun mergeReader(flags: Int): Int = flags

  override fun wrap(
    instrumentedType: TypeDescription,
    classVisitor: ClassVisitor,
    implementationContext: Implementation.Context,
    typePool: TypePool,
    fields: FieldList<FieldDescription.InDefinedShape>,
    methods: MethodList<*>,
    writerFlags: Int,
    readerFlags: Int
  ): ClassVisitor = classVisitor
}
