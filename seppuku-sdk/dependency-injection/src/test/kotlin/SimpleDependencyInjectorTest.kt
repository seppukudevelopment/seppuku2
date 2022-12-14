import pw.seppuku.di.DependencyProvider
import pw.seppuku.di.injectors.SimpleDependencyInjector
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SimpleDependencyInjectorTest {

    private val dependencyInjector = SimpleDependencyInjector()

    @Test
    fun `getOrNull should return x for bound dependency type`() {
        val dependencyType = String::class

        val dependencyProvider = DependencyProvider { "string" }
        dependencyInjector.bind(dependencyType, dependencyProvider)

        val dependency = dependencyInjector.getOrNull(dependencyType)
        assertEquals(dependencyProvider.provide(), dependency)
    }

    @Test
    fun `getOrNull should return null for unbound dependency type`() {
        val dependencyType = String::class

        val dependency = dependencyInjector.getOrNull(dependencyType)

        assertNull(dependency)
    }
}