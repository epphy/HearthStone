package ru.epphy.hearthStone.config;

import lombok.Getter;
import net.kyori.adventure.text.Component;

@Getter
public class Messages implements IConfig {
    private Component usage;
    private Component noPermission;
    private Component unknownCommand;

    @Override
    public void load() {

    }

    @Override
    public void parse() {

    }

    @Override
    public void reload() {

    }
}
