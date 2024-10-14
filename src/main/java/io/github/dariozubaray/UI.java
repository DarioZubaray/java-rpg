package io.github.dariozubaray;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    Font purisaBold;
    Font maruMonica;
    int titleImageCounter;
    public int commandNumber = 0;
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

        if(gamePanel.gameState == GameState.TITLE) {
            drawTitleScreen(g2);
            return;
        }

        if(gamePanel.gameState == GameState.PAUSE) {
            g2.setFont(purisaBold);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
            drawPauseScreen(g2);
            return;
        }

        if(gamePanel.gameState == GameState.DIALOGUE) {
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

    private int getXForCenteredText(Graphics2D g2, String text) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanel.SCREEN_WIDTH / 2 - textLength / 2;
    }

    private void drawText(Graphics2D g2, String text, int x, int y) {
        g2.drawString(text, x, y);
    }

    private void drawTitleScreen(Graphics2D g2) {
        // Background
        g2.setColor(new Color(70,120,80));
        g2.fillRect(0, 0, gamePanel.WORLD_WIDTH, gamePanel.WORLD_HEIGHT);

        // Title
        String[] title = MainLauncher.GAME_TITLE.split(" - ");
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 96F));
        drawTitleName(g2, title, true);

        // Image
        int x = gamePanel.SCREEN_WIDTH / 2 - (gamePanel.TILE_SIZE*2)/2;
        int y = gamePanel.TILE_SIZE * 5;

        BufferedImage image;
        if(titleImageCounter < 40) {
            image = gamePanel.player.down1;
            titleImageCounter++;
        } else if(titleImageCounter < 80) {
            image = gamePanel.player.down2;
            titleImageCounter++;
        } else {
            image = gamePanel.player.down1;
            titleImageCounter = 0;
        }
        g2.drawImage(image, x, y, gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE * 2, null);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        String text = "New Game";
        x = getXForCenteredText(g2, text);
        y += gamePanel.TILE_SIZE*4;
        g2.drawString(text, x, y);
        if(commandNumber == 0) g2.drawString(">", x-gamePanel.TILE_SIZE, y);

        text = "Load Game";
        x = getXForCenteredText(g2, text);
        y += gamePanel.TILE_SIZE;
        g2.drawString(text, x, y);
        if(commandNumber == 1) g2.drawString(">", x-gamePanel.TILE_SIZE, y);

        text = "Quit";
        x = getXForCenteredText(g2, text);
        y += gamePanel.TILE_SIZE;
        g2.drawString(text, x, y);
        if(commandNumber == 2) g2.drawString(">", x-gamePanel.TILE_SIZE, y);
    }

    private void drawTitleName(Graphics2D g2, String[] titleParts, boolean shadows) {
        int y = 0;
        for(String title : titleParts) {
            int x = getXForCenteredText(g2, title);
            y += gamePanel.TILE_SIZE * 2;
            if(shadows) {
                g2.setColor(Color.BLACK);
                g2.drawString(title, x + 5, y + 5);
            }
            g2.setColor(Color.WHITE);
            g2.drawString(title, x, y);
        }
    }

    private void drawPauseScreen(Graphics2D g2) {
        String pauseText = "P A U S E D";
        drawCenteredText(g2, pauseText, 0);
    }

    private void drawCenteredText(Graphics2D g2, String text, int yOffset) {
        int x = getXForCenteredText(g2, text);
        int y = gamePanel.SCREEN_HEIGHT / 2 + yOffset;
        g2.drawString(text, x, y);
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
