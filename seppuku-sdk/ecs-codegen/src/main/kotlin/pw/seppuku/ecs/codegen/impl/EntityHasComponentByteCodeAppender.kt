package pw.seppuku.ecs.codegen.impl

import net.bytebuddy.description.method.MethodDescription
import net.bytebuddy.implementation.Implementation
import net.bytebuddy.implementation.bytecode.ByteCodeAppender
import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size
import net.bytebuddy.jar.asm.Label
import net.bytebuddy.jar.asm.MethodVisitor
import pw.seppuku.ecs.codegen.dsl.*
import pw.seppuku.ecs.codegen.graph.EntityComponentGraph

internal class EntityHasComponentByteCodeAppender(
    private val entityComponentGraph: EntityComponentGraph
) : ByteCodeAppender {
    override fun apply(
        methodVisitor: MethodVisitor,
        implementationContext: Implementation.Context,
        instrumentedMethod: MethodDescription
    ): Size = methodVisitor.run {
        entityComponentGraph.reversed().forEach {
            val exit = Label()

            aload(1)
            it.matchWithExit(this, exit) {
                iconst_1()
                ireturn()
            }

            label(exit)
        }

        iconst_0()
        ireturn()

        return Size(0, 0)
    }
}