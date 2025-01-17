package org.bukkit.craftbukkit.block;

import net.minecraft.tileentity.TileEntityMobSpawner;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.EntityType;

public class CraftCreatureSpawner extends CraftBlockState implements CreatureSpawner {
    private final TileEntityMobSpawner spawner;

    public CraftCreatureSpawner(final Block block) {
        super(block);

        spawner = (TileEntityMobSpawner) ((CraftWorld) block.getWorld()).getTileEntityAt(getX(), getY(), getZ());
    }

    @Deprecated
    public CreatureType getCreatureType() {
        return CreatureType.fromName(spawner.func_145881_a().getEntityNameToSpawn());
    }

    public EntityType getSpawnedType() {
        return EntityType.fromName(spawner.func_145881_a().getEntityNameToSpawn());
    }

    @Deprecated
    public void setCreatureType(CreatureType creatureType) {
        spawner.func_145881_a().setEntityName(creatureType.getName());
    }

    public void setSpawnedType(EntityType entityType) {
        if (entityType == null || entityType.getName() == null) {
            throw new IllegalArgumentException("Can't spawn EntityType " + entityType + " from mobspawners!");
        }

        spawner.func_145881_a().setEntityName(entityType.getName());
    }

    @Deprecated
    public String getCreatureTypeId() {
        return spawner.func_145881_a().getEntityNameToSpawn();
    }

    @Deprecated
    public void setCreatureTypeId(String creatureName) {
        setCreatureTypeByName(creatureName);
    }

    public String getCreatureTypeName() {
        return spawner.func_145881_a().getEntityNameToSpawn();
    }

    public void setCreatureTypeByName(String creatureType) {
        // Verify input
        EntityType type = EntityType.fromName(creatureType);
        if (type == null) {
            return;
        }
        setSpawnedType(type);
    }

    public int getDelay() {
        return spawner.func_145881_a().spawnDelay;
    }

    public void setDelay(int delay) {
        spawner.func_145881_a().spawnDelay = delay;
    }

}
