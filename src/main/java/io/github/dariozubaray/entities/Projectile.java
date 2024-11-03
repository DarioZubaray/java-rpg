package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;

public class Projectile extends Entity {

    Entity user;

    public Projectile(GamePanel gamePanel) {
        super(gamePanel);
    }

    public void set(int worldX, int worldY, EntityDirection direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }

    public void update() {
        if(gamePanel.player.equals(user)) {
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monstersArray);
            if(monsterIndex != 1) {
                gamePanel.player.damageMonster(monsterIndex, attack);
                alive = true;
            }
        } else {
            System.out.println("An enemy shot a projectile");
        }

        switch (direction) {
            case UP -> worldY -= speed;
            case DOWN -> worldY += speed;
            case RIGHT -> worldX += speed;
            case LEFT -> worldX -= speed;
        }

        life--;
        if(life <= 0) {
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNumber == 2) spriteNumber = 1;
            else if(spriteNumber == 1) spriteNumber = 2;
        }
    }
}
