package pw.seppuku.components

data class Toggle(
    private val initialState: Boolean = false,
    private val onStateChange: Boolean.() -> Unit = {},
) {
    var state: Boolean = initialState
        set(value) {
            field = value
            onStateChange(value)
        }

    override fun equals(other: Any?) = when (other) {
        is Boolean -> other == state
        else -> super.equals(other)
    }

    override fun hashCode() = state.hashCode()

    override fun toString() = state.toString()
}