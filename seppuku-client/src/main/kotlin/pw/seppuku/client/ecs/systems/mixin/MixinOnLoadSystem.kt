package pw.seppuku.client.ecs.systems.mixin

import pw.seppuku.client.ecs.components.mixin.MixinOnLoad
import pw.seppuku.client.ecs.components.mixin.MixinOnLoadCallback
import pw.seppuku.components.Toggle
import pw.seppuku.ecs.archetype
import pw.seppuku.ecs.findComponent
import pw.seppuku.ecs.findComponentOrNull
import pw.seppuku.ecs.systems.ArchetypeSystem

internal class MixinOnLoadSystem : ArchetypeSystem<MixinOnLoadCallback>(
  archetype(MixinOnLoad::class)
) {
  override val process: MixinOnLoadCallback = { mixinPackage ->
    forEach {
      if (it.findComponentOrNull<Toggle>()?.state != false) {
        it.findComponent<MixinOnLoad>().callback(this, mixinPackage)
      }
    }
  }
}
