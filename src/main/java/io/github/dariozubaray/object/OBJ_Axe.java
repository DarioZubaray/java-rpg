package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;
import io.github.dariozubaray.entities.EntityType;

public class OBJ_Axe extends Entity {

    public OBJ_Axe(GamePanel gamePanel) {
        super(gamePanel);

        this.name = EntityLabel.AXE;
        this.description = EntityLabel.AXE.getDescription();
        this.image1 = ImageLoader.loadSprite(EntityLabel.AXE.getPath(), gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.type = EntityType.AXE;
        this.attackValue = 2;
        this.attackArea.width = 30;
        this.attackArea.height = 30;
    }
}
