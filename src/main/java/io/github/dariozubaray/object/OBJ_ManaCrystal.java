package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;

public class OBJ_ManaCrystal extends Entity {

    public OBJ_ManaCrystal(GamePanel gamePanel) {
        super(gamePanel);
        this.name = EntityLabel.MANA;
        this.image1 = ImageLoader.loadSprite("/objects/manacrystal_full.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.image2 = ImageLoader.loadSprite("/objects/manacrystal_blank.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }
}
