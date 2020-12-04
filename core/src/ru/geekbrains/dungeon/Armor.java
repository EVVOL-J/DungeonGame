package ru.geekbrains.dungeon;

import lombok.Data;
import ru.geekbrains.dungeon.game.Weapon;
@Data
public class Armor {
    public enum Type {
        HAUBERK, ARMOR,
    }

    Armor.Type type;
    private Weapon.Type resistanceWeapon;
    private int defence;

    public Armor(Armor.Type type,Weapon.Type weapon, int defence) {
        this.type = type;
        this.resistanceWeapon=weapon;
        this.defence=defence;
    }

    public int armorGetDamage(Weapon weapon){
        if(resistanceWeapon==weapon.getType()){
            return 1000;
        }
        else return defence;
    }
}
