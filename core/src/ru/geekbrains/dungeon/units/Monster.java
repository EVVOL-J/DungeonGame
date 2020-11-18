package ru.geekbrains.dungeon.units;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.dungeon.GameController;

import java.util.Random;

public class Monster extends Unit {
    int lastHp;
    Random random;
    int damage;

    public boolean isActive() {
        return hp > 0;
    }

    public Monster(TextureAtlas atlas, GameController gc) {
        super(gc, 5, 2, 10);
        this.texture = atlas.findRegion("monster");
        this.textureHp = atlas.findRegion("hp");
        this.hp = -1;
        this.random = new Random();
        this.damage = 1;
    }

    public void activate(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.hpMax = 10;
        this.hp = hpMax;
        this.lastHp = hpMax;
    }

    public void update(float dt) {
        if (lastHp > hp && (Math.abs(gc.getHero().cellX - cellX) + Math.abs(gc.getHero().cellY - cellY)) == 1 && random.nextInt(100) < 25) {
            gc.getHero().takeDamage(damage);
        }
        lastHp = hp;

    }
}
