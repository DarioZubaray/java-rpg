package io.github.dariozubaray;

import io.github.dariozubaray.object.OBJ_Boot;
import io.github.dariozubaray.object.OBJ_Key;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    Font arial40Plain, arial30Plain;
    Font arial80b;
    OBJ_Key key;
    OBJ_Boot boot;
    public boolean messageOn;
    public String message;
    int messageCounter;
    public boolean gameFinished;
    double playTime;
    DecimalFormat decimalFormat;

    public UI(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.arial40Plain = new Font("Arial", Font.PLAIN, 40);
        this.arial30Plain = new Font("Arial", Font.PLAIN, 40);
        this.arial80b = new Font("Arial", Font.BOLD, 80);
        this.key = new OBJ_Key(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.boot = new OBJ_Boot(gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        decimalFormat = new DecimalFormat("#0.0");
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial40Plain);
        g2.setColor(Color.WHITE);

        if (gameFinished) {
            keyHandler.debugMode = false;
            drawCenteredText(g2, "You found the treasure!", - gamePanel.TILE_SIZE * 3);
            drawCenteredText(g2, "Your time is: " + decimalFormat.format(playTime) + "!!", gamePanel.TILE_SIZE * 4);

            g2.setFont(arial80b);
            g2.setColor(Color.yellow);
            drawCenteredText(g2, "Congratulations!", gamePanel.TILE_SIZE * 2);

            gamePanel.gameThread = null;
            return;
        }
        playTime += (double) 1/60;
        g2.drawString("Time: " + decimalFormat.format(playTime), gamePanel.TILE_SIZE * 11, 65);

        g2.drawImage(key.image, gamePanel.TILE_SIZE/2, gamePanel.TILE_SIZE/2, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
        g2.drawString("x " + gamePanel.player.hasKey, 75, 65);
        if (gamePanel.player.speed == 6) {
            g2.drawImage(boot.image, gamePanel.TILE_SIZE / 2, gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
        }

        if (messageOn) {
            g2.setFont(arial30Plain);
            g2.drawString(message, gamePanel.TILE_SIZE/2, gamePanel.TILE_SIZE * 10);
            messageCounter++;
            if(messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }

        if(keyHandler.debugMode) {
            g2.setColor(Color.RED);
            drawCenteredText(g2, "Debug Mode", gamePanel.TILE_SIZE * -5);
            drawText(g2, "FPS: " + gamePanel.drawnFrames, 400, 500);
        }
    }

    private void drawCenteredText(Graphics2D g2, String text, int yOffset) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gamePanel.SCREEN_WIDTH / 2 - textLength / 2;
        int y = gamePanel.SCREEN_HEIGHT / 2 + yOffset;
        g2.drawString(text, x, y);
    }

    private void drawText(Graphics2D g2, String text, int x, int y) {
        g2.drawString(text, x, y);
    }
}
