package io.github.dariozubaray;

import io.github.dariozubaray.entities.EntityDirection;
import io.github.dariozubaray.sound.SoundLabel;

public class EventHandler {

    GamePanel gamepanel;
    EventRect[][] eventRect;
    int previousEventX, previousEventY;
    boolean canActiveFallPitEvent;

    public EventHandler(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        this.canActiveFallPitEvent = true;
        this.eventRect = new EventRect[gamepanel.MAX_WORLD_COL][gamepanel.MAX_WORLD_ROW];

        int col = 0, row = 0;
        while(col < gamepanel.MAX_WORLD_COL && row < gamepanel.MAX_WORLD_ROW) {

            this.eventRect[col][row] = new EventRect();

            col++;
            if(col == gamepanel.MAX_WORLD_COL) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {
        checkPlayerDistance();
        if(canActiveFallPitEvent) {
            if (hit(27, 17, EntityDirection.RIGHT)) damagePit(27, 16, GameState.DIALOGUE);
        }
        if (hit(23, 12, EntityDirection.UP)) healingPool(23, 12, GameState.DIALOGUE);
        if (hit(19, 17, EntityDirection.LEFT)) teleport(GameState.DIALOGUE);
    }

    private void checkPlayerDistance() {
        int xDistance = Math.abs(gamepanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamepanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamepanel.TILE_SIZE) {
            this.canActiveFallPitEvent = true;
        }
    }

    public boolean hit(int eventCol, int eventRow, EntityDirection direction) {
        boolean hit = false;

        this.gamepanel.player.solidArea.x = this.gamepanel.player.worldX + this.gamepanel.player.solidArea.x;
        this.gamepanel.player.solidArea.y = this.gamepanel.player.worldY + this.gamepanel.player.solidArea.y;
        this.eventRect[eventCol][eventRow].x = eventCol * this.gamepanel.TILE_SIZE + this.eventRect[eventCol][eventRow].x;
        this.eventRect[eventCol][eventRow].y = eventRow * this.gamepanel.TILE_SIZE + this.eventRect[eventCol][eventRow].y;

        if (this.gamepanel.player.solidArea.intersects(this.eventRect[eventCol][eventRow]) &&
                !eventRect[eventCol][eventRow].eventDone) {
            if (this.gamepanel.player.direction.equals(direction) || this.gamepanel.player.direction.equals(EntityDirection.ANY)) {
                hit = true;

                previousEventX = gamepanel.player.worldX;
                previousEventY = gamepanel.player.worldY;
            }
        }

        this.gamepanel.player.solidArea.x = gamepanel.player.solidAreaDefaultX;
        this.gamepanel.player.solidArea.y = gamepanel.player.solidAreaDefaultY;
        this.eventRect[eventCol][eventRow].x = this.eventRect[eventCol][eventRow].eventRectDefaultX;
        this.eventRect[eventCol][eventRow].y = this.eventRect[eventCol][eventRow].eventRectDefaultY;
        return hit;
    }

    public void damagePit(int eventCol, int eventRow, GameState gameState) {
//        this.gamepanel.gameState = gameState;
//        this.gamepanel.ui.currentDialogue = "You fall into a pit!";
        this.gamepanel.ui.addMessage("You fall into a pit!");
        this.gamepanel.playSoundEffect(SoundLabel.RECEIVE_DAMAGE.getAudioIndex());
        this.gamepanel.player.life -= 1;
        //this.eventRect[eventCol][eventRow].eventDone = true;
        this.canActiveFallPitEvent = false;
    }

    public void healingPool(int col, int eventRow, GameState gameState) {
        if(gamepanel.keyHandler.enterPressed) {
//            gamepanel.gameState = gameState;
//            this.gamepanel.ui.currentDialogue = "You drink he water.\nYour life has been recovered.";
            gamepanel.player.attackCanceled = true;
            gamepanel.playSoundEffect(SoundLabel.POWER_UP.getAudioIndex());
            this.gamepanel.ui.addMessage("You drink the water.");
            this.gamepanel.ui.addMessage("Your life and mana has been recovered.");
            this.gamepanel.player.life = this.gamepanel.player.maxLife;
            this.gamepanel.player.mana = this.gamepanel.player.maxMana;
            this.gamepanel.assetSetter.setMonster();
        }
    }

    public void teleport(GameState gameState) {
//        gamepanel.gameState = gameState;
//        this.gamepanel.ui.currentDialogue = "Teleported!";
        this.gamepanel.ui.addMessage("Teleported!");
        this.gamepanel.playSoundEffect(SoundLabel.COIN.getAudioIndex());
        this.gamepanel.player.worldX = gamepanel.TILE_SIZE * 38;
        this.gamepanel.player.worldY = gamepanel.TILE_SIZE * 9;
    }
}
