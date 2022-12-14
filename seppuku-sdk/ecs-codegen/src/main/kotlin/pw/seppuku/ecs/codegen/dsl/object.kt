@file:Suppress("NOTHING_TO_INLINE")

package pw.seppuku.ecs.codegen.dsl

import net.bytebuddy.jar.asm.MethodVisitor
import net.bytebuddy.jar.asm.Opcodes.ANEWARRAY
import net.bytebuddy.jar.asm.Opcodes.ARRAYLENGTH
import net.bytebuddy.jar.asm.Opcodes.CHECKCAST
import net.bytebuddy.jar.asm.Opcodes.GETFIELD
import net.bytebuddy.jar.asm.Opcodes.GETSTATIC
import net.bytebuddy.jar.asm.Opcodes.INSTANCEOF
import net.bytebuddy.jar.asm.Opcodes.NEW
import net.bytebuddy.jar.asm.Opcodes.NEWARRAY
import net.bytebuddy.jar.asm.Opcodes.PUTFIELD
import net.bytebuddy.jar.asm.Opcodes.PUTSTATIC

internal inline fun MethodVisitor.new(type: DslType) =
  visitTypeInsn(NEW, type.coerce().internalName)

internal inline fun MethodVisitor.arraylength() = visitInsn(ARRAYLENGTH)
internal inline fun MethodVisitor.anewarray(type: DslType) =
  visitTypeInsn(ANEWARRAY, type.coerce().internalName)

internal inline fun MethodVisitor.newarray(count: Int) = visitIntInsn(NEWARRAY, count)
internal inline fun MethodVisitor.multinewarray(
  descriptor: String,
  dimensions: Int
) = visitMultiANewArrayInsn(descriptor, dimensions)

internal inline fun MethodVisitor.getfield(
  owner: String,
  name: String,
  descriptor: String
) = visitFieldInsn(GETFIELD, owner, name, descriptor)

internal inline fun MethodVisitor.putfield(
  owner: String,
  name: String,
  descriptor: String
) = visitFieldInsn(PUTFIELD, owner, name, descriptor)

internal inline fun MethodVisitor.getfield(
  owner: DslType,
  name: String,
  descriptor: DslType
) = visitFieldInsn(GETFIELD, owner.coerce().internalName, name, descriptor.coerce().descriptor)

internal inline fun MethodVisitor.putfield(
  owner: DslType,
  name: String,
  descriptor: DslType
) = visitFieldInsn(PUTFIELD, owner.coerce().internalName, name, descriptor.coerce().descriptor)

internal inline fun MethodVisitor.getstatic(
  owner: String,
  name: String,
  descriptor: String
) = visitFieldInsn(GETSTATIC, owner, name, descriptor)

internal inline fun MethodVisitor.putstatic(
  owner: String,
  name: String,
  descriptor: String
) = visitFieldInsn(PUTSTATIC, owner, name, descriptor)

internal inline fun MethodVisitor.getstatic(
  owner: DslType,
  name: String,
  descriptor: DslType
) = visitFieldInsn(GETSTATIC, owner.coerce().internalName, name, descriptor.coerce().descriptor)

internal inline fun MethodVisitor.putstatic(
  owner: DslType,
  name: String,
  descriptor: DslType
) = visitFieldInsn(PUTSTATIC, owner.coerce().internalName, name, descriptor.coerce().descriptor)

internal inline fun MethodVisitor.checkcast(type: String) = visitTypeInsn(CHECKCAST, type)
internal inline fun MethodVisitor.instanceof(type: String) = visitTypeInsn(INSTANCEOF, type)

internal inline fun MethodVisitor.checkcast(type: DslType) =
  visitTypeInsn(CHECKCAST, type.coerce().internalName)

internal inline fun MethodVisitor.instanceof(type: DslType) =
  visitTypeInsn(INSTANCEOF, type.coerce().internalName)
