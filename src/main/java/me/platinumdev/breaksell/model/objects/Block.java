package me.platinumdev.breaksell.model.objects;

import me.platinumdev.breaksell.PurpleBreakSell;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Block {

    private double price;
    private int id = 0;
    private short data = 0;
    private List<Command> commands;

    public Block(String formattedMaterial, double price, List<String> cmds) {
        this.price = price;
        toFormattedCommands(cmds);
        toIdAndDataByString(formattedMaterial);
    }

    public void toFormattedCommands(List<String> values){
        commands = new ArrayList<>();
        values.forEach(s -> {
            String[] array = s.split(";");
            if (array[0] != null) {
                commands.add(new Command(Integer.parseInt(array[0]), s));
            }
        });
    }

    public void executeCommands(String player){
        commands.forEach(cmd -> cmd.executeCommand(player));
    }

    private void toIdAndDataByString(String value) {
        String[] array = value.split(";");
        if (array[0] != null && Integer.parseInt(array[0]) > 0 && Material.getMaterial(Integer.parseInt(array[0])).isBlock()) {
            this.id = Integer.parseInt(array[0]);
            if (array[1] != null) {
                this.data = Short.parseShort(array[1]);
                return;
            }
        }
        PurpleBreakSell.getInstance().getLogger().severe("Ocorreu um erro ao carregar os valores (ID e Data) do bloco " + value);
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public short getData() {
        return data;
    }
}
