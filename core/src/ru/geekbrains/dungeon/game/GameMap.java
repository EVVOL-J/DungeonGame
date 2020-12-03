package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import ru.geekbrains.dungeon.helpers.Assets;
import ru.geekbrains.dungeon.helpers.Utils;

public class GameMap {
    public enum CellType {
        GRASS, WATER, TREE, FOGGY,
    }

    private class Cell {
        CellType type;
        int index;

        public Cell() {
            type = CellType.GRASS;
            index = 0;
        }

        public void changeType(CellType to) {
            type = to;
            if (type == CellType.TREE) {
                index = MathUtils.random(4);
            }
        }
    }

    public static final int CELLS_X = 22;
    public static final int CELLS_Y = 12;
    public static final int CELL_SIZE = 60;
    public static final int FOREST_PERCENTAGE = 5;

    public int getCellsX() {
        return CELLS_X;
    }

    public int getCellsY() {
        return CELLS_Y;
    }

    private Cell[][] data;
    private TextureRegion grassTexture;
    private TextureRegion[] treesTextures;
    private TextureRegion foggyTexture;
    private boolean[][] foggyCell;

    public GameMap() {
        this.data = new Cell[CELLS_X][CELLS_Y];
        for (int i = 0; i < CELLS_X; i++) {
            for (int j = 0; j < CELLS_Y; j++) {
                this.data[i][j] = new Cell();
            }
        }
        int treesCount = (int)((CELLS_X * CELLS_Y * FOREST_PERCENTAGE) / 100.0f);
        for (int i = 0; i < treesCount; i++) {
            this.data[MathUtils.random(0, CELLS_X - 1)][MathUtils.random(0, CELLS_Y - 1)].changeType(CellType.TREE);

        }

        this.grassTexture = Assets.getInstance().getAtlas().findRegion("grass");
        this.foggyTexture=Assets.getInstance().getAtlas().findRegion("foggy");
        this.treesTextures = Assets.getInstance().getAtlas().findRegion("trees").split(60, 90)[0];
        this.foggyCell=new boolean[CELLS_X][CELLS_Y];

    }

    public boolean isCellPassable(int cx, int cy) {
        if (cx < 0 || cx > getCellsX() - 1 || cy < 0 || cy > getCellsY() - 1) {
            return false;
        }
        if (data[cx][cy].type != CellType.GRASS) {
            return false;
        }
        return true;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < CELLS_X; i++) {
            for (int j = CELLS_Y - 1; j >= 0; j--) {
                batch.draw(grassTexture, i * CELL_SIZE, j * CELL_SIZE);
                if (data[i][j].type == CellType.TREE) {
                    batch.draw(treesTextures[data[i][j].index], i * CELL_SIZE, j * CELL_SIZE);
                }
            }
        }
    }
    public void renderFog(SpriteBatch batch, Hero hero){
        for (int i = 0; i < CELLS_X; i++) {
            for (int j = CELLS_Y - 1; j >= 0; j--) {
                if(Utils.getCellsIntDistance(i, j, hero.getCellX(), hero.getCellY()) < 5) foggyCell[i][j]=true;
                if(foggyCell[i][j]==false) batch.draw(foggyTexture,i * CELL_SIZE, j * CELL_SIZE);
            }
        }

    }


}
