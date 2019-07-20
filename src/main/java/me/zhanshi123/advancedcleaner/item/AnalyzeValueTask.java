package me.zhanshi123.advancedcleaner.item;

import me.zhanshi123.advancedcleaner.Main;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnalyzeValueTask extends BukkitRunnable {
    private Map<World, Set<Item>> data;

    public AnalyzeValueTask(Map<World, Set<Item>> data) {
        this.data = data;
    }

    @Override
    public void run() {
        Set<Item> cleanItem = new HashSet<>();
        data.entrySet().forEach(entry -> {
            Set<Item> itemSet = entry.getValue();
            itemSet.forEach(item -> {
                ItemStack itemStack = item.getItemStack();
                if (Main.getInstance().getConfigManager().isCheckValueEnabled()) {
                    if (itemStack.hasItemMeta()) {
                        return;
                    }
                    if (itemStack.getEnchantments().size() >= 3) {
                        return;
                    }
                }
                if (isInBlackList(itemStack)) {
                    return;
                }
                cleanItem.add(item);
            });
        });
        new BukkitRunnable() {
            @Override
            public void run() {
                cleanItem.forEach(Entity::remove);
                Main.getInstance().getBroadcaster().broadcast(MessageFormat.format(Main.getInstance().getConfigManager().getCleanDoneMessage(), String.valueOf(cleanItem.size())));
            }
        }.runTask(Main.getInstance());
    }

    public boolean isInBlackList(ItemStack itemStack) {
        List<String> blackList = Main.getInstance().getConfigManager().getBlackList();
        for (String black : blackList) {
            if (black.startsWith("@")) {
                String blackType = black.substring(1);
                if (itemStack.getType().toString().contains(blackType)) {
                    return true;
                }
                continue;
            } else {
                if (itemStack.getType().toString().equalsIgnoreCase(black)) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }
}
