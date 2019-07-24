package me.zhanshi123.advancedcleaner;

import me.zhanshi123.advancedcleaner.Main;
import me.zhanshi123.advancedcleaner.item.CleanerTask;
import me.zhanshi123.advancedcleaner.item.CollectDropsTask;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public abstract class CountDownTask extends BukkitRunnable {
    private int time;
    private int interval;

    public CountDownTask(int time) {
        this.time = time;
        this.interval = time;
    }

    private Map<Integer, CleanerTask> cleanerTaskMap = new HashMap<>();

    public void addTask(int time, CleanerTask cleanerTask) {
        cleanerTaskMap.remove(time);
        cleanerTaskMap.put(time, cleanerTask);
    }

    public int getTime() {
        return time;
    }

    @Override
    public void run() {
        while (true) {
            CleanerTask cleanerTask = cleanerTaskMap.get(time);
            if (cleanerTask != null) {
                cleanerTask.call();
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
            if (time <= 0) {
                time = interval;
                handle();
            }
        }
    }


    /**
     * Calls at a fixed interval as provided
     */
    public abstract void handle();
}
