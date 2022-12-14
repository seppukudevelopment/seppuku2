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

internal fun MethodVisitor.dadd() = visitInsn(DADD)
internal fun MethodVisitor.dsub() = visitInsn(DSUB)
internal fun MethodVisitor.dmul() = visitInsn(DMUL)
internal fun MethodVisitor.ddiv() = visitInsn(DDIV)
internal fun MethodVisitor.drem() = visitInsn(DREM)
internal fun MethodVisitor.dneg() = visitInsn(DNEG)
internal fun MethodVisitor.dcmpg() = visitInsn(DCMPG)
internal fun MethodVisitor.dcmpl() = visitInsn(DCMPL)

internal fun MethodVisitor.fadd() = visitInsn(FADD)
internal fun MethodVisitor.fsub() = visitInsn(FSUB)
internal fun MethodVisitor.fmul() = visitInsn(FMUL)
internal fun MethodVisitor.fdiv() = visitInsn(FDIV)
internal fun MethodVisitor.frem() = visitInsn(FREM)
internal fun MethodVisitor.fneg() = visitInsn(FNEG)
internal fun MethodVisitor.fcmpg() = visitInsn(FCMPG)
internal fun MethodVisitor.fcmpl() = visitInsn(FCMPL)

internal fun MethodVisitor.iadd() = visitInsn(IADD)
internal fun MethodVisitor.isub() = visitInsn(ISUB)
internal fun MethodVisitor.imul() = visitInsn(IMUL)
internal fun MethodVisitor.idiv() = visitInsn(IDIV)
internal fun MethodVisitor.irem() = visitInsn(IREM)
internal fun MethodVisitor.ineg() = visitInsn(INEG)
internal fun MethodVisitor.ishl() = visitInsn(ISHL)
internal fun MethodVisitor.ishr() = visitInsn(ISHR)
internal fun MethodVisitor.iushr() = visitInsn(IUSHR)
internal fun MethodVisitor.ior() = visitInsn(IOR)
internal fun MethodVisitor.iand() = visitInsn(IAND)
internal fun MethodVisitor.ixor() = visitInsn(IXOR)

internal fun MethodVisitor.ladd() = visitInsn(LADD)
internal fun MethodVisitor.lsub() = visitInsn(LSUB)
internal fun MethodVisitor.lmul() = visitInsn(LMUL)
internal fun MethodVisitor.ldiv() = visitInsn(LDIV)
internal fun MethodVisitor.lrem() = visitInsn(LREM)
internal fun MethodVisitor.lneg() = visitInsn(LNEG)
internal fun MethodVisitor.lshl() = visitInsn(LSHL)
internal fun MethodVisitor.lshr() = visitInsn(LSHR)
internal fun MethodVisitor.lushr() = visitInsn(LUSHR)
internal fun MethodVisitor.lor() = visitInsn(LOR)
internal fun MethodVisitor.land() = visitInsn(LAND)
internal fun MethodVisitor.lxor() = visitInsn(LXOR)
internal fun MethodVisitor.lcmp() = visitInsn(LCMP)
