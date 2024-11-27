package io.github.dariozubaray.tiles_interactive;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.entities.Entity;
import static io.github.dariozubaray.entities.EntityDirection.ANY;

public class InteractiveTile extends Entity {

    GamePanel gamePanel;
    public boolean destructible;

    public InteractiveTile(GamePanel gamePanel, int worldX, int worldY) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        direction = ANY;
    }

    public boolean isCorrectItem(Entity entity) {
        return false;
    }
    public void playSoundEffect() {}
    public InteractiveTile getDestroyedForm() {
        return null;
    }
    public void update() {
        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
}
