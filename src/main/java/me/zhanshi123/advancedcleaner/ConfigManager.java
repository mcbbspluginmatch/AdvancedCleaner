package me.zhanshi123.advancedcleaner;

import com.google.common.base.Charsets;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {
    private FileConfiguration config = new YamlConfiguration();
    private File f = null;

    public ConfigManager() {
        load();
    }

    private void load() {
        try {
            Main plugin = Main.getInstance();
            f = new File(plugin.getDataFolder(), "config.yml");
            if (!f.exists()) {
                plugin.saveResource("config.yml", false);
            }
            config.load(new BufferedReader(new InputStreamReader(new FileInputStream(f), Charsets.UTF_8)));
            ConfigurationSection limit = config.getConfigurationSection("entity.limit");
            if (limit != null) {
                limit.getKeys(false).forEach(name -> {
                    try {
                        EntityType entityType = EntityType.valueOf(name);
                        int value = limit.getInt(name);
                        entityLimit.put(entityType, value);
                    } catch (Exception e) {
                        Main.getInstance().getLogger().warning("Failed to load entity limit, entity type not found: " + name);
                    }

                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<EntityType, Integer> entityLimit = new HashMap<>();

    public Map<EntityType, Integer> getEntityLimit() {
        return entityLimit;
    }

    public boolean isCheckValueEnabled() {
        return config.getBoolean("item.checkValue.enabled", true);
    }

    public List<String> getBlackList() {
        return config.getStringList("item.blackList");
    }

    public List<Integer> getNotificationSeconds() {
        return config.getIntegerList("item.broadcast.notifications");
    }

    public String getCountDownMessage() {
        return config.getString("item.broadcast.countDown", "MESSAGE_NOT_FOUND").replace("&", "§");
    }

    public int getInterval() {
        return config.getInt("item.interval", 600);
    }

    public String getCleanDoneMessage() {
        return config.getString("item.broadcast.cleanDone", "MESSAGE_NOT_FOUND").replace("&", "§");
    }

    public boolean isDropResistEnabled() {
        return config.getBoolean("item.dropResist.enable", true);
    }

    public String getDropResistConsult() {
        return config.getString("item.dropResist.consult", "MESSAGE_NOT_FOUND").replace("&", "§");
    }

    public String getDropResistConfirmed() {
        return config.getString("item.dropResist.confirmed", "MESSAGE_NOT_FOUND").replace("&", "§");
    }

    public int getDropResistValue() {
        return config.getInt("item.dropResist.value", 10);
    }

    public boolean isItemCleanEnabled() {
        return config.getBoolean("item.enable", true);
    }

    public boolean isEntityCleanEnabled() {
        return config.getBoolean("entity.enable", true);
    }

    public int getEntityCleanInterval() {
        return config.getInt("entity.interval", 900);
    }
}
