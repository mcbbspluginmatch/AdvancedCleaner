package me.zhanshi123.advancedcleaner.item;

import me.zhanshi123.advancedcleaner.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectDropsTask extends BukkitRunnable {
    @Override
    public void run() {
        Map<World, Set<Item>> worldItem = new HashMap<>();
        Bukkit.getWorlds().forEach(world -> {
            Set<Item> itemSet = world.getEntitiesByClass(Item.class).stream().collect(Collectors.toSet());
            worldItem.put(world, itemSet);
        });
        new AnalyzeValueTask(worldItem).runTaskAsynchronously(Main.getInstance());
    }
}
