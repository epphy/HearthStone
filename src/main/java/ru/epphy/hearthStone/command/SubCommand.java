package ru.epphy.hearthStone.command;

import lombok.NonNull;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface SubCommand {
    void onCommand(@NonNull CommandSender sender, @NonNull String[] args);
    @NonNull List<String> onTabComplete(@NonNull CommandSender sender, @NonNull String[] args);
}
