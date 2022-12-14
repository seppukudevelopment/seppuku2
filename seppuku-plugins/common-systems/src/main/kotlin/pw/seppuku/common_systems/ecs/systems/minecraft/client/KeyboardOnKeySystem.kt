package pw.seppuku.common_systems.ecs.systems.minecraft.client

import pw.seppuku.common_systems.ecs.components.minecraft.client.KeyboardOnKey
import pw.seppuku.common_systems.ecs.components.minecraft.client.KeyboardOnKeyCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

class KeyboardOnKeySystem : ArchetypeSystem<KeyboardOnKeyCallback>(archetype(KeyboardOnKey::class)) {
  override val process: KeyboardOnKeyCallback = { window, key, scanCode, action, modifiers ->
    val keyboard = this

    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<KeyboardOnKey>()
          .callback(keyboard, window, key, scanCode, action, modifiers)
      }
    }
  }
}
