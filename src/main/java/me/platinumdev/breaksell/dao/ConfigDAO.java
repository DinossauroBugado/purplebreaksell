package me.platinumdev.breaksell.dao;

import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

public interface ConfigDAO {

    void loadAll(ConfigurationSection cs);
    void reloadAll(ConfigurationSection cs);

    boolean isValidValue(ConfigurationSection cs, String value);

}
