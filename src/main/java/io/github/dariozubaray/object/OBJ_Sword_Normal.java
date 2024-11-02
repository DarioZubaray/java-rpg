package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;
import io.github.dariozubaray.entities.EntityType;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gamePanel) {
        super(gamePanel);

        this.name = EntityLabel.SWORD_NORMAL;
        this.description = EntityLabel.SWORD_NORMAL.getDescription();
        this.image1 = ImageLoader.loadSprite(EntityLabel.SWORD_NORMAL.getPath(), gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.type = EntityType.SWORD;
        this.attackValue = 1;
        this.attackArea.width = 36;
        this.attackArea.height = 36;
    }
}
