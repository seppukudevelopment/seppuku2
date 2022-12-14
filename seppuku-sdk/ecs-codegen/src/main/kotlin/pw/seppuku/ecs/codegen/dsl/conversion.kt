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

internal fun MethodVisitor.d2i() = visitInsn(D2I)
internal fun MethodVisitor.i2d() = visitInsn(I2D)

internal fun MethodVisitor.d2l() = visitInsn(D2L)
internal fun MethodVisitor.l2d() = visitInsn(L2D)

internal fun MethodVisitor.d2f() = visitInsn(D2F)
internal fun MethodVisitor.f2d() = visitInsn(F2D)

internal fun MethodVisitor.f2i() = visitInsn(F2I)
internal fun MethodVisitor.i2f() = visitInsn(I2F)

internal fun MethodVisitor.f2l() = visitInsn(F2L)
internal fun MethodVisitor.l2f() = visitInsn(L2F)

internal fun MethodVisitor.i2b() = visitInsn(I2B)
internal fun MethodVisitor.i2c() = visitInsn(I2C)
internal fun MethodVisitor.i2s() = visitInsn(I2S)

internal fun MethodVisitor.i2l() = visitInsn(I2L)
internal fun MethodVisitor.l2i() = visitInsn(L2I)
