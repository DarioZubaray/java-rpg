package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePanel gamePanel) {
        super(gamePanel);
        this.name = ObjectLabel.CHEST;
        this.image1 = ImageLoader.loadSprite("/objects/chest.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }
}
