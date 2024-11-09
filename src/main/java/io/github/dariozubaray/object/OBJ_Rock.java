package io.github.dariozubaray.object;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.EntityLabel;
import io.github.dariozubaray.entities.Projectile;

public class OBJ_Rock extends Projectile {

    public OBJ_Rock(GamePanel gamePanel) {
        super(gamePanel);

        name = EntityLabel.ROCK;
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getProjectileImages();
    }

    public void getProjectileImages() {
        up1 = ImageLoader.loadSprite("/projectile/rock_image_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        up2 = ImageLoader.loadSprite("/projectile/rock_image_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        down1 = ImageLoader.loadSprite("/projectile/rock_image_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        down2 = ImageLoader.loadSprite("/projectile/rock_image_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        right1 = ImageLoader.loadSprite("/projectile/rock_image_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        right2 = ImageLoader.loadSprite("/projectile/rock_image_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        left1 = ImageLoader.loadSprite("/projectile/rock_image_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        left2 = ImageLoader.loadSprite("/projectile/rock_image_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }
}
