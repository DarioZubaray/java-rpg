package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gamePanel;
    public int worldX, worldY, speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public EntityDirection direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn;
    public int actionLockCounter;
    String[] dialogues;
    int dialogueIndex;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.solidArea = new Rectangle(0, 0, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.dialogues = new String[20];
    }

    public void setAction() {}

    public void speak() {}

    public void update() {
        setAction();
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);

        moveNPC();
        invertSprites();
    }

    private void moveNPC() {
        if (!collisionOn) {
            switch (this.direction) {
                case UP -> this.worldY -= speed;
                case DOWN -> this.worldY += speed;
                case LEFT -> this.worldX -= speed;
                case RIGHT -> this.worldX += speed;
            }
        }
    }

    private void invertSprites() {
        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNumber == 1) spriteNumber = 2;
            else if (spriteNumber == 2) spriteNumber = 1;

            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.SCREEN_X;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.SCREEN_Y;

        boolean isWithinXBounds = (worldX + gamePanel.TILE_SIZE > gamePanel.player.worldX - gamePanel.player.SCREEN_X) &&
                (worldX - gamePanel.TILE_SIZE < gamePanel.player.worldX + gamePanel.player.SCREEN_X);
        boolean isWithinYBounds = (worldY + gamePanel.TILE_SIZE > gamePanel.player.worldY - gamePanel.player.SCREEN_Y) &&
                (worldY - gamePanel.TILE_SIZE < gamePanel.player.worldY + gamePanel.player.SCREEN_Y);

        if (isWithinXBounds && isWithinYBounds) {
            BufferedImage image = null;
            switch (direction) {
                case UP -> {
                    if (this.spriteNumber == 1) image = up1;
                    if (this.spriteNumber == 2) image = up2;
                }
                case DOWN -> {
                    if (this.spriteNumber == 1) image = down1;
                    if (this.spriteNumber == 2) image = down2;
                }
                case RIGHT -> {
                    if (this.spriteNumber == 1) image = right1;
                    if (this.spriteNumber == 2) image = right2;
                }
                case LEFT -> {
                    if (this.spriteNumber == 1) image = left1;
                    if (this.spriteNumber == 2) image = left2;
                }
            }
            g2.drawImage(image, screenX, screenY, null);
        }
    }
}
