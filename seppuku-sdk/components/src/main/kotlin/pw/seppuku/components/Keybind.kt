package pw.seppuku.components

data class Keybind(
    val key: Int,
    val modifiers: Int,
    val onAction: (KeybindAction) -> Unit,
) {

    companion object {
        fun onRelease(onReleaseAction: () -> Unit): (KeybindAction) -> Unit = {
            if (it == KeybindAction.RELEASE)
                onReleaseAction()
        }

        fun onPress(onPressAction: () -> Unit): (KeybindAction) -> Unit = {
            if (it == KeybindAction.PRESS)
                onPressAction()
        }

        fun onHold(onHoldAction: () -> Unit): (KeybindAction) -> Unit = {
            if (it == KeybindAction.HOLD)
                onHoldAction()
        }
    }
}