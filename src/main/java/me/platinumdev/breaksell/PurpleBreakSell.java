package me.platinumdev.breaksell;

import me.platinumdev.breaksell.model.utils.ConfigUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class PurpleBreakSell extends JavaPlugin {

    private static PurpleBreakSell instance;

    private ConfigUtils config;
    private ConfigUtils messages;
    private boolean debug;

    // Oi cauan <3

    public void onEnable() {
        instance = this;
        loadAll();
        debug = config.getConfig().getBoolean("debug");
    }

    public void onDisable() {

    }

    /*
        Getters
     */

    public static PurpleBreakSell getInstance() {
        return instance;
    }

    public ConfigUtils getConfiguration() {
        return config;
    }

    /*
        Methods
     */

    public void loadAll(){

    }

    public void loadConfigurations(){
        config = new ConfigUtils(this, "config.yml");
        loadConfig(config);
    }

    private void loadConfig(ConfigUtils input){
        if(!input.getFile().exists()) input.saveDefaultConfig();
        input.reloadConfig();
    }

    public boolean isDebug() {
        return debug;
    }
}
