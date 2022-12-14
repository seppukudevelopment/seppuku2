package pw.seppuku.components

data class HumanIdentifier(private val humanIdentifier: String) {

    override fun equals(other: Any?) = when (other) {
        is String -> other == humanIdentifier
        else -> super.equals(other)
    }

    override fun hashCode() = humanIdentifier.hashCode()

    override fun toString() = humanIdentifier
}