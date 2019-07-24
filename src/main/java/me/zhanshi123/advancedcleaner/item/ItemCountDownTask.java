package me.zhanshi123.advancedcleaner.item;

import me.zhanshi123.advancedcleaner.CountDownTask;
import me.zhanshi123.advancedcleaner.Main;

public class ItemCountDownTask extends CountDownTask {
    public ItemCountDownTask(int time) {
        super(time);
    }

    @Override
    public void handle() {
        new CollectDropsTask().runTask(Main.getInstance());
    }
}
