package pw.seppuku.ecs.codegen

import net.bytebuddy.ByteBuddy
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy
import net.bytebuddy.matcher.ElementMatchers
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.codegen.graph.buildEntityComponentGraph
import pw.seppuku.ecs.codegen.impl.EntityFindComponentOrNullImplementation
import pw.seppuku.ecs.codegen.impl.EntityHasComponentImplementation
import pw.seppuku.ecs.codegen.impl.RecomputeFramesVisitor
import pw.seppuku.ecs.codegen.impl.RecomputeMaxesVisitor
import java.io.File
import kotlin.reflect.KClass

class EntityCodeGenerator(
  private val generatedClassSavePath: File? = null,
  private val classLoadingStrategy: ClassLoadingStrategy<ClassLoader> = ClassLoadingStrategy.Default.INJECTION
) {
  fun <T : Entity> generateImplementationClass(
    entityClass: KClass<T>,
    classLoader: ClassLoader = this::class.java.classLoader
  ): KClass<out T> {
    val entityComponentGraph = buildEntityComponentGraph(entityClass)

    return ByteBuddy().subclass(entityClass.java)
      .method(ElementMatchers.named("hasComponent"))
      .intercept(EntityHasComponentImplementation(entityComponentGraph))
      .method(ElementMatchers.named("findComponentOrNull"))
      .intercept(EntityFindComponentOrNullImplementation(entityComponentGraph))
      .visit(RecomputeFramesVisitor)
      .visit(RecomputeMaxesVisitor)
      .make()
      .apply {
        if (generatedClassSavePath != null) saveIn(generatedClassSavePath)
      }
      .load(classLoader, classLoadingStrategy).loaded.kotlin
  }
}
