package me.zhanshi123.advancedcleaner.entity;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class EntityCleanTask extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getWorlds().forEach(world -> {
            for (Chunk chunk : world.getLoadedChunks()) {
                List<Entity> entities = Arrays.asList(chunk.getEntities());
                Map<Class<? extends Entity>, Set<? extends Entity>> types = new HashMap<>();
                
            }
        });
    }
}
