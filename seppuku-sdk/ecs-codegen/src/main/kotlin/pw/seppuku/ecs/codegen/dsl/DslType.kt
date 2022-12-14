@file:Suppress("NOTHING_TO_INLINE")

package pw.seppuku.ecs.codegen.dsl

import net.bytebuddy.description.type.TypeDescription
import net.bytebuddy.jar.asm.Type
import kotlin.reflect.KClass

internal typealias DslType = Any

internal inline fun DslType.coerce(): Type = when (this) {
  is Type -> this
  is String -> Type.getObjectType(this)
  is Class<*> -> Type.getType(this)
  is KClass<*> -> Type.getType(this.java)
  is TypeDescription -> Type.getType(this.descriptor)
  else -> error("Could not coerce type '${this::class.simpleName}'")
}
