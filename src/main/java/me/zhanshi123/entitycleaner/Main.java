package me.zhanshi123.entitycleaner;

import me.zhanshi123.entitycleaner.item.CountDownThread;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.MessageFormat;

public final class Main extends JavaPlugin {
    private static Main instance;
    private ConfigManager configManager;
    private Broadcaster broadcaster;
    private CountDownThread countDownThread;
    private Thread countDown;

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

    public Thread getCountDown() {
        return countDown;
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
        countDown = new Thread(countDownThread);
        countDown.start();
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(Main.getInstance());
    }
}
