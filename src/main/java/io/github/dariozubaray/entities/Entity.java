package io.github.dariozubaray.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY, speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public EntityDirection direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn;
}
