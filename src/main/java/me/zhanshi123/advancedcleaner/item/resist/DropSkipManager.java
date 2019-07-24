package me.zhanshi123.advancedcleaner.item.resist;

import me.zhanshi123.advancedcleaner.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class DropSkipManager {
    private Map<String, Integer> data = new HashMap<>();

    public DropSkipManager() {
        Bukkit.getPluginManager().registerEvents(new DropResistListener(), Main.getInstance());
    }

    SkipState getState(Player player) {
        String name = player.getName();
        Integer integer = data.get(name);
        if (integer == null) {
            return SkipState.NORMAL;
        }
        return SkipState.valueOf(integer);
    }

    void updateState(Player player, SkipState skipState) {
        String name = player.getName();
        data.remove(name);
        data.put(name, skipState.getState());
    }

    public void clear() {
        data.clear();
    }
}
