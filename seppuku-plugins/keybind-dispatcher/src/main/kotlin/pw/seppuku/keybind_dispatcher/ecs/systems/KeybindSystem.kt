package pw.seppuku.keybind_dispatcher.ecs.systems

import pw.seppuku.ecs.systems.ComponentSystem
import pw.seppuku.keybind_dispatcher.ecs.components.Keybind
import pw.seppuku.keybind_dispatcher.ecs.components.Keybind.KeybindAction

class KeybindSystem : ComponentSystem<(key: Int, modifiers: Int, action: Int) -> Unit, Keybind>(
  Keybind::class.java
) {
  override val process: (key: Int, modifiers: Int, action: Int) -> Unit =
    { key, modifiers, action ->
      KeybindAction.fromOrNull(action)?.let { keybindAction ->
        filter { it.key == key && it.modifiers == modifiers }.forEach { it.onAction(keybindAction) }
      }
    }
}
