package pw.seppuku.common_systems.ecs.components.minecraft.client

import net.minecraft.client.Keyboard

typealias KeyboardOnKeyCallback = Keyboard.(window: Long, key: Int, scanCode: Int, action: Int, modifiers: Int) -> Unit

data class KeyboardOnKey(val callback: KeyboardOnKeyCallback)