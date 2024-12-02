package io.github.dariozubaray.tiles_interactive;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.EntityType;
import io.github.dariozubaray.sound.SoundLabel;
import java.awt.Color;

public class IT_DryTree extends InteractiveTile {

    public IT_DryTree(GamePanel gamePanel, int worldX, int worldY) {
        super(gamePanel, worldX, worldY);

        this.worldX = gamePanel.TILE_SIZE * worldX;
        this.worldY = gamePanel.TILE_SIZE * worldY;
        destructible = true;
        life = 2;
        image1 = ImageLoader.loadSprite("/tiles_interactive/drytree.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }

    @Override
    public boolean isCorrectItem(Entity entity) {
        return EntityType.AXE.equals(entity.currentWeapon.type);
    }
    @Override
    public void playSoundEffect() {
        gamePanel.playSoundEffect(SoundLabel.CUT_TREE.getAudioIndex());
    }
    @Override
    public InteractiveTile getDestroyedForm() {
        return new IT_Trunk(gamePanel, worldX / gamePanel.TILE_SIZE, worldY / gamePanel.TILE_SIZE);
    }

    public Color getParticleColor() {
        return new Color(65, 50, 30);
    }
    public int getParticleSize() {
        return 6;
    }
    public int getParticleSpeed() {
        return 1;
    }
    public int getParticleMaxLife() {
        return 20;
    }
}
