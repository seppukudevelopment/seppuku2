package pw.seppuku.components

enum class KeybindAction {
    RELEASE,
    PRESS,
    HOLD;

    companion object {
        fun fromOrNull(code: Int): KeybindAction? =
            when (code) {
                0 -> RELEASE
                1 -> PRESS
                2 -> HOLD
                else -> null
            }
    }
}