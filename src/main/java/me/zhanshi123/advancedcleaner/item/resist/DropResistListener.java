package me.zhanshi123.advancedcleaner.item.resist;

import me.zhanshi123.advancedcleaner.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropResistListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!Main.getInstance().getConfigManager().isDropResistEnabled()) {
            return;
        }
        if (Main.getInstance().getItemCountDownTask().getTime() > Main.getInstance().getConfigManager().getDropResistValue()) {
            return;
        }
        Player player = e.getPlayer();
        SkipState skipState = Main.getInstance().getDropSkipManager().getState(player);
        if (skipState == SkipState.CONFIRMED) {
            return;
        }
        if (skipState == SkipState.NORMAL) {
            e.setCancelled(true);
            Main.getInstance().getDropSkipManager().updateState(player, SkipState.WAIT_TO_CONFIRM);
            player.sendMessage(Main.getInstance().getConfigManager().getDropResistConsult());
            return;
        }
        if (skipState == SkipState.WAIT_TO_CONFIRM) {
            Main.getInstance().getDropSkipManager().updateState(player, SkipState.CONFIRMED);
            player.sendMessage(Main.getInstance().getConfigManager().getDropResistConfirmed());
        }
    }
}
