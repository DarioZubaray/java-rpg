package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.KeyHandler;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int SCREEN_X;
    public final int SCREEN_Y;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        SCREEN_X = (gamePanel.SCREEN_WIDTH /2) - (gamePanel.TILE_SIZE /2);
        SCREEN_Y = (gamePanel.SCREEN_HEIGHT /2) - (gamePanel.TILE_SIZE /2);

        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidArea.width = 32;
        this.solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.worldX = gamePanel.TILE_SIZE * 23;
        this.worldY = gamePanel.TILE_SIZE * 21;
        this.speed = 4;
        this.direction = "right";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed) {

            if (keyHandler.upPressed) {
                this.direction = "up";
            } else if (keyHandler.downPressed) {
                this.direction = "down";
            } else if (keyHandler.rightPressed) {
                this.direction = "right";
            } else {
                this.direction = "left";
            }

            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            if (!collisionOn) {
                switch (this.direction) {
                    case "up" -> this.worldY -= speed;
                    case "down" -> this.worldY += speed;
                    case "left" -> this.worldX -= speed;
                    case "right" -> this.worldX += speed;
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

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (this.spriteNumber == 1) image = up1;
                if (this.spriteNumber == 2) image = up2;
            }
            case "down" -> {
                if (this.spriteNumber == 1) image = down1;
                if (this.spriteNumber == 2) image = down2;
            }
            case "right" -> {
                if (this.spriteNumber == 1) image = right1;
                if (this.spriteNumber == 2) image = right2;
            }
            case "left" -> {
                if (this.spriteNumber == 1) image = left1;
                if (this.spriteNumber == 2) image = left2;
            }
        }

        g2.drawImage(image, this.SCREEN_X, this.SCREEN_Y, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
    }
}
