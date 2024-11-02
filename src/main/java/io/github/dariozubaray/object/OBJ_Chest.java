package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePanel gamePanel) {
        super(gamePanel);
        this.name = EntityLabel.CHEST;
        this.description = EntityLabel.CHEST.getDescription();
        this.image1 = ImageLoader.loadSprite(EntityLabel.CHEST.getPath(), gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }
}
