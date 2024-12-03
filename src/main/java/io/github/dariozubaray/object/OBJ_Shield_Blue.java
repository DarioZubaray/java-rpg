package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;
import io.github.dariozubaray.entities.EntityType;

public class OBJ_Shield_Blue extends Entity {

    public OBJ_Shield_Blue(GamePanel gamePanel) {
        super(gamePanel);

        this.name = EntityLabel.SHIELD_BLUE;
        this.description = EntityLabel.SHIELD_BLUE.getDescription();
        this.image1 = ImageLoader.loadSprite(EntityLabel.SHIELD_BLUE.getPath(), gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.type = EntityType.SHIELD;
        this.defenseValue = 2;
    }
}