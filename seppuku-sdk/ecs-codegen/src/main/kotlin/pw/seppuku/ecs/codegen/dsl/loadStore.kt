package pw.seppuku.ecs.codegen.dsl

import net.bytebuddy.jar.asm.MethodVisitor
import net.bytebuddy.jar.asm.Opcodes.*

internal fun MethodVisitor.aaload() = visitInsn(AALOAD)
internal fun MethodVisitor.aastore() = visitInsn(AASTORE)
internal fun MethodVisitor.aconst_null() = visitInsn(ACONST_NULL)
internal fun MethodVisitor.aload(index: Int) = visitVarInsn(ALOAD, index)
internal fun MethodVisitor.astore(index: Int) = visitVarInsn(ASTORE, index)

internal fun MethodVisitor.baload() = visitInsn(BALOAD)
internal fun MethodVisitor.bastore() = visitInsn(BASTORE)
internal fun MethodVisitor.bipush(operand: Int) = visitIntInsn(BIPUSH, operand)

internal fun MethodVisitor.caload() = visitInsn(CALOAD)
internal fun MethodVisitor.castore() = visitInsn(CASTORE)

internal fun MethodVisitor.daload() = visitInsn(DALOAD)
internal fun MethodVisitor.dastore() = visitInsn(DASTORE)
internal fun MethodVisitor.dconst_0() = visitInsn(DCONST_0)
internal fun MethodVisitor.dconst_1() = visitInsn(DCONST_1)
internal fun MethodVisitor.dload(index: Int) = visitVarInsn(DLOAD, index)
internal fun MethodVisitor.dstore(index: Int) = visitVarInsn(DSTORE, index)

internal fun MethodVisitor.faload() = visitInsn(FALOAD)
internal fun MethodVisitor.fastore() = visitInsn(FASTORE)
internal fun MethodVisitor.fconst_0() = visitInsn(FCONST_0)
internal fun MethodVisitor.fconst_1() = visitInsn(FCONST_1)
internal fun MethodVisitor.fconst_2() = visitInsn(FCONST_2)
internal fun MethodVisitor.fload(index: Int) = visitVarInsn(FLOAD, index)
internal fun MethodVisitor.fstore(index: Int) = visitVarInsn(FSTORE, index)

internal fun MethodVisitor.iaload() = visitInsn(IALOAD)
internal fun MethodVisitor.iastore() = visitInsn(IASTORE)
internal fun MethodVisitor.iconst_0() = visitInsn(ICONST_0)
internal fun MethodVisitor.iconst_1() = visitInsn(ICONST_1)
internal fun MethodVisitor.iconst_2() = visitInsn(ICONST_2)
internal fun MethodVisitor.iconst_3() = visitInsn(ICONST_3)
internal fun MethodVisitor.iconst_4() = visitInsn(ICONST_4)
internal fun MethodVisitor.iconst_5() = visitInsn(ICONST_5)
internal fun MethodVisitor.iload(index: Int) = visitVarInsn(ILOAD, index)
internal fun MethodVisitor.istore(index: Int) = visitVarInsn(ISTORE, index)

internal fun MethodVisitor.laload() = visitInsn(LALOAD)
internal fun MethodVisitor.lastore() = visitInsn(LASTORE)
internal fun MethodVisitor.lload(index: Int) = visitVarInsn(LLOAD, index)
internal fun MethodVisitor.lstore(index: Int) = visitVarInsn(LSTORE, index)
internal fun MethodVisitor.lconst_0() = visitInsn(LCONST_0)
internal fun MethodVisitor.lconst_1() = visitInsn(LCONST_1)

internal fun MethodVisitor.saload() = visitInsn(AALOAD)
internal fun MethodVisitor.sastore() = visitInsn(AASTORE)
internal fun MethodVisitor.sipush(operand: Int) = visitIntInsn(SIPUSH, operand)

internal fun MethodVisitor.ldc(value: DslType) = visitLdcInsn(value.coerce())
