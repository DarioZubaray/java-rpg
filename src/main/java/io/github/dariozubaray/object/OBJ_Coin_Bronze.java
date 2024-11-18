package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityLabel;
import io.github.dariozubaray.entities.EntityType;
import io.github.dariozubaray.sound.SoundLabel;

public class OBJ_Coin_Bronze extends Entity {

    public OBJ_Coin_Bronze(GamePanel gamePanel) {
        super(gamePanel);

        name = EntityLabel.COIN_BRONZE;
        type = EntityType.PICK_UP_ONLY;
        value = 1;
        image1 = ImageLoader.loadSprite(name.getPath(), gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }

    @Override
    public void use(Entity entity) {
        gamePanel.ui.addMessage("Coin +" + value + ".");
        gamePanel.playSoundEffect(SoundLabel.COIN.getAudioIndex());
        entity.coins += value;
    }
}
