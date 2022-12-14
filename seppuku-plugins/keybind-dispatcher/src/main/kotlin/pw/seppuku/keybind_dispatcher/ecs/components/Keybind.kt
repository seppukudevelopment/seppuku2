package pw.seppuku.keybind_dispatcher.ecs.components

import pw.seppuku.keybind_dispatcher.ecs.components.Keybind.KeybindAction.HOLD
import pw.seppuku.keybind_dispatcher.ecs.components.Keybind.KeybindAction.PRESS
import pw.seppuku.keybind_dispatcher.ecs.components.Keybind.KeybindAction.RELEASE

data class Keybind(
  val key: Int,
  val modifiers: Int,
  val onAction: (KeybindAction) -> Unit,
) {

  enum class KeybindAction {
    RELEASE,
    PRESS,
    HOLD;

    internal companion object {
      internal fun fromOrNull(action: Int): KeybindAction? = when (action) {
        0 -> RELEASE
        1 -> PRESS
        2 -> HOLD
        else -> null
      }
    }
  }

  companion object {
    internal fun onRelease(onReleaseAction: () -> Unit): (KeybindAction) -> Unit = {
      if (it == RELEASE) onReleaseAction()
    }

    internal fun onPress(onPressAction: () -> Unit): (KeybindAction) -> Unit = {
      if (it == PRESS) onPressAction()
    }

    internal fun onHold(onHoldAction: () -> Unit): (KeybindAction) -> Unit = {
      if (it == HOLD) onHoldAction()
    }
  }
}
