package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);

        this.direction = EntityDirection.DOWN;
        this.speed = 1;

        getOldManImage();
    }

    public void getOldManImage() {
        up1 = ImageLoader.loadSprite("/npc/oldman_up_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        up2 = ImageLoader.loadSprite("/npc/oldman_up_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        down1 = ImageLoader.loadSprite("/npc/oldman_down_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        down2 = ImageLoader.loadSprite("/npc/oldman_down_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        right1 = ImageLoader.loadSprite("/npc/oldman_right_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        right2 = ImageLoader.loadSprite("/npc/oldman_right_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        left1 = ImageLoader.loadSprite("/npc/oldman_left_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        left2 = ImageLoader.loadSprite("/npc/oldman_left_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }

}
