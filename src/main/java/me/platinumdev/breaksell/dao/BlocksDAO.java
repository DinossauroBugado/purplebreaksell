package me.platinumdev.breaksell.dao;

import me.platinumdev.breaksell.PurpleBreakSell;
import me.platinumdev.breaksell.model.objects.Block;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

public class BlocksDAO implements ConfigDAO {

    private HashMap<String, Block> blocksMap = new HashMap<>();
    private PurpleBreakSell instance = PurpleBreakSell.getInstance();

    public void loadAll(ConfigurationSection cs) {
        for(String current : cs.getKeys(false)){
            if(!isValidValue(cs, current)) return;
            Block block = new Block(current, cs.getDouble(current+".price"), cs.getStringList(current+".commands"));
            blocksMap.put(current, block);
            if(instance.isDebug())
                instance.getLogger().info("Bloco '"+current+"' carregado com sucesso!");
        }
        instance.getLogger().info("Foram carregados ("+blocksMap.size()+") blocos da configuracao.");
    }

    public void reloadAll(ConfigurationSection cs) {
        blocksMap.clear();
        loadAll(cs);
    }

    public boolean isValidValue(ConfigurationSection cs, String value) {
        if(!(cs.getInt(value+"price") >= 0)){
            instance.getLogger().severe("Ocorreu um erro ao carregar o bloco '"+value+"' (Preco invalido)");
            return false;
        }
        if(value.split(";").length == 2){
            instance.getLogger().severe("Ocorreu um erro ao carregar o bloco '"+value+"' (ID/DATA invalido)");
            return false;
        }
        if(cs.getStringList(value+".commands").stream().filter(s -> s.split(";").length >= 2).count() > 0){
            instance.getLogger().severe("Ocorreu um erro ao carregar o bloco '"+value+"' (Comandos invalidos)");
            return false;
        }
        return true;
    }
}
