package io.github.dariozubaray.tiles_interactive;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.entities.Entity;
import static io.github.dariozubaray.entities.EntityDirection.ANY;
import java.awt.Graphics2D;

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

    @Override
    public void update() {
        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.SCREEN_X;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.SCREEN_Y;

        boolean isWithinXBounds = (worldX + gamePanel.TILE_SIZE > gamePanel.player.worldX - gamePanel.player.SCREEN_X) &&
                (worldX - gamePanel.TILE_SIZE < gamePanel.player.worldX + gamePanel.player.SCREEN_X);
        boolean isWithinYBounds = (worldY + gamePanel.TILE_SIZE > gamePanel.player.worldY - gamePanel.player.SCREEN_Y) &&
                (worldY - gamePanel.TILE_SIZE < gamePanel.player.worldY + gamePanel.player.SCREEN_Y);

        if (isWithinXBounds && isWithinYBounds) {
            g2.drawImage(image1, screenX, screenY, null);
        }
    }
}
