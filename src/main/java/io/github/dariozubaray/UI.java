package io.github.dariozubaray;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    Font purisaBold;
    Font maruMonica;
    public String message;
    public boolean gameFinished;
    double playTime;
    DecimalFormat decimalFormat;
    public String currentDialogue;

    public UI(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.maruMonica = FontLoader.loadFont(FontLoader.MARU_MONICA);
        this.purisaBold = FontLoader.loadFont(FontLoader.PURISA_BOLD);
        decimalFormat = new DecimalFormat("#00.00");
    }

    public void showMessage(String text) {
        message = text;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(maruMonica);
        g2.setColor(Color.WHITE);

        if(gamePanel.gameState == gamePanel.pauseState) {
            g2.setFont(purisaBold);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
            drawPauseScreen(g2);
            return;
        }

        if(gamePanel.gameState == gamePanel.dialogueState) {
            drawDialogueScreen(g2);
            return;
        }

        if(keyHandler.debugMode) {
            g2.setFont(purisaBold);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
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

    private void drawPauseScreen(Graphics2D g2) {
        String pauseText = "P A U S E D";
        drawCenteredText(g2, pauseText, 0);
    }

    private void drawDialogueScreen(Graphics2D g2) {
        int x = gamePanel.TILE_SIZE * 2;
        int y = gamePanel.TILE_SIZE / 2;
        int width = gamePanel.SCREEN_WIDTH - (gamePanel.TILE_SIZE * 4);
        int height = gamePanel.TILE_SIZE * 4;
        drawSubWindow(g2, x, y, width, height);

        x += gamePanel.TILE_SIZE;
        y += gamePanel.TILE_SIZE;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    private void drawSubWindow(Graphics2D g2, int x, int y, int width, int height) {
        Color background = new Color(0, 0, 0,180);
        g2.setColor(background);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        Color whiteBorder = new Color(255, 255, 255);
        g2.setColor(whiteBorder);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
}
