package pw.seppuku.client.ecs.entities

import pw.seppuku.client.ecs.components.minecraft.client.MinecraftClientInitEarly
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Engine
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.mapComponent
import pw.seppuku.settings.config.Config
import kotlin.concurrent.thread

internal abstract class ConfigPersistenceEntity(
  private val engine: Engine
) : Entity {

  @Component val humanIdentifier = HumanIdentifier("config_persistence")

  @Component val minecraftClientInitEarly = MinecraftClientInitEarly {
    engine.mapComponent<Config>().filter(Config::exists).forEach {
      it.readFromFile()
    }

    Runtime.getRuntime().addShutdownHook(thread(start = false) {
      engine.mapComponent<Config>().forEach { it.writeToFile() }
    })
  }
}
