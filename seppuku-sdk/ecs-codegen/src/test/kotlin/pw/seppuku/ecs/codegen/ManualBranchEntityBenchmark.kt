/*
 *     Copyright (C) 2022  Seppuku Development
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
