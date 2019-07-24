package me.zhanshi123.advancedcleaner;

import me.zhanshi123.advancedcleaner.item.ItemCountDownTask;
import me.zhanshi123.advancedcleaner.item.resist.DropSkipManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.MessageFormat;

public final class Main extends JavaPlugin {
    private static Main instance;
    private ConfigManager configManager;
    private Broadcaster broadcaster;
    private CountDownTask countDownTask;
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

    public CountDownTask getCountDownTask() {
        return countDownTask;
    }

    public DropSkipManager getDropSkipManager() {
        return dropSkipManager;
    }

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();
        broadcaster = new Broadcaster();
        dropSkipManager = new DropSkipManager();
        countDownTask = new ItemCountDownTask(Main.getInstance().getConfigManager().getInterval());
        configManager.getNotificationSeconds().forEach(integer ->
                countDownTask.addTask(integer, () -> broadcaster.broadcast(MessageFormat.format(configManager.getCountDownMessage(), String.valueOf(integer))))
        );
        countDownTask.runTaskAsynchronously(Main.getInstance());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(Main.getInstance());
    }
}
