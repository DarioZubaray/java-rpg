package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;

public class OBJ_Shield_Wood extends Entity {

    public OBJ_Shield_Wood(GamePanel gamePanel) {
        super(gamePanel);

        this.name = EntityLabel.SHIELD_WOOD;
        this.description = "[" + this.name.getName() + "]\nMade by wood.";
        this.image1 = ImageLoader.loadSprite("/objects/shield_wood.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        defenseValue = 1;
    }
}
