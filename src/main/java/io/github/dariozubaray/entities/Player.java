package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.KeyHandler;

import io.github.dariozubaray.object.ObjectLabel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int SCREEN_X;
    public final int SCREEN_Y;
    public int hasKey = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        SCREEN_X = (gamePanel.SCREEN_WIDTH /2) - (gamePanel.TILE_SIZE /2);
        SCREEN_Y = (gamePanel.SCREEN_HEIGHT /2) - (gamePanel.TILE_SIZE /2);

        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 32;
        this.solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.worldX = gamePanel.TILE_SIZE * 23;
        this.worldY = gamePanel.TILE_SIZE * 21;
        this.speed = 4;
        this.direction = EntityDirection.DOWN;
    }

    public void getPlayerImage() {
        up1 = ImageLoader.loadSprite("/player/boy_up_1.png");
        up2 = ImageLoader.loadSprite("/player/boy_up_2.png");
        down1 = ImageLoader.loadSprite("/player/boy_down_1.png");
        down2 = ImageLoader.loadSprite("/player/boy_down_2.png");
        right1 = ImageLoader.loadSprite("/player/boy_right_1.png");
        right2 = ImageLoader.loadSprite("/player/boy_right_2.png");
        left1 = ImageLoader.loadSprite("/player/boy_left_1.png");
        left2 = ImageLoader.loadSprite("/player/boy_left_2.png");
    }

    public void update() {
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed) {

            if (keyHandler.upPressed) {
                this.direction = EntityDirection.UP;
            } else if (keyHandler.downPressed) {
                this.direction = EntityDirection.DOWN;
            } else if (keyHandler.rightPressed) {
                this.direction = EntityDirection.RIGHT;
            } else {
                this.direction = EntityDirection.LEFT;
            }

            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            if (!collisionOn) {
                switch (this.direction) {
                    case UP -> this.worldY -= speed;
                    case DOWN -> this.worldY += speed;
                    case LEFT -> this.worldX -= speed;
                    case RIGHT -> this.worldX += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if(spriteNumber == 1) spriteNumber = 2;
                else if (spriteNumber == 2) spriteNumber = 1;

                spriteCounter = 0;
            }

        }
    }

    public void pickUpObject(int index) {
        if (index == -1) {
            return;
        }
        ObjectLabel objectLabel = gamePanel.objects[index].name;
        switch (objectLabel) {
            case KEY -> {
                gamePanel.playSoundEffect(objectLabel.getAudioIndex());
                hasKey++;
                gamePanel.objects[index] = null;
                gamePanel.ui.showMessage("You get a key!");
            }
            case DOOR -> {
                if (hasKey > 0) {
                    gamePanel.playSoundEffect(objectLabel.getAudioIndex());
                    gamePanel.objects[index] = null;
                    hasKey--;
                    gamePanel.ui.showMessage("You opened the door!");
                } else {
                    gamePanel.ui.showMessage("You need a key!");
                }
            }
            case BOOT -> {
                gamePanel.playSoundEffect(objectLabel.getAudioIndex());
                gamePanel.objects[index] = null;
                speed += 2;
            }
        }

    }

    public void draw(Graphics2D g2) {
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

        g2.drawImage(image, this.SCREEN_X, this.SCREEN_Y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }
}
