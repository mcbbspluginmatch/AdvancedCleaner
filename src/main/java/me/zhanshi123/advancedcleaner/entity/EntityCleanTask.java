package me.zhanshi123.advancedcleaner.entity;

import me.zhanshi123.advancedcleaner.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class EntityCleanTask extends BukkitRunnable {
    private static void accept(List<Entity> entities) {
        Map<EntityType, List<Entity>> types = new HashMap<>();
        entities.forEach(entity -> {
            EntityType type = entity.getType();
            List<Entity> list = types.get(type);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(entity);
            types.remove(type);
            types.put(type, list);
        });
        Map<EntityType, Integer> limit = Main.getInstance().getConfigManager().getEntityLimit();
        types.forEach((key, value1) -> {
            Integer value = limit.get(key);
            if (value == null) {
                return;
            }
            if (value1.size() < value) {
                return;
            }
            int toClean = value1.size() - value;
            for (int i = 0; i < toClean; i++) {
                value1.get(i).remove();
            }
        });
    }

    @Override
    public void run() {
        Bukkit.getWorlds().stream().flatMap(world -> Arrays.stream(world.getLoadedChunks())).map(chunk -> Arrays.asList(chunk.getEntities())).forEach(EntityCleanTask::accept);
    }
}
