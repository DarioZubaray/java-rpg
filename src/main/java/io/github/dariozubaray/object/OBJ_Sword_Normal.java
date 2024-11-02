package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gamePanel) {
        super(gamePanel);

        this.name = EntityLabel.SWORD_NORMAL;
        this.description = "[" + this.name.getName() + "]\nAn old sword.";
        this.image1 = ImageLoader.loadSprite("/objects/sword_normal.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        attackValue = 1;
    }
}
