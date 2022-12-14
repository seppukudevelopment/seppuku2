package pw.seppuku.ecs.codegen.impl

import net.bytebuddy.asm.AsmVisitorWrapper
import net.bytebuddy.description.field.FieldDescription
import net.bytebuddy.description.field.FieldList
import net.bytebuddy.description.method.MethodList
import net.bytebuddy.description.type.TypeDescription
import net.bytebuddy.implementation.Implementation
import net.bytebuddy.jar.asm.ClassVisitor
import net.bytebuddy.jar.asm.ClassWriter
import net.bytebuddy.pool.TypePool

internal object RecomputeMaxesVisitor : AsmVisitorWrapper {
  override fun mergeWriter(flags: Int): Int = flags or ClassWriter.COMPUTE_MAXS

  override fun mergeReader(flags: Int): Int = flags

  override fun wrap(
    instrumentedType: TypeDescription,
    classVisitor: ClassVisitor,
    implementationContext: Implementation.Context,
    typePool: TypePool,
    fields: FieldList<FieldDescription.InDefinedShape>,
    methods: MethodList<*>,
    writerFlags: Int,
    readerFlags: Int
  ): ClassVisitor = classVisitor
}
