package io.github.dariozubaray;

import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.NPC_OldMan;
import io.github.dariozubaray.monster.MON_GreenSlime;
import io.github.dariozubaray.object.OBJ_Axe;
import io.github.dariozubaray.object.OBJ_Boot;
import io.github.dariozubaray.object.OBJ_Coin_Bronze;
import io.github.dariozubaray.object.OBJ_Heart;
import io.github.dariozubaray.object.OBJ_Key;
import io.github.dariozubaray.object.OBJ_ManaCrystal;
import io.github.dariozubaray.object.OBJ_Potion_Red;
import io.github.dariozubaray.object.OBJ_Shield_Blue;
import io.github.dariozubaray.tiles_interactive.IT_DryTree;

public class AssetSetter {

    GamePanel gamePanel;
    int indexCounter;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        indexCounter = 0;
    }

    public void setObject() {
        setNewObject(new OBJ_Boot(this.gamePanel), 36, 41);
        setNewObject(new OBJ_Coin_Bronze(this.gamePanel), 25, 23);
        setNewObject(new OBJ_Coin_Bronze(this.gamePanel), 21, 19);
        setNewObject(new OBJ_Coin_Bronze(this.gamePanel), 26, 21);
        setNewObject(new OBJ_Axe(this.gamePanel), 33, 21);
        setNewObject(new OBJ_Shield_Blue(this.gamePanel), 35, 21);
        setNewObject(new OBJ_Potion_Red(this.gamePanel), 22, 27);

        setNewObject(new OBJ_Heart(this.gamePanel), 22, 29);
        setNewObject(new OBJ_ManaCrystal(this.gamePanel), 22, 31);
    }

    private void setNewObject(Entity object, int worldX, int worldY) {
        gamePanel.objectsArray[indexCounter] = object;
        gamePanel.objectsArray[indexCounter].worldX = worldX * gamePanel.TILE_SIZE;
        gamePanel.objectsArray[indexCounter].worldY = worldY * gamePanel.TILE_SIZE;
        indexCounter++;
    }

    public void setNpc() {
        gamePanel.npcsArray[0] = new NPC_OldMan(gamePanel);
        gamePanel.npcsArray[0].worldX = gamePanel.TILE_SIZE * 21;
        gamePanel.npcsArray[0].worldY = gamePanel.TILE_SIZE * 21;
    }

    public void setMonster() {
        int index = 0;
        gamePanel.monstersArray[index] = new MON_GreenSlime(gamePanel);
        gamePanel.monstersArray[index].worldX = gamePanel.TILE_SIZE * 23;
        gamePanel.monstersArray[index].worldY = gamePanel.TILE_SIZE * 36;
        index++;
        gamePanel.monstersArray[index] = new MON_GreenSlime(gamePanel);
        gamePanel.monstersArray[index].worldX = gamePanel.TILE_SIZE * 23;
        gamePanel.monstersArray[index].worldY = gamePanel.TILE_SIZE * 37;
        index++;
        gamePanel.monstersArray[index] = new MON_GreenSlime(gamePanel);
        gamePanel.monstersArray[index].worldX = gamePanel.TILE_SIZE * 24;
        gamePanel.monstersArray[index].worldY = gamePanel.TILE_SIZE * 37;
        index++;
        gamePanel.monstersArray[index] = new MON_GreenSlime(gamePanel);
        gamePanel.monstersArray[index].worldX = gamePanel.TILE_SIZE * 34;
        gamePanel.monstersArray[index].worldY = gamePanel.TILE_SIZE * 42;
        index++;
        gamePanel.monstersArray[index] = new MON_GreenSlime(gamePanel);
        gamePanel.monstersArray[index].worldX = gamePanel.TILE_SIZE * 38;
        gamePanel.monstersArray[index].worldY = gamePanel.TILE_SIZE * 42;
    }

    public void setInteractiveTiles() {
        int index = 0;
        gamePanel.interactiveTiles[index] = new IT_DryTree(gamePanel, 27, 11);
        index++;
        gamePanel.interactiveTiles[index] = new IT_DryTree(gamePanel, 28, 11);
        index++;
        gamePanel.interactiveTiles[index] = new IT_DryTree(gamePanel, 29, 11);
        index++;
        gamePanel.interactiveTiles[index] = new IT_DryTree(gamePanel, 30, 11);
        index++;
        gamePanel.interactiveTiles[index] = new IT_DryTree(gamePanel, 31, 11);
    }
}
