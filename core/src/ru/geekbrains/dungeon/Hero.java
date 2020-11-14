package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Vector2;


public class Hero {
    private Vector2 position;
    private Vector2 velocity;
    private ProjectileController projectileController;
    private TextureRegion heroTexture;
    private boolean regimeOfShoot;
    private final int VELOCITY_PROJECTILE = 200;
    private float mapX;
    private float mapY;

    Hero(TextureAtlas textureAtlas, ProjectileController projectileController, GameMap gameMap) {
        this.heroTexture = textureAtlas.findRegion("tank");
        this.position = new Vector2(100, 100);
        this.velocity = new Vector2(100, 0);
        this.projectileController = projectileController;
        this.mapX = gameMap.getMapSizeX();
        this.mapY = gameMap.getMapSizeY();


    }

    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocity.setAngleDeg(90);
            if (position.y > mapY - 20) position.y = mapY - 20;
            position.mulAdd(velocity, dt);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            velocity.setAngleDeg(-90);
            if (position.y < 20) position.y = 20;
            position.mulAdd(velocity, dt);

        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.setAngleDeg(180);
            if (position.x < 20) position.x = 20;
            position.mulAdd(velocity, dt);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocity.setAngleDeg(0);
            if (position.x > mapX - 20) position.x = mapX - 20;
            position.mulAdd(velocity, dt);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            regimeOfShoot = !regimeOfShoot;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Vector2 direction = new Vector2(velocity);
            direction.nor();
            if (regimeOfShoot) {
                direction.rotateDeg(15);
                projectileController.activate(position.x, position.y, direction.x * VELOCITY_PROJECTILE, direction.y * VELOCITY_PROJECTILE);
                direction.rotateDeg(-30);
            }
            projectileController.activate(position.x, position.y, direction.x * VELOCITY_PROJECTILE, direction.y * VELOCITY_PROJECTILE);
        }

    }

    public void render(SpriteBatch batch) {
        batch.draw(heroTexture, position.x - 20, position.y - 20, 20, 20, 40, 40, 1, 1, velocity.angleDeg() - 90, false);
    }
}
