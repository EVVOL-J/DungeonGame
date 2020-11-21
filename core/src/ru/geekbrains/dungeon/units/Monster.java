package ru.geekbrains.dungeon.units;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.dungeon.GameController;


import java.util.Random;


public class Monster extends Unit {
    private float aiBrainsImplseTime;
    private Unit target;
    private Random random;

    public Monster(TextureAtlas atlas, GameController gc) {
        super(gc, 5, 2, 10);
        this.texture = atlas.findRegion("monster");
        this.textureHp = atlas.findRegion("hp");
        this.hp = -1;
        this.random = new Random();
    }

    public void activate(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.targetX = cellX;
        this.targetY = cellY;
        this.hpMax = 10;
        this.hp = hpMax;
        this.target = gc.getUnitController().getHero();
    }

    public void update(float dt) {
        super.update(dt);
        if (canIMakeAction()) {
            if (isStayStill()) {
                aiBrainsImplseTime += dt;
            }
            if (aiBrainsImplseTime > 0.4f) {
                aiBrainsImplseTime = 0.0f;
                if (targetInSight()) {
                    if (canIAttackThisTarget(target)) {
                        attack(target);
                    } else {
                        tryToMove();
                    }
                } else {
                    goRandom();
                }
            }
        }
    }
//Ход в рандомное место
    private void goRandom() {
        int count=0;
        int[] randomX={-1,-1,-1,-1};
        int[] randomY={-1,-1,-1,-1};


        for (int i = cellX - 1; i <= cellX + 1; i++) {
            for (int j = cellY - 1; j <= cellY + 1; j++) {
                if (Math.abs(cellX - i) + Math.abs(cellY - j) == 1 && gc.getGameMap().isCellPassable(i, j) && gc.getUnitController().isCellFree(i, j)) {
                   randomX[count]=i;
                   randomY[count]=j;
                    count++;
                }
            }
        }

       int randomCounter=random.nextInt(count-1);
        goTo(randomX[randomCounter], randomY[randomCounter]);
    }


    //проверка на видимость героя
    private boolean targetInSight() {
        return Math.sqrt((cellX - target.getCellX()) * (cellX - target.getCellX()) + (cellY - target.getCellY()) * (cellY - target.getCellY())) < 5;
    }

    public void tryToMove() {
        int bestX = -1, bestY = -1;
        float bestDst = 10000;
        for (int i = cellX - 1; i <= cellX + 1; i++) {
            for (int j = cellY - 1; j <= cellY + 1; j++) {
                if (Math.abs(cellX - i) + Math.abs(cellY - j) == 1 && gc.getGameMap().isCellPassable(i, j) && gc.getUnitController().isCellFree(i, j)) {
                    float dst = (float) Math.sqrt((i - target.getCellX()) * (i - target.getCellX()) + (j - target.getCellY()) * (j - target.getCellY()));
                    if (dst < bestDst) {
                        bestDst = dst;
                        bestX = i;
                        bestY = j;
                    }
                }
            }
        }
        goTo(bestX, bestY);
    }
}
