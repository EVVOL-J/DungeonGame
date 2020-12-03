package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.geekbrains.dungeon.helpers.Assets;
import ru.geekbrains.dungeon.helpers.ObjectPool;
import ru.geekbrains.dungeon.helpers.Utils;

public class MoneyController extends ObjectPool<Money> {

    private GameController gc;


    public MoneyController(GameController gc) {
        this.gc = gc;
    }

    @Override
    protected Money newObject() {
        return new Money();
    }

    public void activate(int money, int x, int y) {
        for (int i = 0; i < money; i++) {
            getActiveElement().activate(x, y);
        }
    }

    public void update(float dt) {
        for (int units = 0; units < gc.getUnitController().getAllUnits().size(); units++) {
            Unit unit = gc.getUnitController().getAllUnits().get(units);
            for (int i = 0; i < getActiveList().size(); i++) {
                Money money = getActiveList().get(i);
                if (Utils.getCellsFloatDistance(unit.cellX, unit.cellY, money.getX(), money.getY()) < 1) {
                    money.deactivate();
                    unit.gold++;
                }
            }
            checkPool();
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < getActiveList().size(); i++) {
            Money money = getActiveList().get(i);
            money.render(batch);
        }
    }
}
