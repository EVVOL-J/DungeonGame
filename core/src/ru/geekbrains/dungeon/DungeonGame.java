package ru.geekbrains.dungeon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class DungeonGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private Hero hero;
    private TextureAtlas textureAtlas;
    private GameMap gameMap;
    private ProjectileController projectileController;


    @Override
    public void create() {
        textureAtlas = new TextureAtlas("images/game.pack");
        batch = new SpriteBatch();
        gameMap = new GameMap(textureAtlas);
        projectileController = new ProjectileController(textureAtlas, gameMap);
        hero = new Hero(textureAtlas, projectileController, gameMap);


    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        gameMap.render(batch);
        hero.render(batch);
        projectileController.render(batch);
        batch.end();
    }

    public void update(float dt) {
        hero.update(dt);
        projectileController.update(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
        textureAtlas.dispose();

    }
}
