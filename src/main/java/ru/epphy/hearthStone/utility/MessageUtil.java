package ru.epphy.hearthStone.utility;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

@UtilityClass
public class MessageUtil {

    public void sendMessage(@NonNull CommandSender sender, @NonNull Component message, @NonNull Object... values) {
        sender.sendMessage(message);
    }

    public void sendActionBar(@NonNull CommandSender sender, @NonNull Component message, @NonNull Object... values) {
        sender.sendActionBar(message);
    }
}
