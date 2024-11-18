package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.GameState;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;
import io.github.dariozubaray.entities.EntityType;
import io.github.dariozubaray.sound.SoundLabel;

public class OBJ_Potion_Red extends Entity {

    public OBJ_Potion_Red(GamePanel gamePanel) {
        super(gamePanel);
        this.name = EntityLabel.POTION_RED;
        this.description = EntityLabel.POTION_RED.getDescription() + value + ".";
        this.image1 = ImageLoader.loadSprite(EntityLabel.POTION_RED.getPath(), gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.type = EntityType.CONSUMABLE;
        value = 5;
    }

    @Override
    public void use(Entity entity) {
        gamePanel.ui.addMessage("You drink the " + name.getName() + "!\nYour life has been recovered by " + value + ".");
        gamePanel.playSoundEffect(SoundLabel.POWER_UP.getAudioIndex());
        entity.life += value;
    }
}