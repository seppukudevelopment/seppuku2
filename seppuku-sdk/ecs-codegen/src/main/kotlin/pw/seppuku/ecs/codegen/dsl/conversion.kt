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
import net.bytebuddy.jar.asm.Opcodes.D2F
import net.bytebuddy.jar.asm.Opcodes.D2I
import net.bytebuddy.jar.asm.Opcodes.D2L
import net.bytebuddy.jar.asm.Opcodes.F2D
import net.bytebuddy.jar.asm.Opcodes.F2I
import net.bytebuddy.jar.asm.Opcodes.F2L
import net.bytebuddy.jar.asm.Opcodes.I2B
import net.bytebuddy.jar.asm.Opcodes.I2C
import net.bytebuddy.jar.asm.Opcodes.I2D
import net.bytebuddy.jar.asm.Opcodes.I2F
import net.bytebuddy.jar.asm.Opcodes.I2L
import net.bytebuddy.jar.asm.Opcodes.I2S
import net.bytebuddy.jar.asm.Opcodes.L2D
import net.bytebuddy.jar.asm.Opcodes.L2F
import net.bytebuddy.jar.asm.Opcodes.L2I

internal inline fun MethodVisitor.d2i() = visitInsn(D2I)
internal inline fun MethodVisitor.i2d() = visitInsn(I2D)

internal inline fun MethodVisitor.d2l() = visitInsn(D2L)
internal inline fun MethodVisitor.l2d() = visitInsn(L2D)

internal inline fun MethodVisitor.d2f() = visitInsn(D2F)
internal inline fun MethodVisitor.f2d() = visitInsn(F2D)

internal inline fun MethodVisitor.f2i() = visitInsn(F2I)
internal inline fun MethodVisitor.i2f() = visitInsn(I2F)

internal inline fun MethodVisitor.f2l() = visitInsn(F2L)
internal inline fun MethodVisitor.l2f() = visitInsn(L2F)

internal inline fun MethodVisitor.i2b() = visitInsn(I2B)
internal inline fun MethodVisitor.i2c() = visitInsn(I2C)
internal inline fun MethodVisitor.i2s() = visitInsn(I2S)

internal inline fun MethodVisitor.i2l() = visitInsn(I2L)
internal inline fun MethodVisitor.l2i() = visitInsn(L2I)
