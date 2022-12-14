@file:Suppress("NOTHING_TO_INLINE")

package pw.seppuku.ecs.codegen.dsl

import net.bytebuddy.jar.asm.MethodVisitor
import net.bytebuddy.jar.asm.Opcodes.DADD
import net.bytebuddy.jar.asm.Opcodes.DCMPG
import net.bytebuddy.jar.asm.Opcodes.DCMPL
import net.bytebuddy.jar.asm.Opcodes.DDIV
import net.bytebuddy.jar.asm.Opcodes.DMUL
import net.bytebuddy.jar.asm.Opcodes.DNEG
import net.bytebuddy.jar.asm.Opcodes.DREM
import net.bytebuddy.jar.asm.Opcodes.DSUB
import net.bytebuddy.jar.asm.Opcodes.FADD
import net.bytebuddy.jar.asm.Opcodes.FCMPG
import net.bytebuddy.jar.asm.Opcodes.FCMPL
import net.bytebuddy.jar.asm.Opcodes.FDIV
import net.bytebuddy.jar.asm.Opcodes.FMUL
import net.bytebuddy.jar.asm.Opcodes.FNEG
import net.bytebuddy.jar.asm.Opcodes.FREM
import net.bytebuddy.jar.asm.Opcodes.FSUB
import net.bytebuddy.jar.asm.Opcodes.IADD
import net.bytebuddy.jar.asm.Opcodes.IAND
import net.bytebuddy.jar.asm.Opcodes.IDIV
import net.bytebuddy.jar.asm.Opcodes.IMUL
import net.bytebuddy.jar.asm.Opcodes.INEG
import net.bytebuddy.jar.asm.Opcodes.IOR
import net.bytebuddy.jar.asm.Opcodes.IREM
import net.bytebuddy.jar.asm.Opcodes.ISHL
import net.bytebuddy.jar.asm.Opcodes.ISHR
import net.bytebuddy.jar.asm.Opcodes.ISUB
import net.bytebuddy.jar.asm.Opcodes.IUSHR
import net.bytebuddy.jar.asm.Opcodes.IXOR
import net.bytebuddy.jar.asm.Opcodes.LADD
import net.bytebuddy.jar.asm.Opcodes.LAND
import net.bytebuddy.jar.asm.Opcodes.LCMP
import net.bytebuddy.jar.asm.Opcodes.LDIV
import net.bytebuddy.jar.asm.Opcodes.LMUL
import net.bytebuddy.jar.asm.Opcodes.LNEG
import net.bytebuddy.jar.asm.Opcodes.LOR
import net.bytebuddy.jar.asm.Opcodes.LREM
import net.bytebuddy.jar.asm.Opcodes.LSHL
import net.bytebuddy.jar.asm.Opcodes.LSHR
import net.bytebuddy.jar.asm.Opcodes.LSUB
import net.bytebuddy.jar.asm.Opcodes.LUSHR
import net.bytebuddy.jar.asm.Opcodes.LXOR

internal inline fun MethodVisitor.dadd() = visitInsn(DADD)
internal inline fun MethodVisitor.dsub() = visitInsn(DSUB)
internal inline fun MethodVisitor.dmul() = visitInsn(DMUL)
internal inline fun MethodVisitor.ddiv() = visitInsn(DDIV)
internal inline fun MethodVisitor.drem() = visitInsn(DREM)
internal inline fun MethodVisitor.dneg() = visitInsn(DNEG)
internal inline fun MethodVisitor.dcmpg() = visitInsn(DCMPG)
internal inline fun MethodVisitor.dcmpl() = visitInsn(DCMPL)

internal inline fun MethodVisitor.fadd() = visitInsn(FADD)
internal inline fun MethodVisitor.fsub() = visitInsn(FSUB)
internal inline fun MethodVisitor.fmul() = visitInsn(FMUL)
internal inline fun MethodVisitor.fdiv() = visitInsn(FDIV)
internal inline fun MethodVisitor.frem() = visitInsn(FREM)
internal inline fun MethodVisitor.fneg() = visitInsn(FNEG)
internal inline fun MethodVisitor.fcmpg() = visitInsn(FCMPG)
internal inline fun MethodVisitor.fcmpl() = visitInsn(FCMPL)

internal inline fun MethodVisitor.iadd() = visitInsn(IADD)
internal inline fun MethodVisitor.isub() = visitInsn(ISUB)
internal inline fun MethodVisitor.imul() = visitInsn(IMUL)
internal inline fun MethodVisitor.idiv() = visitInsn(IDIV)
internal inline fun MethodVisitor.irem() = visitInsn(IREM)
internal inline fun MethodVisitor.ineg() = visitInsn(INEG)
internal inline fun MethodVisitor.ishl() = visitInsn(ISHL)
internal inline fun MethodVisitor.ishr() = visitInsn(ISHR)
internal inline fun MethodVisitor.iushr() = visitInsn(IUSHR)
internal inline fun MethodVisitor.ior() = visitInsn(IOR)
internal inline fun MethodVisitor.iand() = visitInsn(IAND)
internal inline fun MethodVisitor.ixor() = visitInsn(IXOR)

internal inline fun MethodVisitor.ladd() = visitInsn(LADD)
internal inline fun MethodVisitor.lsub() = visitInsn(LSUB)
internal inline fun MethodVisitor.lmul() = visitInsn(LMUL)
internal inline fun MethodVisitor.ldiv() = visitInsn(LDIV)
internal inline fun MethodVisitor.lrem() = visitInsn(LREM)
internal inline fun MethodVisitor.lneg() = visitInsn(LNEG)
internal inline fun MethodVisitor.lshl() = visitInsn(LSHL)
internal inline fun MethodVisitor.lshr() = visitInsn(LSHR)
internal inline fun MethodVisitor.lushr() = visitInsn(LUSHR)
internal inline fun MethodVisitor.lor() = visitInsn(LOR)
internal inline fun MethodVisitor.land() = visitInsn(LAND)
internal inline fun MethodVisitor.lxor() = visitInsn(LXOR)
internal inline fun MethodVisitor.lcmp() = visitInsn(LCMP)
