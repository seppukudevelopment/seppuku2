package pw.seppuku.components

import java.util.*

data class UniqueIdentifier(private val uniqueIdentifier: UUID) {

    override fun equals(other: Any?) = when (other) {
        is UUID -> other == uniqueIdentifier
        else -> super.equals(other)
    }

    override fun hashCode() = uniqueIdentifier.hashCode()

    override fun toString() = uniqueIdentifier.toString()
}