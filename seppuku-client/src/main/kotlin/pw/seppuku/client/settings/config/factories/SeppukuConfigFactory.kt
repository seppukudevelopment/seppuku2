package pw.seppuku.client.settings.config.factories

import pw.seppuku.settings.config.configs.ObjectConfig
import pw.seppuku.settings.config.factories.CascadingConfigFactory

internal class SeppukuConfigFactory : CascadingConfigFactory<ObjectConfig>(
  ObjectConfig::class, "seppuku/config", "dat"
)
