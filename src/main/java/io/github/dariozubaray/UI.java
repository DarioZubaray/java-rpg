package io.github.dariozubaray;

import io.github.dariozubaray.object.OBJ_Boot;
import io.github.dariozubaray.object.OBJ_Key;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

    GamePanel gamePanel;
    Font arialFont;
    OBJ_Key key;
    OBJ_Boot boot;
    public boolean messageOn;
    public String message;
    int messageCounter;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.arialFont = new Font("Arial", Font.PLAIN, 40);
        this.key = new OBJ_Key();
        this.boot = new OBJ_Boot();
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arialFont);
        g2.setColor(Color.WHITE);
        g2.drawImage(key.image, gamePanel.TILE_SIZE/2, gamePanel.TILE_SIZE/2, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
        g2.drawString("x " + gamePanel.player.hasKey, 75, 65);
        if (gamePanel.player.speed == 6) {
            g2.drawImage(boot.image, gamePanel.TILE_SIZE / 2, gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
        }

        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gamePanel.TILE_SIZE/2, gamePanel.TILE_SIZE * 10);
            messageCounter++;
            if(messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
