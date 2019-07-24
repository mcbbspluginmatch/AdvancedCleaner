package me.zhanshi123.advancedcleaner.item;

import me.zhanshi123.advancedcleaner.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollectDropsTask extends BukkitRunnable {
    @Override
    public void run() {
        Map<World, Set<Item>> worldItem = new HashMap<>();
        Bukkit.getWorlds().forEach(world -> {
            Set<Item> itemSet = new HashSet<>(world.getEntitiesByClass(Item.class));
            worldItem.put(world, itemSet);
        });
        new AnalyzeValueTask(worldItem).runTaskAsynchronously(Main.getInstance());
    }
}
