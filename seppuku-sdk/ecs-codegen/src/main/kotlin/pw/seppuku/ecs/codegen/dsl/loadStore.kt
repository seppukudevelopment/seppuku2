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

import net.bytebuddy.jar.asm.MethodVisitor
import net.bytebuddy.jar.asm.Opcodes.AALOAD
import net.bytebuddy.jar.asm.Opcodes.AASTORE
import net.bytebuddy.jar.asm.Opcodes.ACONST_NULL
import net.bytebuddy.jar.asm.Opcodes.ALOAD
import net.bytebuddy.jar.asm.Opcodes.ASTORE
import net.bytebuddy.jar.asm.Opcodes.BALOAD
import net.bytebuddy.jar.asm.Opcodes.BASTORE
import net.bytebuddy.jar.asm.Opcodes.BIPUSH
import net.bytebuddy.jar.asm.Opcodes.CALOAD
import net.bytebuddy.jar.asm.Opcodes.CASTORE
import net.bytebuddy.jar.asm.Opcodes.DALOAD
import net.bytebuddy.jar.asm.Opcodes.DASTORE
import net.bytebuddy.jar.asm.Opcodes.DCONST_0
import net.bytebuddy.jar.asm.Opcodes.DCONST_1
import net.bytebuddy.jar.asm.Opcodes.DLOAD
import net.bytebuddy.jar.asm.Opcodes.DSTORE
import net.bytebuddy.jar.asm.Opcodes.FALOAD
import net.bytebuddy.jar.asm.Opcodes.FASTORE
import net.bytebuddy.jar.asm.Opcodes.FCONST_0
import net.bytebuddy.jar.asm.Opcodes.FCONST_1
import net.bytebuddy.jar.asm.Opcodes.FCONST_2
import net.bytebuddy.jar.asm.Opcodes.FLOAD
import net.bytebuddy.jar.asm.Opcodes.FSTORE
import net.bytebuddy.jar.asm.Opcodes.IALOAD
import net.bytebuddy.jar.asm.Opcodes.IASTORE
import net.bytebuddy.jar.asm.Opcodes.ICONST_0
import net.bytebuddy.jar.asm.Opcodes.ICONST_1
import net.bytebuddy.jar.asm.Opcodes.ICONST_2
import net.bytebuddy.jar.asm.Opcodes.ICONST_3
import net.bytebuddy.jar.asm.Opcodes.ICONST_4
import net.bytebuddy.jar.asm.Opcodes.ICONST_5
import net.bytebuddy.jar.asm.Opcodes.ILOAD
import net.bytebuddy.jar.asm.Opcodes.ISTORE
import net.bytebuddy.jar.asm.Opcodes.LALOAD
import net.bytebuddy.jar.asm.Opcodes.LASTORE
import net.bytebuddy.jar.asm.Opcodes.LCONST_0
import net.bytebuddy.jar.asm.Opcodes.LCONST_1
import net.bytebuddy.jar.asm.Opcodes.LLOAD
import net.bytebuddy.jar.asm.Opcodes.LSTORE
import net.bytebuddy.jar.asm.Opcodes.SIPUSH

internal inline fun MethodVisitor.aaload() = visitInsn(AALOAD)
internal inline fun MethodVisitor.aastore() = visitInsn(AASTORE)
internal inline fun MethodVisitor.aconst_null() = visitInsn(ACONST_NULL)
internal inline fun MethodVisitor.aload(index: Int) = visitVarInsn(ALOAD, index)
internal inline fun MethodVisitor.astore(index: Int) = visitVarInsn(ASTORE, index)

internal inline fun MethodVisitor.baload() = visitInsn(BALOAD)
internal inline fun MethodVisitor.bastore() = visitInsn(BASTORE)
internal inline fun MethodVisitor.bipush(operand: Int) = visitIntInsn(BIPUSH, operand)

internal inline fun MethodVisitor.caload() = visitInsn(CALOAD)
internal inline fun MethodVisitor.castore() = visitInsn(CASTORE)

internal inline fun MethodVisitor.daload() = visitInsn(DALOAD)
internal inline fun MethodVisitor.dastore() = visitInsn(DASTORE)
internal inline fun MethodVisitor.dconst_0() = visitInsn(DCONST_0)
internal inline fun MethodVisitor.dconst_1() = visitInsn(DCONST_1)
internal inline fun MethodVisitor.dload(index: Int) = visitVarInsn(DLOAD, index)
internal inline fun MethodVisitor.dstore(index: Int) = visitVarInsn(DSTORE, index)

internal inline fun MethodVisitor.faload() = visitInsn(FALOAD)
internal inline fun MethodVisitor.fastore() = visitInsn(FASTORE)
internal inline fun MethodVisitor.fconst_0() = visitInsn(FCONST_0)
internal inline fun MethodVisitor.fconst_1() = visitInsn(FCONST_1)
internal inline fun MethodVisitor.fconst_2() = visitInsn(FCONST_2)
internal inline fun MethodVisitor.fload(index: Int) = visitVarInsn(FLOAD, index)
internal inline fun MethodVisitor.fstore(index: Int) = visitVarInsn(FSTORE, index)

internal inline fun MethodVisitor.iaload() = visitInsn(IALOAD)
internal inline fun MethodVisitor.iastore() = visitInsn(IASTORE)
internal inline fun MethodVisitor.iconst_0() = visitInsn(ICONST_0)
internal inline fun MethodVisitor.iconst_1() = visitInsn(ICONST_1)
internal inline fun MethodVisitor.iconst_2() = visitInsn(ICONST_2)
internal inline fun MethodVisitor.iconst_3() = visitInsn(ICONST_3)
internal inline fun MethodVisitor.iconst_4() = visitInsn(ICONST_4)
internal inline fun MethodVisitor.iconst_5() = visitInsn(ICONST_5)
internal inline fun MethodVisitor.iload(index: Int) = visitVarInsn(ILOAD, index)
internal inline fun MethodVisitor.istore(index: Int) = visitVarInsn(ISTORE, index)

internal inline fun MethodVisitor.laload() = visitInsn(LALOAD)
internal inline fun MethodVisitor.lastore() = visitInsn(LASTORE)
internal inline fun MethodVisitor.lload(index: Int) = visitVarInsn(LLOAD, index)
internal inline fun MethodVisitor.lstore(index: Int) = visitVarInsn(LSTORE, index)
internal inline fun MethodVisitor.lconst_0() = visitInsn(LCONST_0)
internal inline fun MethodVisitor.lconst_1() = visitInsn(LCONST_1)

internal inline fun MethodVisitor.saload() = visitInsn(AALOAD)
internal inline fun MethodVisitor.sastore() = visitInsn(AASTORE)
internal inline fun MethodVisitor.sipush(operand: Int) = visitIntInsn(SIPUSH, operand)

internal inline fun MethodVisitor.ldc(value: DslType) = visitLdcInsn(value.coerce())
