package me.zhanshi123.advancedcleaner;

import me.zhanshi123.advancedcleaner.entity.EntityCountDownTask;
import me.zhanshi123.advancedcleaner.item.ItemCountDownTask;
import me.zhanshi123.advancedcleaner.item.resist.DropSkipManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.MessageFormat;

public final class Main extends JavaPlugin {
    private static Main instance;
    private ConfigManager configManager;
    private Broadcaster broadcaster;
    private CountDownTask itemCountDownTask;
    private CountDownTask entityCountDownTask;
    private DropSkipManager dropSkipManager;

    public static Main getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public Broadcaster getBroadcaster() {
        return broadcaster;
    }

    public DropSkipManager getDropSkipManager() {
        return dropSkipManager;
    }

    public CountDownTask getItemCountDownTask() {
        return itemCountDownTask;
    }

    public CountDownTask getEntityCountDownTask() {
        return entityCountDownTask;
    }

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();
        broadcaster = new Broadcaster();
        dropSkipManager = new DropSkipManager();
        if (configManager.isItemCleanEnabled()) {
            itemCountDownTask = new ItemCountDownTask(Main.getInstance().getConfigManager().getInterval());
            configManager.getNotificationSeconds().forEach(integer ->
                    itemCountDownTask.addTask(integer, () -> broadcaster.broadcast(MessageFormat.format(configManager.getCountDownMessage(), String.valueOf(integer))))
            );
            itemCountDownTask.runTaskAsynchronously(Main.getInstance());
        }
        if (configManager.isEntityCleanEnabled()) {
            entityCountDownTask = new EntityCountDownTask(Main.getInstance().getConfigManager().getEntityCleanInterval());
            entityCountDownTask.runTaskAsynchronously(Main.getInstance());
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(Main.getInstance());
    }
}
