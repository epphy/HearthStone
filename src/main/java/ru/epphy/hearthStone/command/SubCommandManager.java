package ru.epphy.hearthStone.command;

import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.Nullable;

import java.util.*;

abstract class SubCommandManager {
    private final Set<SubCommandWrapper> wrappers = new HashSet<>();
    private final Map<String, SubCommandWrapper> wrapperAliases = new HashMap<>();

    protected void addSubCommand(@NonNull SubCommand command, @NonNull Set<String> aliases, @NonNull Permission permission) {
        final SubCommandWrapper wrapper = new SubCommandWrapper(command, aliases, permission);
        aliases.forEach(alias -> wrapperAliases.put(alias, wrapper));
        wrappers.add(wrapper);
    }

    @Nullable
    protected SubCommandWrapper getWrapper(@NonNull String argument) {
        return wrapperAliases.get(argument);
    }

    @NonNull
    protected List<String> getFirstAliases(@NonNull CommandSender sender) {
        final List<String> result = new ArrayList<>();
        for (final SubCommandWrapper wrapper : wrappers) {
            if (!wrapper.hasPermission(sender)) continue;
            result.addAll(wrapper.aliases());
        }
        return result;
    }

    protected boolean hasAnyPermission(@NonNull CommandSender sender) {
        for (final SubCommandWrapper wrapper : wrappers) {
            if (wrapper.hasPermission(sender)) return true;
        }
        return false;
    }
}
