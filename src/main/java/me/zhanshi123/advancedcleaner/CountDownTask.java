package me.zhanshi123.advancedcleaner;

import me.zhanshi123.advancedcleaner.item.CleanerTask;
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

    // 尽管没有在运行后对其进行调用，但仍然建议封闭对该 Map 的更改方法 —— 754503921
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
            // 应使用 runTaskTimer 来进行类似的定时循环任务，而不是 sleep 使调度器线程未被利用 —— 754503921
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
