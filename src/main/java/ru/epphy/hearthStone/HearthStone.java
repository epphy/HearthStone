package ru.epphy.hearthStone;

import org.bukkit.plugin.java.JavaPlugin;
import ru.epphy.hearthStone.utility.LoggerUtil;

public final class HearthStone extends JavaPlugin {
    @Override
    public void onEnable() {
        printStartMessage();
    }

    private void printStartMessage() {
        LoggerUtil.announce(this, "==============================");
        LoggerUtil.announce(this, "     ✨ HearthStone Activated ✨");
        LoggerUtil.announce(this, "   Set and return to your peaceful home");
        LoggerUtil.announce(this, "==============================");
    }

    @Override
    public void onDisable() {
        printStopMessage();
    }

    private void printStopMessage() {
        LoggerUtil.announce(this, "==============================");
        LoggerUtil.announce(this, "     💤 HearthStone Disabled 💤");
        LoggerUtil.announce(this, "   Your hearth grows cold... until next time.");
        LoggerUtil.announce(this, "==============================");
    }
}
