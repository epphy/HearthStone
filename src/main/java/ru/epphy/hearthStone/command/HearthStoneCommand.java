package ru.epphy.hearthStone.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.epphy.hearthStone.config.Config;
import ru.epphy.hearthStone.config.Messages;
import ru.epphy.hearthStone.utility.MessageUtil;

import java.util.List;

@RequiredArgsConstructor
public final class HearthStoneCommand extends SubCommandManager implements TabExecutor {
    private final JavaPlugin plugin;
    private final Config config;
    private final Messages messages;

    public void init() {
        registerSubCommands();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            sendFeedback(sender, messages.getUsage());
            return true;
        }

        final SubCommandWrapper wrapper = getWrapper(args[0]);
        if (wrapper == null) {
            sendFeedback(sender, messages.getUnknownCommand(), label, args);
            return true;
        }

        if (!wrapper.hasPermission(sender)) {
            sendFeedback(sender, messages.getNoPermission(), label, args);
            return true;
        }

        wrapper.command().onCommand(sender, args);
        return true;
    }

    private void sendFeedback(@NonNull CommandSender sender, @NonNull Component message, @NonNull Object... values) {
        MessageUtil.sendMessage(sender, message, values);
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!hasAnyPermission(sender)) return List.of();
        if (args.length == 1) return getFirstAliases(sender);

        final SubCommandWrapper wrapper = getWrapper(args[0]);
        if (wrapper == null) return List.of();
        if (!wrapper.hasPermission(sender)) return List.of();

        return wrapper.command().onTabComplete(sender, args);
    }

    private void registerSubCommands() {

    }
}
