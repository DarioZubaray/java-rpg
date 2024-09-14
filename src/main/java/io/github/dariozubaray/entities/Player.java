package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.KeyHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
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
                this.y -= speed;
            } else if (keyHandler.downPressed) {
                this.direction = "down";
                this.y += speed;
            } else if (keyHandler.rightPressed) {
                this.direction = "right";
                this.x += speed;
            } else if (keyHandler.leftPressed) {
                this.direction = "left";
                this.x -= speed;
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
        //g2.setColor(Color.LIGHT_GRAY);
        //g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (this.spriteNumber == 1) image = up1;
                if (this.spriteNumber == 2) image = up2;
                break;
            case "down":
                if (this.spriteNumber == 1) image = down1;
                if (this.spriteNumber == 2) image = down2;
                break;
            case "right":
                if (this.spriteNumber == 1) image = right1;
                if (this.spriteNumber == 2) image = right2;
                break;
            case "left":
                if (this.spriteNumber == 1) image = left1;
                if (this.spriteNumber == 2) image = left2;
                break;
        }

        g2.drawImage(image, this.x, this.y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
