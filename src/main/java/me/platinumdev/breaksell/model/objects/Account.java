package me.platinumdev.breaksell.model.objects;

public class Account {

    private String player;
    private int blocks;

    public Account(String player, int blocks) {
        this.player = player;
        this.blocks = blocks;
    }

    public String getPlayer() {
        return player;
    }

    public int getBlocks() {
        return blocks;
    }
}
