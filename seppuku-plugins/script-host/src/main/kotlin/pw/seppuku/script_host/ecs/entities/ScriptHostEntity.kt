package pw.seppuku.script_host.ecs.entities

import pw.seppuku.common_systems.ecs.components.minecraft.client.MinecraftClientInit
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.script_host.SeppukuScriptHost
import kotlin.io.path.Path

abstract class ScriptHostEntity : Entity {

  @Component
  val humanIdentifier = HumanIdentifier("script_host")

  @Component
  val minecraftClientInit = MinecraftClientInit {
    val result = SeppukuScriptHost.eval(Path("seppuku", "scripts", "test.kts"))
    result.reports.forEach {
      println(it)
    }
  }
}
