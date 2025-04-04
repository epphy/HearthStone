package ru.epphy.hearthStone.command.list;

import lombok.NonNull;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.epphy.hearthStone.command.SubCommand;

import java.util.List;

public final class SetHomeCommand implements SubCommand {
    @Override
    public void onCommand(@NonNull CommandSender sender, @NotNull @NonNull String[] args) {

    }

    @Override
    public @NonNull List<String> onTabComplete(@NonNull CommandSender sender, @NotNull @NonNull String[] args) {
        return List.of();
    }
}
