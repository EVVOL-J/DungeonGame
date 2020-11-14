package ru.geekbrains.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private Vector2 position;
    private Vector2 velocity;
    private TextureRegion projectileRegion;
    private boolean active;
    private float mapX;
    private float mapY;


    Projectile(TextureRegion region, GameMap gameMap) {
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);

        this.projectileRegion = region;
        this.active = false;
        this.mapX = gameMap.getMapSizeX();
        this.mapY = gameMap.getMapSizeY();
    }

    public boolean isActive() {
        return active;
    }

    public void activate(float x, float y, float vx, float vy) {
        active = true;
        position.set(x, y);
        velocity.set(vx, vy);

    }

    public void deactivate() {
        active = false;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        if (position.x < 0 || position.x > mapX || position.y < 0 || position.y > mapY) {
            deactivate();
        }

    }

    public void render(SpriteBatch batch) {
        batch.draw(projectileRegion, position.x - 8, position.y - 8);
    }

}
