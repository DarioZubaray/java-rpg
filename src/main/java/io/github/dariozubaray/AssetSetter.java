package io.github.dariozubaray;

import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.NPC_OldMan;
import io.github.dariozubaray.monster.MON_GreenSlime;
import io.github.dariozubaray.object.OBJ_Axe;
import io.github.dariozubaray.object.OBJ_Boot;
import io.github.dariozubaray.object.OBJ_Key;
import io.github.dariozubaray.object.OBJ_Potion_Red;
import io.github.dariozubaray.object.OBJ_Shield_Blue;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        int i = 0;
        i = setNewObject(i, new OBJ_Boot(this.gamePanel), 36, 41);
        i = setNewObject(i, new OBJ_Key(this.gamePanel), 25, 23);
        i = setNewObject(i, new OBJ_Key(this.gamePanel), 21, 19);
        i = setNewObject(i, new OBJ_Key(this.gamePanel), 26, 21);
        i = setNewObject(i, new OBJ_Axe(this.gamePanel), 33, 21);
        i = setNewObject(i, new OBJ_Shield_Blue(this.gamePanel), 35, 21);
        setNewObject(i, new OBJ_Potion_Red(this.gamePanel), 22, 27);
    }

    private int setNewObject(int i, Entity object, int worldX, int worldY) {
        gamePanel.objects[i] = object;
        gamePanel.objects[i].worldX = worldX * gamePanel.TILE_SIZE;
        gamePanel.objects[i].worldY = worldY * gamePanel.TILE_SIZE;
        i++;
        return i;
    }

    public void setNpc() {
        gamePanel.npcs[0] = new NPC_OldMan(gamePanel);
        gamePanel.npcs[0].worldX = gamePanel.TILE_SIZE * 21;
        gamePanel.npcs[0].worldY = gamePanel.TILE_SIZE * 21;
    }

    public void setMonster() {
        int index = 0;
        gamePanel.monsters[index] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[index].worldX = gamePanel.TILE_SIZE * 23;
        gamePanel.monsters[index].worldY = gamePanel.TILE_SIZE * 36;
        index++;
        gamePanel.monsters[index] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[index].worldX = gamePanel.TILE_SIZE * 23;
        gamePanel.monsters[index].worldY = gamePanel.TILE_SIZE * 37;
        index++;
        gamePanel.monsters[index] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[index].worldX = gamePanel.TILE_SIZE * 24;
        gamePanel.monsters[index].worldY = gamePanel.TILE_SIZE * 37;
        index++;
        gamePanel.monsters[index] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[index].worldX = gamePanel.TILE_SIZE * 34;
        gamePanel.monsters[index].worldY = gamePanel.TILE_SIZE * 42;
        index++;
        gamePanel.monsters[index] = new MON_GreenSlime(gamePanel);
        gamePanel.monsters[index].worldX = gamePanel.TILE_SIZE * 38;
        gamePanel.monsters[index].worldY = gamePanel.TILE_SIZE * 42;
    }
}
