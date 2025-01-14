package me.platinumdev.breaksell.model.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigUtils {

    private JavaPlugin plugin;
    private String name;
    private File file;
    private YamlConfiguration config;

    public ConfigUtils(JavaPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        file = new File(plugin.getDataFolder(),name);
        reloadConfig();
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            getConfig().save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile(){
        return file;
    }

    public void saveDefaultConfig() {
        plugin.saveResource(name, false);
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public String getMessage(String path){
        return config.getString(path).replace("&","§");
    }

    public List<String> getListMessage(String path){
        List replace = getConfig().getStringList(path);
        for(String s : getConfig().getStringList(path)) {
            replace.remove(s);
            s = s.replace("&", "§");
            replace.add(s);
        }
        return replace;
    }

    public List<String> getReplaceListMessage(String path, String level, String price, String blocks){
        List replace = getConfig().getStringList(path);
        for(String s : getConfig().getStringList(path)) {
            replace.remove(s);
            s = s.replace("&", "§").replace("@level",level).replace("@price",price).replace("@blocks",blocks);
            replace.add(s);
        }
        return replace;
    }

}
