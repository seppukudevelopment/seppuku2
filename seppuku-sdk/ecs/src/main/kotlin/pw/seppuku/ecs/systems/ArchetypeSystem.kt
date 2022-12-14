package pw.seppuku.ecs.systems

import pw.seppuku.ecs.Archetype
import pw.seppuku.ecs.Entity

abstract class ArchetypeSystem<T>(
    archetype: Archetype,
    entities: MutableList<Entity> = mutableListOf()
) : EntitySystem<T>(archetype.buildEntityPredicate(), entities)