package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gamePanel) {
        super(gamePanel);
        this.name = EntityLabel.KEY;
        this.image1 = ImageLoader.loadSprite("/objects/heart_full.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.image2 = ImageLoader.loadSprite("/objects/heart_half.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.image3 = ImageLoader.loadSprite("/objects/heart_blank.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }
}
