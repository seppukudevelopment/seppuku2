package pw.seppuku.settings.config

import pw.seppuku.settings.SettingDelegateProperty

fun <K, V : Any> Config.setting(
  path: String,
  defaultValue: V,
  changeCallback: ((V) -> Unit)? = null
): SettingDelegateProperty<K, V> = SettingDelegateProperty(this, path, defaultValue, changeCallback)
