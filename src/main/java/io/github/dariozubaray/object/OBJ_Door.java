package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;

public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gamePanel) {
        super(gamePanel);
        this.name = EntityLabel.DOOR;
        this.description = EntityLabel.DOOR.getDescription();
        this.image1 = ImageLoader.loadSprite(EntityLabel.DOOR.getPath(), gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        collision = true;
    }
}
