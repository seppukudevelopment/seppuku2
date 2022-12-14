package pw.seppuku.client.mixin

import pw.seppuku.client.Seppuku
import pw.seppuku.client.ecs.systems.mixin.MixinOnLoadSystem
import pw.seppuku.ecs.findSystem

internal class SeppukuMixinConfigPlugin : AbstractMixinConfigPlugin() {
  override fun onLoad(mixinPackage: String?) {
    Seppuku.engine.findSystem<MixinOnLoadSystem>().process(this, mixinPackage)
    super.onLoad(mixinPackage)
  }
}
