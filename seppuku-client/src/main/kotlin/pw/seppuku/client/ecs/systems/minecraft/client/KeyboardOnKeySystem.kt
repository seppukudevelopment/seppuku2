package pw.seppuku.client.ecs.systems.minecraft.client

import pw.seppuku.client.ecs.components.minecraft.client.KeyboardOnKey
import pw.seppuku.client.ecs.components.minecraft.client.KeyboardOnKeyCallback
import pw.seppuku.client.ecs.systems.KeybindSystem
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.*
import pw.seppuku.ecs.systems.ArchetypeSystem

class KeyboardOnKeySystem(
  engine: Engine
) : ArchetypeSystem<KeyboardOnKeyCallback>(archetype(KeyboardOnKey::class)) {
  private val keybindSystem: KeybindSystem by lazy { engine.findSystem() }

  override val process: KeyboardOnKeyCallback = { window, key, scanCode, action, modifiers ->
    keybindSystem.process(key, action, modifiers)

    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<KeyboardOnKey>().callback(this, window, key, scanCode, action, modifiers)
      }
    }
  }
}
