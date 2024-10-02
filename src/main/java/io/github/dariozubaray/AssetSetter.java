package io.github.dariozubaray;

import io.github.dariozubaray.object.OBJ_Boot;
import io.github.dariozubaray.object.OBJ_Chest;
import io.github.dariozubaray.object.OBJ_Door;
import io.github.dariozubaray.object.OBJ_Key;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.objects[0] = new OBJ_Key(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        gamePanel.objects[0].worldX = 23 * gamePanel.TILE_SIZE;
        gamePanel.objects[0].worldY = 7 * gamePanel.TILE_SIZE;
        gamePanel.objects[1] = new OBJ_Key(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        gamePanel.objects[1].worldX = 23 * gamePanel.TILE_SIZE;
        gamePanel.objects[1].worldY = 40 * gamePanel.TILE_SIZE;
        gamePanel.objects[2] = new OBJ_Key(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        gamePanel.objects[2].worldX = 38 * gamePanel.TILE_SIZE;
        gamePanel.objects[2].worldY = 8 * gamePanel.TILE_SIZE;

        gamePanel.objects[3] = new OBJ_Door(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        gamePanel.objects[3].worldX = 10 * gamePanel.TILE_SIZE;
        gamePanel.objects[3].worldY = 12 * gamePanel.TILE_SIZE;
        gamePanel.objects[4] = new OBJ_Door(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        gamePanel.objects[4].worldX = 8 * gamePanel.TILE_SIZE;
        gamePanel.objects[4].worldY = 28 * gamePanel.TILE_SIZE;
        gamePanel.objects[5] = new OBJ_Door(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        gamePanel.objects[5].worldX = 12 * gamePanel.TILE_SIZE;
        gamePanel.objects[5].worldY = 23 * gamePanel.TILE_SIZE;

        gamePanel.objects[6] = new OBJ_Chest(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        gamePanel.objects[6].worldX = 10 * gamePanel.TILE_SIZE;
        gamePanel.objects[6].worldY = 8 * gamePanel.TILE_SIZE;

        gamePanel.objects[7] = new OBJ_Boot(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        gamePanel.objects[7].worldX = 36 * gamePanel.TILE_SIZE;
        gamePanel.objects[7].worldY = 41 * gamePanel.TILE_SIZE;
    }
}
