package ru.epphy.hearthStone.storage;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import ru.epphy.hearthStone.utility.TaskUtil;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerHomesService {
    private static PlayerHomesService instance;
    private JavaPlugin plugin;
    private PlayerHomesStorage storage;
    private Map<UUID, Set<Location>> data = new HashMap<>();

    public static PlayerHomesService getInstance() {
        if (instance == null) {
            instance = new PlayerHomesService();
        }
        return instance;
    }

    public static void init(@NonNull JavaPlugin plugin) {
        instance.plugin = plugin;
        instance.storage = new PlayerHomesStorage();
        instance.storage.load(plugin);
    }

    public static void unload() {
        instance = null;
    }

    public void updateData() {
        TaskUtil.getInstance().runTaskAsync(plugin, () -> {
            data = storage.retrieve();
        });
    }

    public void updateStorage() {
        TaskUtil.getInstance().runTaskAsync(plugin, () -> {
            storage.store(data);
        });
    }

    @NonNull
    public List<UUID> getPlayerIdsWithLocation(@NonNull Location location) {
        List<UUID> result = new ArrayList<>();
        for (Map.Entry<UUID, Set<Location>> entry : data.entrySet()) {
            if (entry.getValue().contains(location)) result.add(entry.getKey());
        }
        return List.copyOf(result);
    }

    @NonNull
    public List<Set<Location>> getLocationsOf(@NonNull UUID playerId) {
        if (hasHome(playerId)) return List.of();
        else return List.of(data.get(playerId));
    }

    public boolean hasHome(@NonNull UUID playerId) {
        return data.containsKey(playerId);
    }

    @NonNull
    public List<UUID> getPlayerIds() {
        return List.copyOf(data.keySet());
    }

    @NonNull
    public List<Set<Location>> getLocations() {
        return List.copyOf(data.values());
    }

    @NonNull
    public Set<Map.Entry<UUID, Set<Location>>> getDataEntries() {
        return Set.copyOf(data.entrySet());
    }

    @NonNull
    public Map<UUID, Set<Location>> getData() {
        return Map.copyOf(data);
    }
}
