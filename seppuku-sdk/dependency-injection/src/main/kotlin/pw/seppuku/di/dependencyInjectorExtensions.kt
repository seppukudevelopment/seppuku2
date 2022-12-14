package pw.seppuku.di

inline fun <reified T : Any> DependencyInjector.getOrNull(): T? = getOrNull(T::class)

inline fun <reified T : Any> DependencyInjector.createOrNull(): T? = createOrNull(T::class)

inline fun <reified T : Any> DependencyInjector.get(): T = get(T::class)

inline fun <reified T : Any> DependencyInjector.create(): T = create(T::class)