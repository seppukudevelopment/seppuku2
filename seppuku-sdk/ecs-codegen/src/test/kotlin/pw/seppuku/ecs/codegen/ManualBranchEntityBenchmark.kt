package pw.seppuku.ecs.codegen

import kotlinx.benchmark.*
import org.openjdk.jmh.annotations.Benchmark
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.hasComponent

@State(Scope.Benchmark)
open class ManualBranchEntityBenchmark {

  private lateinit var entity: Entity

  @Setup
  fun setup() {
    entity = ManualBranchEntity()
  }

  @Benchmark
  fun hasComponentByClassBenchmark(blackHole: Blackhole) {
    val hasComponent = entity.hasComponent(Velocity::class.java)
    blackHole.consume(hasComponent)
  }

  @Benchmark
  fun hasComponentByKClassBenchmark(blackHole: Blackhole) {
    val hasComponent = entity.hasComponent(Velocity::class)
    blackHole.consume(hasComponent)
  }

  @Benchmark
  fun hasComponentByGenericBenchmark(blackHole: Blackhole) {
    val hasComponent = entity.hasComponent<Velocity>()
    blackHole.consume(hasComponent)
  }

  @Benchmark
  fun findComponentByClassBenchmark(blackHole: Blackhole) {
    val component = entity.findComponentOrNull(Velocity::class.java)
    blackHole.consume(component)
  }

  @Benchmark
  fun findComponentByKClassBenchmark(blackHole: Blackhole) {
    val component = entity.findComponentOrNull(Velocity::class)
    blackHole.consume(component)
  }

  @Benchmark
  fun findComponentByGenericBenchmark(blackHole: Blackhole) {
    val component = entity.findComponentOrNull<Velocity>()
    blackHole.consume(component)
  }
}
