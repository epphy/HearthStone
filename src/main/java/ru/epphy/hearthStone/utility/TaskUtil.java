package ru.epphy.hearthStone.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TaskUtil {
    private static TaskUtil instance;
    private boolean shuttingDown;

    public static TaskUtil getInstance() {
        if (instance == null) {
            instance = new TaskUtil();
        }
        return instance;
    }

    public void setShuttingDown(boolean value) {
        this.shuttingDown = value;
        LoggerUtil.info(this, "Server is marked as %s".formatted(value ? "shutting down" : "working"));
    }

    @Nullable
    public BukkitTask runTaskAsync(@NonNull JavaPlugin plugin, @NonNull Runnable task) {
        if (shuttingDown) {
            task.run();
            LoggerUtil.warn(this, "Failed to run an async task; server is shutting down");
            return null;
        } else {
            return Bukkit.getScheduler().runTaskAsynchronously(plugin, task);
        }
    }
}
