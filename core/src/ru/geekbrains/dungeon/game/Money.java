package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import lombok.Data;
import ru.geekbrains.dungeon.helpers.Assets;
import ru.geekbrains.dungeon.helpers.Poolable;

@Data
public class Money implements Poolable {
    private TextureRegion texture;
    private int x;
    private int y;
    private int pos;
    private boolean active;

    @Override
    public boolean isActive() {
        return active;
    }

    public Money() {
        this.x = 0;
        this.y = 0;
        this.texture = Assets.getInstance().getAtlas().findRegion("pngwave");
        this.active = false;
        this.pos = MathUtils.random(-GameMap.CELL_SIZE/2+20, GameMap.CELL_SIZE/2);
    }

    public void deactivate() {
        active = false;
    }

    public void activate(int x, int y) {
        active = true;
        this.x = x;
        this.y = y;

    }

    public void update(float dt) {

    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x * GameMap.CELL_SIZE + pos, y * GameMap.CELL_SIZE + 10);
    }
}
