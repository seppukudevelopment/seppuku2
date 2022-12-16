package pw.seppuku.script_host.ecs

import pw.seppuku.ecs.System
import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript
interface ScriptSystem<T> : System<T>
