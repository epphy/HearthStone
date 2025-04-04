package ru.epphy.hearthStone.command;

import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.Objects;
import java.util.Set;

record SubCommandWrapper(SubCommand command, Set<String> aliases, Permission permission) {

    boolean hasPermission(@NonNull CommandSender sender) {
        return sender.hasPermission(permission);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubCommandWrapper that = (SubCommandWrapper) o;
        return Objects.equals(command, that.command) && Objects.equals(aliases, that.aliases) && Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, aliases, permission);
    }

    @Override
    public String toString() {
        return "SubCommandWrapper{" +
                "command=" + command +
                ", aliases=" + aliases +
                ", permission=" + permission +
                '}';
    }
}
