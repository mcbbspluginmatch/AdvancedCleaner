package me.zhanshi123.advancedcleaner.entity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class EntityCleanTask extends BukkitRunnable {
    private static void accept(List<Entity> entities) {
        Map<EntityType, Set<Entity>> types = new HashMap<>();
        entities.forEach(entity -> {
            EntityType type = entity.getType();
            Set<Entity> set = types.get(type);
            if (set == null) {
                set = new HashSet<>();
            }
            set.add(entity);
            types.remove(type);
            types.put(type, set);
        });
        //TODO clean mobs
    }

    @Override
    public void run() {
        Bukkit.getWorlds().stream().flatMap(world -> Arrays.stream(world.getLoadedChunks())).map(chunk -> Arrays.asList(chunk.getEntities())).forEach(EntityCleanTask::accept);
    }
}
