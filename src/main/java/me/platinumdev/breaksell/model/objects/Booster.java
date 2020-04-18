package me.platinumdev.breaksell.model.objects;

public class Booster {

    private String prefix;
    private String permission;
    private int moneyBoost;

    public Booster(String prefix, String permission, int moneyBoost) {
        this.prefix = prefix;
        this.permission = permission;
        this.moneyBoost = moneyBoost;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPermission() {
        return permission;
    }

    public int getMoneyBoost() {
        return moneyBoost;
    }

    public double getBoostedValue(double value){
        return value + (value * moneyBoost);
    }
}
