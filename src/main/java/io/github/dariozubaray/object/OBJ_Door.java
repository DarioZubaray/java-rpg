package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;

public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gamePanel) {
        super(gamePanel);
        this.name = ObjectLabel.DOOR;
        this.image1 = ImageLoader.loadSprite("/objects/door.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        collision = true;
    }
}
