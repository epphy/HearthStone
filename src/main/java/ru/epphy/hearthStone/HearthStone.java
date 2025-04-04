package ru.epphy.hearthStone;

import org.bukkit.plugin.java.JavaPlugin;
import ru.epphy.hearthStone.storage.PlayerHomesService;
import ru.epphy.hearthStone.utility.LoggerUtil;

public final class HearthStone extends JavaPlugin {
    @Override
    public void onEnable() {
        loadServices();
        printStartMessage();
    }

    private void loadServices() {
        PlayerHomesService.init(this);
    }

    private void printStartMessage() {
        LoggerUtil.announce(this, "==============================");
        LoggerUtil.announce(this, "     ✨ HearthStone Activated ✨");
        LoggerUtil.announce(this, "   Set and return to your peaceful home");
        LoggerUtil.announce(this, "==============================");
    }

    @Override
    public void onDisable() {
        unloadServices();
        printStopMessage();
    }

    private void unloadServices() {
        PlayerHomesService.unload();
    }

    private void printStopMessage() {
        LoggerUtil.announce(this, "==============================");
        LoggerUtil.announce(this, "     💤 HearthStone Disabled 💤");
        LoggerUtil.announce(this, "   Your hearth grows cold... until next time.");
        LoggerUtil.announce(this, "==============================");
    }
}
