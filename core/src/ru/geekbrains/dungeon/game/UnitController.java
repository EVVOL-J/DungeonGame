package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class UnitController {
    private GameController gc;
    private MonsterController monsterController;
    private Hero hero;
    private Unit currentUnit;
    private int index;
    private List<Unit> allUnits;
    private int round;

    public MonsterController getMonsterController() {
        return monsterController;
    }

    public Hero getHero() {
        return hero;
    }

    public boolean isItMyTurn(Unit unit) {
        return currentUnit == unit;
    }

    public boolean isCellFree(int cellX, int cellY) {
        for (Unit u : allUnits) {
            if (u.getCellX() == cellX && u.getCellY() == cellY) {
                return false;
            }
        }
        return true;
    }

    public UnitController(GameController gc) {
        this.gc = gc;
        this.hero = new Hero(gc);
        this.monsterController = new MonsterController(gc);
        this.round=0;
    }

    public void init() {
        this.index = -1;
        this.allUnits = new ArrayList<>();
        this.allUnits.add(hero);
        activateRandomMonster(2);

        this.nextTurn();
    }

    public void nextTurn() {
        index++;
        if (index >= allUnits.size()) {
            index = 0;
            round++;
            checkRound();
            hillAllUnit(1);

        }
        currentUnit = allUnits.get(index);
        currentUnit.startTurn();
    }

    private void hillAllUnit(int i) {
        for (Unit u : allUnits) {
            u.hillUnit(i);
        }

    }

    private void activateRandomMonster(int numberOfMonster){
        for (int i=0;i<numberOfMonster;i++) {
            while (!this.monsterController.activate((MathUtils.random(0, gc.getGameMap().getCellsX() - 1)), (MathUtils.random(0, gc.getGameMap().getCellsY() - 1)))) {
            }
            this.allUnits.clear();//костыль
            this.allUnits.add(hero);
            this.allUnits.addAll(monsterController.getActiveList());
        }
    }

    private void checkRound() {
        if(round%3==0) {
            activateRandomMonster(1);

        }
    }

    public void render(SpriteBatch batch, BitmapFont font18) {
        hero.render(batch, font18);
        monsterController.render(batch, font18);
    }

    public void update(float dt) {
        hero.update(dt);
        monsterController.update(dt);

        if (!currentUnit.isActive() || currentUnit.getTurns() == 0) {
            nextTurn();
        }
    }

    public void removeUnitAfterDeath(Unit unit) {
        int unitIndex = allUnits.indexOf(unit);
        allUnits.remove(unit);
        if (unitIndex <= index) {
            index--;
        }
    }

    public int getRound() {
        return round;
    }
    public boolean isCellEmpty(int cx, int cy) {
        return gc.getGameMap().isCellPassable(cx, cy) && isCellFree(cx, cy);
    }
}
