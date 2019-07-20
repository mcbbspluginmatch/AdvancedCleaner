package me.zhanshi123.entitycleaner.item;

import me.zhanshi123.entitycleaner.Main;

import java.util.HashMap;
import java.util.Map;

public class CountDownThread implements Runnable {
    private int time = Main.getInstance().getConfigManager().getInterval();

    private Map<Integer, CleanerTask> cleanerTaskMap = new HashMap<>();

    public void addTask(int time, CleanerTask cleanerTask) {
        cleanerTaskMap.remove(time);
        cleanerTaskMap.put(time, cleanerTask);
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
                time = Main.getInstance().getConfigManager().getInterval();
                new CollectDropsTask().runTask(Main.getInstance());
            }
        }
    }
}
