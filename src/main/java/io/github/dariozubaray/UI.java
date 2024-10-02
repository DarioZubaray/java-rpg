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
    Font arial40Plain;
    Font arial80b;
    public String message;
    public boolean gameFinished;
    double playTime;
    DecimalFormat decimalFormat;

    public UI(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.arial40Plain = new Font("Arial", Font.PLAIN, 40);
        this.arial80b = new Font("Arial", Font.BOLD, 80);
        decimalFormat = new DecimalFormat("#00.00");
    }

    public void showMessage(String text) {
        message = text;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial40Plain);
        g2.setColor(Color.WHITE);

        if(gamePanel.gameState == gamePanel.pausedGame) {
            String pausedText = "P A U S E D";
            drawCenteredText(g2, pausedText, 0);
            return;
        }

        if(keyHandler.debugMode) {
            drawText(g2, "Debug Mode", gamePanel.TILE_SIZE * 10 + 36, gamePanel.TILE_SIZE);

            playTime += (double) 1/60;
            g2.drawString("Time: " + decimalFormat.format(playTime), gamePanel.TILE_SIZE * 11, gamePanel.TILE_SIZE * 2);

            drawText(g2, "FPS: " + gamePanel.drawnFrames, gamePanel.TILE_SIZE * 12 + 18, gamePanel.TILE_SIZE * 10 + 20);
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
