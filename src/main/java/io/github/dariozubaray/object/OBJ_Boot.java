package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;

public class OBJ_Boot extends Entity {

    public OBJ_Boot(GamePanel gamePanel) {
        super(gamePanel);
        this.name = EntityLabel.BOOT;
        this.image1 = ImageLoader.loadSprite("/objects/boots.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }
}
