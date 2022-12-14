package pw.seppuku.ecs.codegen.impl

import net.bytebuddy.dynamic.scaffold.InstrumentedType
import net.bytebuddy.implementation.Implementation
import net.bytebuddy.implementation.bytecode.ByteCodeAppender
import pw.seppuku.ecs.codegen.graph.EntityComponentGraph

internal class EntityFindComponentOrNullImplementation(
    private val entityComponentGraph: EntityComponentGraph
) : Implementation {
    override fun prepare(instrumentedType: InstrumentedType): InstrumentedType =
        instrumentedType

    override fun appender(implementationTarget: Implementation.Target): ByteCodeAppender =
        EntityFindComponentOrNullByteCodeAppender(entityComponentGraph)
}