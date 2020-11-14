package ru.geekbrains.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameMap {
    private TextureRegion grassTexture;
    public static final int CELLS_X = 20;
    public static final int CELLS_Y = 15;
    private byte[][] data;

    public GameMap(TextureAtlas textureAtlas) {
        this.data = new byte[CELLS_X][CELLS_Y];
        grassTexture = textureAtlas.findRegion("grass40");

    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < CELLS_X; i++) {
            for (int j = 0; j < CELLS_Y; j++) {
                batch.draw(grassTexture, i * 40, j * 40);
            }
        }
    }

    public float getMapSizeX() {
        return grassTexture.getRegionWidth() * CELLS_X;
    }

    public float getMapSizeY() {
        return grassTexture.getRegionHeight() * CELLS_Y;
    }


}
