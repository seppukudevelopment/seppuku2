@file:Suppress("NOTHING_TO_INLINE", "FunctionName")

package pw.seppuku.ecs.codegen.dsl

import net.bytebuddy.jar.asm.MethodVisitor
import net.bytebuddy.jar.asm.Opcodes.DUP
import net.bytebuddy.jar.asm.Opcodes.DUP2
import net.bytebuddy.jar.asm.Opcodes.DUP2_X1
import net.bytebuddy.jar.asm.Opcodes.DUP2_X2
import net.bytebuddy.jar.asm.Opcodes.DUP_X1
import net.bytebuddy.jar.asm.Opcodes.DUP_X2
import net.bytebuddy.jar.asm.Opcodes.POP
import net.bytebuddy.jar.asm.Opcodes.POP2
import net.bytebuddy.jar.asm.Opcodes.SWAP

internal inline fun MethodVisitor.dup() = visitInsn(DUP)
internal inline fun MethodVisitor.dup_x1() = visitInsn(DUP_X1)
internal inline fun MethodVisitor.dup_x2() = visitInsn(DUP_X2)

internal inline fun MethodVisitor.dup2() = visitInsn(DUP2)
internal inline fun MethodVisitor.dup2_x1() = visitInsn(DUP2_X1)
internal inline fun MethodVisitor.dup2_x2() = visitInsn(DUP2_X2)

internal inline fun MethodVisitor.pop() = visitInsn(POP)
internal inline fun MethodVisitor.pop2() = visitInsn(POP2)

internal inline fun MethodVisitor.swap() = visitInsn(SWAP)
