package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
    }

    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
    }

    public void update() {
        if (keyHandler.upPressed) {
            y -= speed;
        } else if (keyHandler.downPressed) {
            y += speed;
        } else if (keyHandler.rightPressed) {
            x += speed;
        } else if (keyHandler.leftPressed) {
            x -= speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}
