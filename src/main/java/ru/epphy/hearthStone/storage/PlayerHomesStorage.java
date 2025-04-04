package ru.epphy.hearthStone.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import ru.epphy.hearthStone.utility.LoggerUtil;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
final class PlayerHomesStorage {
    private static final String FILE_NAME = "homes.json";
    private static final Gson GSON = new Gson();
    private static final TypeToken<Map<UUID, Set<Location>>> typeToken = new TypeToken<>(){};
    private File file;

    void load(@NonNull JavaPlugin plugin) {
        if (file == null) {
            file = new File(plugin.getDataFolder(), FILE_NAME);
        }

        if (!file.exists()) {
            plugin.saveResource(FILE_NAME, false);
        }
    }

    void store(@NonNull Map<UUID, Set<Location>> rawData) {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(GSON.toJson(rawData));
            LoggerUtil.info(this, "Data has been stored");
        } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve data from %s".formatted(FILE_NAME), e);
        }
    }

    Map<UUID, Set<Location>> retrieve() {
        try (final BufferedReader reader = new BufferedReader(new FileReader(file))) {
            final Map<UUID, Set<Location>> data = GSON.fromJson(reader, typeToken);
            LoggerUtil.info(this, "Data has been retrieved");
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve data from %s".formatted(FILE_NAME), e);
        }
    }
}
