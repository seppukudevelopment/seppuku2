package pw.seppuku.keybind_system.ecs.components

import pw.seppuku.keybind_system.ecs.components.Keybind.Companion

fun onRelease(
  key: Int,
  modifiers: Int? = null,
  onReleaseAction: () -> Unit
): Keybind = Keybind(key, modifiers ?: 0, Keybind.onRelease(onReleaseAction))

fun onPress(
  key: Int,
  modifiers: Int? = null,
  onPressAction: () -> Unit
): Keybind = Keybind(key, modifiers ?: 0, Keybind.onPress(onPressAction))

fun onHold(
  key: Int,
  modifiers: Int? = null,
  onHoldAction: () -> Unit
): Keybind = Keybind(key, modifiers ?: 0, Keybind.onHold(onHoldAction))
