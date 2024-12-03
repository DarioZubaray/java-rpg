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

    @Override
    public void update() {
        if(gamePanel.player.equals(user)) {
            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monstersArray);
            if(monsterIndex != -1) {
                gamePanel.player.damageMonster(monsterIndex, attack);
                generateParticle(user.projectile, gamePanel.monstersArray[monsterIndex]);
                alive = false;
            }
        } else {
            boolean contactPlayer = gamePanel.collisionChecker.checkPlayer(this);
            if(!gamePanel.player.invincible && contactPlayer) {
                damagePlayer(attack);
                generateParticle(user.projectile, gamePanel.player);
                alive = false;
            }
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

    public boolean haveResource(Entity user) {
        return false;
    }

    public void subtractResource(Entity user) {}
}
