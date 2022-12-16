package pw.seppuku.components

import java.io.Serializable

data class Toggle(
  private val initialState: Boolean = false,
  @Transient private val onStateChange: Boolean.() -> Unit = {}
) : Serializable {
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
