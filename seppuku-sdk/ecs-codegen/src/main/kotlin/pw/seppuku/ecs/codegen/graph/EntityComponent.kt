package pw.seppuku.ecs.codegen.graph

import net.bytebuddy.jar.asm.Label
import net.bytebuddy.jar.asm.MethodVisitor
import pw.seppuku.ecs.codegen.dsl.if_acmpne
import pw.seppuku.ecs.codegen.dsl.ldc
import kotlin.reflect.KClass

internal data class EntityComponent(
    val componentClass: KClass<*>,
    val componentOwner: KClass<*>,
    val componentFieldName: String,
    val componentGetterName: String,
) {

    internal fun matchWithExit(methodVisitor: MethodVisitor, exit: Label, matching: MethodVisitor.() -> Unit) =
        methodVisitor.run {
            ldc(componentClass)
            if_acmpne(exit)

            matching()
        }
}