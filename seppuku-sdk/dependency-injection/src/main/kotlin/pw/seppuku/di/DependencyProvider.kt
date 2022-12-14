package pw.seppuku.di

fun interface DependencyProvider<T : Any> {

    fun provide(): T?

    companion object {
        fun <T : Any> singleton(instance: T): DependencyProvider<T> {
            return DependencyProvider { instance }
        }
    }
}