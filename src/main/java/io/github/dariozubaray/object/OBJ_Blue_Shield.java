package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;

public class OBJ_Blue_Shield extends Entity {

    public OBJ_Blue_Shield(GamePanel gamePanel) {
        super(gamePanel);

        this.name = EntityLabel.BLUE_SHIELD;
        this.description = EntityLabel.BLUE_SHIELD.getDescription();
        this.image1 = ImageLoader.loadSprite(EntityLabel.BLUE_SHIELD.getPath(), gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        defenseValue = 1;
    }
}
