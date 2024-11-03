package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;
import io.github.dariozubaray.entities.EntityType;

public class OBJ_Boot extends Entity {

    public OBJ_Boot(GamePanel gamePanel) {
        super(gamePanel);
        this.name = EntityLabel.BOOT;
        this.description = EntityLabel.BOOT.getDescription();
        this.type = EntityType.CONSUMABLE;
        this.image1 = ImageLoader.loadSprite(EntityLabel.BOOT.getPath(), gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }
}
