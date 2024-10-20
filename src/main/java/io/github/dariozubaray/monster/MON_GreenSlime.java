package io.github.dariozubaray.monster;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityDirection;
import io.github.dariozubaray.entities.EntityLabel;
import java.util.Random;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);

        type = 2;
        name = EntityLabel.GREEN_SLIME;
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImages();
    }

    public void getImages() {
        int scaleTo = this.gamePanel.TILE_SIZE;
        up1 = ImageLoader.loadSprite("/monster/greenslime_down_1.png", scaleTo, scaleTo);
        up2 = ImageLoader.loadSprite("/monster/greenslime_down_2.png", scaleTo, scaleTo);
        down1 = ImageLoader.loadSprite("/monster/greenslime_down_1.png", scaleTo, scaleTo);
        down2 = ImageLoader.loadSprite("/monster/greenslime_down_2.png", scaleTo, scaleTo);
        left1 = ImageLoader.loadSprite("/monster/greenslime_down_1.png", scaleTo, scaleTo);
        left2 = ImageLoader.loadSprite("/monster/greenslime_down_2.png", scaleTo, scaleTo);
        right1 = ImageLoader.loadSprite("/monster/greenslime_down_1.png", scaleTo, scaleTo);
        right2 = ImageLoader.loadSprite("/monster/greenslime_down_2.png", scaleTo, scaleTo);
    }

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
            if (i > 75) {
                this.direction = EntityDirection.RIGHT;
            }

            actionLockCounter = 0;
        }
    }
}
