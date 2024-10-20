package io.github.dariozubaray;

import io.github.dariozubaray.entities.NPC_OldMan;
import io.github.dariozubaray.object.OBJ_Boot;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.objects[0] = new OBJ_Boot(this.gamePanel);
        gamePanel.objects[0].worldX = 36 * gamePanel.TILE_SIZE;
        gamePanel.objects[0].worldY = 41 * gamePanel.TILE_SIZE;
    }

    public void setNpc() {
        gamePanel.npcs[0] = new NPC_OldMan(gamePanel);
        gamePanel.npcs[0].worldX = gamePanel.TILE_SIZE * 21;
        gamePanel.npcs[0].worldY = gamePanel.TILE_SIZE * 21;
    }
}
