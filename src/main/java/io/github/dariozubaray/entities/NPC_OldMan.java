package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import java.util.Random;

public class NPC_OldMan extends Entity {

    int maxDialogueIndex;

    public NPC_OldMan(GamePanel gamePanel) {
        super(gamePanel);

        this.direction = EntityDirection.DOWN;
        this.speed = 1;

        getOldManImage();
        serDialogue();
    }

    public void serDialogue() {
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So you've come to this island to find the treasure?.";
        dialogues[2] = "I used to be a great wizard but now... I'm a bit too old for taking an adventure.";
        dialogues[3] = "Well, good luck on you.";
        maxDialogueIndex = 3;
    }

    @Override
    public void setAction() {
        actionLockCounter++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                this.direction = EntityDirection.DOWN;
            }
            if (i > 25 && i <= 50) {
                this.direction = EntityDirection.UP;
            }
            if (i > 50 && i <= 75) {
                this.direction = EntityDirection.LEFT;
            }
            if (i > 75) {
                this.direction = EntityDirection.RIGHT;
            }

            actionLockCounter = 0;
        }
    }

    @Override
    public void speak() {
        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        if (dialogueIndex < maxDialogueIndex) {
            dialogueIndex++;
        }

        switch (gamePanel.player.direction) {
            case UP -> direction = EntityDirection.DOWN;
            case DOWN -> direction = EntityDirection.UP;
            case LEFT -> direction = EntityDirection.RIGHT;
            case RIGHT -> direction = EntityDirection.LEFT;
        }
    }

    public void getOldManImage() {
        up1 = ImageLoader.loadSprite("/npc/oldman_up_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        up2 = ImageLoader.loadSprite("/npc/oldman_up_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        down1 = ImageLoader.loadSprite("/npc/oldman_down_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        down2 = ImageLoader.loadSprite("/npc/oldman_down_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        right1 = ImageLoader.loadSprite("/npc/oldman_right_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        right2 = ImageLoader.loadSprite("/npc/oldman_right_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        left1 = ImageLoader.loadSprite("/npc/oldman_left_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        left2 = ImageLoader.loadSprite("/npc/oldman_left_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }

}
