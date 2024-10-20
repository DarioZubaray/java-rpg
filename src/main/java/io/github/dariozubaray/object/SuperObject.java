package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.entities.EntityLabel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

@Deprecated
public class SuperObject {

    public BufferedImage image, image2, image3;
    public EntityLabel name;
    public boolean collision;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.SCREEN_X;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.SCREEN_Y;

        boolean isWithinXBounds = (worldX + gamePanel.TILE_SIZE > gamePanel.player.worldX - gamePanel.player.SCREEN_X) &&
                (worldX - gamePanel.TILE_SIZE < gamePanel.player.worldX + gamePanel.player.SCREEN_X);
        boolean isWithinYBounds = (worldY + gamePanel.TILE_SIZE > gamePanel.player.worldY - gamePanel.player.SCREEN_Y) &&
                (worldY - gamePanel.TILE_SIZE < gamePanel.player.worldY + gamePanel.player.SCREEN_Y);

        if (isWithinXBounds && isWithinYBounds) {
            g2.drawImage(image, screenX, screenY, null);
        }
    }
}
