package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;

public class OBJ_Shield_Wood extends Entity {

    public OBJ_Shield_Wood(GamePanel gamePanel) {
        super(gamePanel);

        this.name = EntityLabel.SHIELD_WOOD;
        this.description = EntityLabel.SHIELD_WOOD.getDescription();
        this.image1 = ImageLoader.loadSprite(EntityLabel.SHIELD_WOOD.getPath(), gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        defenseValue = 1;
    }
}
