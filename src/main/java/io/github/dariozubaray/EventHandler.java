package io.github.dariozubaray;

import io.github.dariozubaray.entities.EntityDirection;
import java.awt.Rectangle;

public class EventHandler {

    GamePanel gamepanel;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gamepanel) {
        this.gamepanel = gamepanel;

        this.eventRect = new Rectangle();
        this.eventRect.x = 23;
        this.eventRect.y = 23;
        this.eventRect.width = 2;
        this.eventRect.height = 2;

        this.eventRectDefaultX = this.eventRect.x;
        this.eventRectDefaultY = this.eventRect.y;
    }

    public void checkEvent() {
        if (hit(27, 17, EntityDirection.RIGHT)) damagePit(GameState.DIALOGUE);
        if(hit(23, 12, EntityDirection.UP)) healingPool(GameState.DIALOGUE);
        if(hit(19, 17, EntityDirection.LEFT)) teleport(GameState.DIALOGUE);
    }

    public boolean hit(int eventCol, int eventRaw, EntityDirection direction) {
        boolean hit = false;

        this.gamepanel.player.solidArea.x = this.gamepanel.player.worldX + this.gamepanel.player.solidArea.x;
        this.gamepanel.player.solidArea.y = this.gamepanel.player.worldY + this.gamepanel.player.solidArea.y;
        this.eventRect.x = eventCol * this.gamepanel.TILE_SIZE + this.eventRect.x;
        this.eventRect.y = eventRaw * this.gamepanel.TILE_SIZE + this.eventRect.y;

        if (this.gamepanel.player.solidArea.intersects(this.eventRect)) {
            if (this.gamepanel.player.direction.equals(direction) || this.gamepanel.player.direction.equals(EntityDirection.ANY)) {
                hit = true;
            }
        }

        this.gamepanel.player.solidArea.x = gamepanel.player.solidAreaDefaultX;
        this.gamepanel.player.solidArea.y = gamepanel.player.solidAreaDefaultY;
        this.eventRect.x = eventRectDefaultX;
        this.eventRect.y = eventRectDefaultY;
        return hit;
    }

    public void damagePit(GameState gameState) {
        this.gamepanel.gameState = gameState;
        this.gamepanel.ui.currentDialogue = "You fall into a pit!";
        this.gamepanel.player.life -= 1;
    }

    public void healingPool(GameState gameState) {
        if(gamepanel.keyHandler.enterPressed) {
            gamepanel.gameState = gameState;
            this.gamepanel.ui.currentDialogue = "You drink he water.\nYour life has been recovered.";
            this.gamepanel.player.life = this.gamepanel.player.maxLife;
        }
    }

    public void teleport(GameState gameState) {
        gamepanel.gameState = gameState;
        this.gamepanel.ui.currentDialogue = "Teleport!";
        this.gamepanel.player.worldX = gamepanel.TILE_SIZE * 38;
        this.gamepanel.player.worldY = gamepanel.TILE_SIZE * 9;
    }
}
