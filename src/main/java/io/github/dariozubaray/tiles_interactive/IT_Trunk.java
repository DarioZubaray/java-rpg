package io.github.dariozubaray.tiles_interactive;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;

public class IT_Trunk extends InteractiveTile {

    public IT_Trunk(GamePanel gamePanel, int worldX, int worldY) {
        super(gamePanel, worldX, worldY);

        this.worldX = gamePanel.TILE_SIZE * worldX;
        this.worldY = gamePanel.TILE_SIZE * worldY;
        image1 = ImageLoader.loadSprite("/tiles_interactive/trunk.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);

        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidArea.width = 0;
        this.solidArea.height = 0;
        this.solidAreaDefaultX = solidArea.x;
        this.solidAreaDefaultY = solidArea.y;
    }
}
