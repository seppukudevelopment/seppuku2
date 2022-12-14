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

internal fun MethodVisitor.invokeinterface(
  owner: DslType,
  name: String,
  descriptor: String
) = visitMethodInsn(INVOKEINTERFACE, owner.coerce().internalName, name, descriptor, true)

internal fun MethodVisitor.invokevirtual(
  owner: DslType,
  name: String,
  descriptor: String,
  isInterface: Boolean = false
) = visitMethodInsn(INVOKEVIRTUAL, owner.coerce().internalName, name, descriptor, isInterface)

internal fun MethodVisitor.invokespecial(
  owner: DslType,
  name: String,
  descriptor: String,
  isInterface: Boolean = false
) = visitMethodInsn(INVOKESPECIAL, owner.coerce().internalName, name, descriptor, isInterface)

internal fun MethodVisitor.invokestatic(
  owner: DslType,
  name: String,
  descriptor: String,
  isInterface: Boolean = false
) = visitMethodInsn(INVOKESTATIC, owner.coerce().internalName, name, descriptor, isInterface)

internal fun MethodVisitor.invokeinterface(
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

internal fun MethodVisitor.invokevirtual(
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

internal fun MethodVisitor.invokespecial(
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

internal fun MethodVisitor.invokestatic(
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

internal fun MethodVisitor._return() = visitInsn(RETURN)
internal fun MethodVisitor.areturn() = visitInsn(ARETURN)
internal fun MethodVisitor.dreturn() = visitInsn(DRETURN)
internal fun MethodVisitor.freturn() = visitInsn(FRETURN)
internal fun MethodVisitor.ireturn() = visitInsn(IRETURN)
internal fun MethodVisitor.lreturn() = visitInsn(LRETURN)
