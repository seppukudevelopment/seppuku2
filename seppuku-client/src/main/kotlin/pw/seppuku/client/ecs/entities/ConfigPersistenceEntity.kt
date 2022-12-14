package pw.seppuku.client.ecs.entities

import pw.seppuku.client.Seppuku
import pw.seppuku.client.ecs.components.minecraft.client.MinecraftClientInitEarly
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.ecs.Component
import pw.seppuku.ecs.Entity
import pw.seppuku.ecs.mapComponent
import pw.seppuku.settings.config.Config
import kotlin.concurrent.thread

internal abstract class ConfigPersistenceEntity : Entity {

  @Component val humanIdentifier = HumanIdentifier("config_persistence")

  @Component val minecraftClientInitEarly = MinecraftClientInitEarly {
    Seppuku.engine.mapComponent<Config>().filter(Config::exists).forEach {
      println("Loading ${it.file.name}")
      it.readFromFile()
    }

    Runtime.getRuntime().addShutdownHook(thread(start = false) {
      Seppuku.engine.mapComponent<Config>().forEach { it.writeToFile() }
    })
  }
}
