package me.platinumdev.breaksell.model.enums;

import me.platinumdev.breaksell.PurpleBreakSell;
import me.platinumdev.breaksell.model.utils.ConfigUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Configuration {

    // Config formatacao
    FORMAT_PROGRESSBAR_COMPLETED("Format.progress-bar.completed", "&d"),
    FORMAT_PROGRESSBAR_NOT_COMPLETED("Format.progress-bar.not-completed", "&7"),
    FORMAT_PROGRESSBAR_BARS("Format.progress-bar.bars", 10),
    FORMAT_PROGRESSBAR_SYMBOL("Format.progress-bar.symbol", "|"),
    FORMAT_PREFIXES("Format.prefixes", Arrays.asList("K", "KK", "B")),
    FORMAT_PROGRESSBAR_FORMAT("Format.progress-bar.format", "&8[@bar&8]"),

    // Config geral
    WORLDS_LIST("Worlds", Collections.singletonList("world")),
    FORTUNE_BONUS("Fortune-Multiplier.bonus", 0.2),
    FORTUNE_BLOCKS("Fortune-Multiplier.blocks", Collections.singletonList("74;0"));

    private String string;
    private List<String> stringList;
    private String path;
    private double value;

    Configuration(String path, double defaultValue){
        ConfigUtils config = PurpleBreakSell.getInstance().getConfiguration();
        this.path = path;
        if(!config.getConfig().contains(path)){
            config.getConfig().set(path, defaultValue);
            this.value = defaultValue;
            PurpleBreakSell.getInstance().getLogger().severe("Ocorreu um erro ao carregar o valor: '"+path+"' (Salvando padrao)");
            config.saveConfig();
            return;
        }
        this.value = config.getConfig().getInt(path);
    }

    Configuration(String path, List<String> defaultValue){
        ConfigUtils config = PurpleBreakSell.getInstance().getConfiguration();
        this.path = path;
        if(!config.getConfig().contains(path)){
            config.getConfig().set(path, defaultValue);
            this.stringList = defaultValue;
            PurpleBreakSell.getInstance().getLogger().severe("Ocorreu um erro ao carregar o valor: '"+path+"' (Salvando padrao)");
            config.saveConfig();
            return;
        }
        this.stringList = config.getListMessage(path);
    }

    Configuration(String path, String defaultValue){
        ConfigUtils config = PurpleBreakSell.getInstance().getConfiguration();
        this.path = path;
        if(!config.getConfig().contains(path)){
            config.getConfig().set(path, defaultValue);
            this.string = defaultValue;
            PurpleBreakSell.getInstance().getLogger().severe("Ocorreu um erro ao carregar o valor: '"+path+"' (Salvando padrao)");
            config.saveConfig();
            return;
        }
        this.string = config.getMessage(path);
    }


    public double getValue() {
        return value;
    }

    public String getStringValue() {
        return string;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public static void load() { }
}
