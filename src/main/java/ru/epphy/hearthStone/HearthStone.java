package ru.epphy.hearthStone;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import ru.epphy.hearthStone.command.HearthStoneCommand;
import ru.epphy.hearthStone.config.ConfigService;
import ru.epphy.hearthStone.storage.PlayerHomesService;
import ru.epphy.hearthStone.utility.LoggerUtil;
import ru.epphy.hearthStone.utility.TaskUtil;

public final class HearthStone extends JavaPlugin {
    @Override
    public void onEnable() {
        loadServices();
        loadCommand();
        printStartMessage();
    }

    private void loadServices() {
        ConfigService.init(this);
        PlayerHomesService.init(this);
    }

    private void loadCommand() {
        final PluginCommand command = getCommand("hearthstone");
        if (command == null) {
            throw new IllegalStateException("Command hasn't been initialised");
        }

        final var commandHandler = new HearthStoneCommand(
                this,
                ConfigService.getInstance().getConfig(),
                ConfigService.getInstance().getMessages()
        );
        commandHandler.init();

        command.setExecutor(commandHandler);
        command.setTabCompleter(commandHandler);
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
        TaskUtil.getInstance().setShuttingDown(true);
        PlayerHomesService.unload();
        ConfigService.unload();
    }

    private void printStopMessage() {
        LoggerUtil.announce(this, "==============================");
        LoggerUtil.announce(this, "     💤 HearthStone Disabled 💤");
        LoggerUtil.announce(this, "   Your hearth grows cold... until next time.");
        LoggerUtil.announce(this, "==============================");
    }
}
