package me.zhanshi123.entitycleaner;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Broadcaster {
    public void broadcast(String message) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(message);
            }
        }.runTask(Main.getInstance());
    }
}
