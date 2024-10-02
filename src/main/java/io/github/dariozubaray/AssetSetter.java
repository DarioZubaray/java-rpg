package io.github.dariozubaray;

import io.github.dariozubaray.object.OBJ_Boot;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.objects[0] = new OBJ_Boot(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        gamePanel.objects[0].worldX = 36 * gamePanel.TILE_SIZE;
        gamePanel.objects[0].worldY = 41 * gamePanel.TILE_SIZE;
    }
}
