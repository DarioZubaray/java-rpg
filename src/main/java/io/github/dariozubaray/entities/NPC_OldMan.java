package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);

        this.direction = EntityDirection.DOWN;
        this.speed = 1;

        getOldManImage();
    }

    @Override
    public void setAction() {
        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                this.direction = EntityDirection.DOWN;
            }
            if (i > 25 && i <= 50) {
                this.direction = EntityDirection.UP;
            }
            if (i > 50 && i <= 75) {
                this.direction = EntityDirection.LEFT;
            }
            if (i > 75 && i <= 100) {
                this.direction = EntityDirection.RIGHT;
            }

            actionLockCounter = 0;
        }
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
