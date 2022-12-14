package pw.seppuku.settings

import pw.seppuku.settings.config.Config
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SettingDelegateProperty<K, V : Any>(
  private val config: Config,
  private val path: String,
  private val defaultValue: V,
  private val changeCallback: ((V) -> Unit)? = null
) : ReadWriteProperty<K, V> {

  override fun getValue(
    thisRef: K,
    property: KProperty<*>
  ): V {
    val value = config.read<V>(path)
    if (value != null)
      return value

    config.write(path, defaultValue)
    return defaultValue
  }

  override fun setValue(
    thisRef: K,
    property: KProperty<*>,
    value: V
  ) {
    config.write(path, value)
    changeCallback?.invoke(value)
  }
}
