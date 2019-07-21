package me.zhanshi123.advancedcleaner;

import me.zhanshi123.advancedcleaner.item.CountDownThread;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.MessageFormat;

public final class Main extends JavaPlugin {
    private static Main instance;
    private ConfigManager configManager;
    private Broadcaster broadcaster;
    private CountDownThread countDownThread;

    public static Main getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public Broadcaster getBroadcaster() {
        return broadcaster;
    }

    public CountDownThread getCountDownThread() {
        return countDownThread;
    }

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();
        broadcaster = new Broadcaster();
        countDownThread = new CountDownThread();
        configManager.getNotificationSeconds().forEach(integer ->
                countDownThread.addTask(integer, () -> broadcaster.broadcast(MessageFormat.format(configManager.getCountDownMessage(), String.valueOf(integer))))
        );
        countDownThread.runTaskAsynchronously(Main.getInstance());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(Main.getInstance());
    }
}
