package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public GamePanel gamePanel;
    public int worldX, worldY, speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, attackRight2, attackLeft1, attackLeft2;
    public EntityDirection direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea;
    public Rectangle attackArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn;
    public int actionLockCounter;

    public boolean invincible;
    public boolean attacking;
    public int invincibleCounter;
    String[] dialogues;
    int dialogueIndex;
    int maxDialogueIndex;

    public int maxLife;
    public int life;

    public BufferedImage image1, image2, image3;
    public EntityLabel name;
    public boolean collision;
    public int type;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.solidArea = new Rectangle(0, 0, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.attackArea = new Rectangle(0, 0, 0, 0);
        this.dialogues = new String[20];

        this.direction = EntityDirection.ANY;
    }

    public void setAction() {}

    public void speak() {
        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        if (dialogueIndex < maxDialogueIndex) {
            dialogueIndex++;
        }

        switch (gamePanel.player.direction) {
            case UP -> direction = EntityDirection.DOWN;
            case DOWN -> direction = EntityDirection.UP;
            case LEFT -> direction = EntityDirection.RIGHT;
            case RIGHT -> direction = EntityDirection.LEFT;
        }
    }

    public void update() {
        setAction();
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.npcs);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);
        boolean contactPlayer = gamePanel.collisionChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer) {
            if (!gamePanel.player.invincible) {
                gamePanel.player.life -= 1;
                gamePanel.player.invincible = true;
            }
        }

        moveNPC();
        invertSprites();
        checkInvincibility();
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

    private void checkInvincibility() {
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
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
                case ANY -> image = image1;
            }
            if(invincible) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            g2.drawImage(image, screenX, screenY, null);
            if(invincible) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}
