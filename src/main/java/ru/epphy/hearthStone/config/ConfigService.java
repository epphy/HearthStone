package ru.epphy.hearthStone.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bukkit.plugin.java.JavaPlugin;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigService {
    private static ConfigService instance;

    public static ConfigService getInstance() {
        if (instance == null) {
            instance = new ConfigService();
        }
        return instance;
    }

    public static void init(@NonNull JavaPlugin plugin) {

    }

    public static void unload() {
        instance = null;
    }

    public Config getConfig() {

    }

    public Messages getMessages() {

    }
}
