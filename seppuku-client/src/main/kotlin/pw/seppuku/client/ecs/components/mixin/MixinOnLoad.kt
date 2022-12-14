package pw.seppuku.client.ecs.components.mixin

import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin

internal typealias MixinOnLoadCallback = IMixinConfigPlugin.(mixinPackage: String?) -> Unit

internal data class MixinOnLoad(val callback: MixinOnLoadCallback)