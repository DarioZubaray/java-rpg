package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);
        this.name = EntityLabel.KEY;
        this.description = "[" + this.name.getName() + "]\nIt opens a door.";
        this.image1 = ImageLoader.loadSprite("/objects/key.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }
}

