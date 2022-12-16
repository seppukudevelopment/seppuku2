package pw.seppuku.client.ecs.systems

import pw.seppuku.client.ecs.components.Keybind
import pw.seppuku.client.ecs.components.Keybind.KeybindAction
import pw.seppuku.ecs.systems.ComponentSystem

class KeybindSystem : ComponentSystem<(key: Int, action: Int, modifiers: Int) -> Unit, Keybind>(
  Keybind::class.java
) {
  override val process: (key: Int, action: Int, modifiers: Int) -> Unit =
    { key, action, modifiers ->
      KeybindAction.fromOrNull(action)?.let { keybindAction ->
        filter { it.key == key && it.modifiers == modifiers }.forEach { it.onAction(keybindAction) }
      }
    }
}
