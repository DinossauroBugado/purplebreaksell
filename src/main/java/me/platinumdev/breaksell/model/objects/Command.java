package me.platinumdev.breaksell.model.objects;

import org.bukkit.Bukkit;

import java.util.Random;

public class Command {

    private int chance;
    private String command;

    public Command(int chance, String command) {
        this.chance = chance;
        this.command = command;
    }

    public void executeCommand(String player){
        String current = null;
        if(new Random().nextInt(100) < chance) current = command;
        if(current != null) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("@player", player));
    }

}
