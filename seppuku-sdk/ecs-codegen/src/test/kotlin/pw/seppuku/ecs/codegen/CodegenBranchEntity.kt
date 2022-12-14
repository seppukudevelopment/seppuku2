package pw.seppuku.ecs.codegen

import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity

open class CodegenBranchEntity : Entity {
  @Component
  val velocity = Velocity(0f, 0f, 0f)
}
