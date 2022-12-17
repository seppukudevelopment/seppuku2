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
import net.bytebuddy.jar.asm.Opcodes.ARETURN
import net.bytebuddy.jar.asm.Opcodes.DRETURN
import net.bytebuddy.jar.asm.Opcodes.FRETURN
import net.bytebuddy.jar.asm.Opcodes.INVOKEINTERFACE
import net.bytebuddy.jar.asm.Opcodes.INVOKESPECIAL
import net.bytebuddy.jar.asm.Opcodes.INVOKESTATIC
import net.bytebuddy.jar.asm.Opcodes.INVOKEVIRTUAL
import net.bytebuddy.jar.asm.Opcodes.IRETURN
import net.bytebuddy.jar.asm.Opcodes.LRETURN
import net.bytebuddy.jar.asm.Opcodes.RETURN
import net.bytebuddy.jar.asm.Type

internal inline fun MethodVisitor.invokeinterface(
  owner: DslType,
  name: String,
  descriptor: String
) = visitMethodInsn(INVOKEINTERFACE, owner.coerce().internalName, name, descriptor, true)

internal inline fun MethodVisitor.invokevirtual(
  owner: DslType,
  name: String,
  descriptor: String,
  isInterface: Boolean = false
) = visitMethodInsn(INVOKEVIRTUAL, owner.coerce().internalName, name, descriptor, isInterface)

internal inline fun MethodVisitor.invokespecial(
  owner: DslType,
  name: String,
  descriptor: String,
  isInterface: Boolean = false
) = visitMethodInsn(INVOKESPECIAL, owner.coerce().internalName, name, descriptor, isInterface)

internal inline fun MethodVisitor.invokestatic(
  owner: DslType,
  name: String,
  descriptor: String,
  isInterface: Boolean = false
) = visitMethodInsn(INVOKESTATIC, owner.coerce().internalName, name, descriptor, isInterface)

internal inline fun MethodVisitor.invokeinterface(
  owner: DslType,
  name: String,
  returnType: DslType,
  vararg arguments: DslType
) = visitMethodInsn(
  INVOKEINTERFACE,
  owner.coerce().internalName,
  name,
  Type.getMethodDescriptor(returnType.coerce(), *arguments.map(DslType::coerce).toTypedArray()),
  true
)

internal inline fun MethodVisitor.invokevirtual(
  owner: DslType,
  name: String,
  returnType: DslType,
  vararg arguments: DslType,
  isInterface: Boolean = false
) = visitMethodInsn(
  INVOKEVIRTUAL,
  owner.coerce().internalName,
  name,
  Type.getMethodDescriptor(returnType.coerce(), *arguments.map(DslType::coerce).toTypedArray()),
  isInterface
)

internal inline fun MethodVisitor.invokespecial(
  owner: DslType,
  name: String,
  returnType: DslType,
  vararg arguments: DslType,
  isInterface: Boolean = false
) = visitMethodInsn(
  INVOKESPECIAL,
  owner.coerce().internalName,
  name,
  Type.getMethodDescriptor(returnType.coerce(), *arguments.map(DslType::coerce).toTypedArray()),
  isInterface
)

internal inline fun MethodVisitor.invokestatic(
  owner: DslType,
  name: String,
  returnType: DslType,
  vararg arguments: DslType,
  isInterface: Boolean = false
) = visitMethodInsn(
  INVOKESTATIC,
  owner.coerce().internalName,
  name,
  Type.getMethodDescriptor(returnType.coerce(), *arguments.map(DslType::coerce).toTypedArray()),
  isInterface
)

internal inline fun MethodVisitor.`return`() = visitInsn(RETURN)
internal inline fun MethodVisitor.areturn() = visitInsn(ARETURN)
internal inline fun MethodVisitor.dreturn() = visitInsn(DRETURN)
internal inline fun MethodVisitor.freturn() = visitInsn(FRETURN)
internal inline fun MethodVisitor.ireturn() = visitInsn(IRETURN)
internal inline fun MethodVisitor.lreturn() = visitInsn(LRETURN)
