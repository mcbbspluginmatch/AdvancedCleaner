package me.zhanshi123.advancedcleaner.entity;

import me.zhanshi123.advancedcleaner.CountDownTask;
import me.zhanshi123.advancedcleaner.Main;

public class EntityCountDownTask extends CountDownTask {

    public EntityCountDownTask(int time) {
        super(time);
    }

    @Override
    public void handle() {
        // 至 0 后未取消该任务 —— 754503921
        new EntityCleanTask().runTask(Main.getInstance());
    }
}
