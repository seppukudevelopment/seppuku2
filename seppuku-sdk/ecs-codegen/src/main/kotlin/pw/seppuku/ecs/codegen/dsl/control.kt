package pw.seppuku.ecs.codegen.dsl

import net.bytebuddy.jar.asm.Label
import net.bytebuddy.jar.asm.MethodVisitor
import net.bytebuddy.jar.asm.Opcodes.GOTO
import net.bytebuddy.jar.asm.Opcodes.IFEQ
import net.bytebuddy.jar.asm.Opcodes.IFGE
import net.bytebuddy.jar.asm.Opcodes.IFGT
import net.bytebuddy.jar.asm.Opcodes.IFLE
import net.bytebuddy.jar.asm.Opcodes.IFLT
import net.bytebuddy.jar.asm.Opcodes.IFNE
import net.bytebuddy.jar.asm.Opcodes.IFNONNULL
import net.bytebuddy.jar.asm.Opcodes.IFNULL
import net.bytebuddy.jar.asm.Opcodes.IF_ACMPEQ
import net.bytebuddy.jar.asm.Opcodes.IF_ACMPNE
import net.bytebuddy.jar.asm.Opcodes.IF_ICMPEQ
import net.bytebuddy.jar.asm.Opcodes.IF_ICMPGE
import net.bytebuddy.jar.asm.Opcodes.IF_ICMPGT
import net.bytebuddy.jar.asm.Opcodes.IF_ICMPLE
import net.bytebuddy.jar.asm.Opcodes.IF_ICMPLT
import net.bytebuddy.jar.asm.Opcodes.IF_ICMPNE
import net.bytebuddy.jar.asm.Opcodes.JSR
import net.bytebuddy.jar.asm.Opcodes.RET

internal fun MethodVisitor.label(label: Label) = visitLabel(label)

internal fun MethodVisitor.goto(label: Label) = visitJumpInsn(GOTO, label)
internal fun MethodVisitor.jsr(label: Label) = visitJumpInsn(JSR, label)

internal fun MethodVisitor.ret(index: Int) = visitVarInsn(RET, index)

internal fun MethodVisitor.if_icmpeq(label: Label) = visitJumpInsn(IF_ICMPEQ, label)
internal fun MethodVisitor.if_icmpne(label: Label) = visitJumpInsn(IF_ICMPNE, label)
internal fun MethodVisitor.if_icmpgt(label: Label) = visitJumpInsn(IF_ICMPGT, label)
internal fun MethodVisitor.if_icmpge(label: Label) = visitJumpInsn(IF_ICMPGE, label)
internal fun MethodVisitor.if_icmplt(label: Label) = visitJumpInsn(IF_ICMPLT, label)
internal fun MethodVisitor.if_icmple(label: Label) = visitJumpInsn(IF_ICMPLE, label)

internal fun MethodVisitor.if_acmpeq(label: Label) = visitJumpInsn(IF_ACMPEQ, label)
internal fun MethodVisitor.if_acmpne(label: Label) = visitJumpInsn(IF_ACMPNE, label)

internal fun MethodVisitor.ifeq(label: Label) = visitJumpInsn(IFEQ, label)
internal fun MethodVisitor.ifne(label: Label) = visitJumpInsn(IFNE, label)
internal fun MethodVisitor.ifgt(label: Label) = visitJumpInsn(IFGT, label)
internal fun MethodVisitor.ifge(label: Label) = visitJumpInsn(IFGE, label)
internal fun MethodVisitor.iflt(label: Label) = visitJumpInsn(IFLT, label)
internal fun MethodVisitor.ifle(label: Label) = visitJumpInsn(IFLE, label)

internal fun MethodVisitor.ifnull(label: Label) = visitJumpInsn(IFNULL, label)
internal fun MethodVisitor.ifnonnull(label: Label) = visitJumpInsn(IFNONNULL, label)
