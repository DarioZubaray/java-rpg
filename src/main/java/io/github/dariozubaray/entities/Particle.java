package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;

public class Particle extends Entity {

    Entity generator;
    Color color;
    int size, xd, yd;

    public Particle(GamePanel gamePanel, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gamePanel);
        this.generator = generator;
        this.color = color;

        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.life = maxLife;
        this.xd = xd;
        this.yd = yd;

        int offset = (gamePanel.TILE_SIZE/2) - (size/2);
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;
    }

    @Override
    public void update() {
        life--;
        worldX += xd * speed;
        worldY += yd * speed;

        if(life == 0) {
            alive = false;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.SCREEN_X;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.SCREEN_Y;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }
}
