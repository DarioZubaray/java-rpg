package io.github.dariozubaray;

import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.object.OBJ_Heart;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UI {

    Graphics2D g2;
    GamePanel gamePanel;
    KeyHandler keyHandler;
    Font purisaBold;
    Font maruMonica;
    BufferedImage heartFull, heartHalf, heartBlank;
    int titleImageCounter;
    public int commandNumber = 0;

    List<String> messages;
    List<Integer> messagesCounter;

    public boolean gameFinished;
    double playTime;
    DecimalFormat decimalFormat;
    public String currentDialogue;

    public int slotCol, slotRow;

    public UI(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.maruMonica = FontLoader.loadFont(FontLoader.MARU_MONICA);
        this.purisaBold = FontLoader.loadFont(FontLoader.PURISA_BOLD);
        decimalFormat = new DecimalFormat("#00.00");

        Entity heart = new OBJ_Heart(gamePanel);
        heartFull = heart.image1;
        heartHalf = heart.image2;
        heartBlank = heart.image3;

        this.messages = new ArrayList<>();
        this.messagesCounter = new ArrayList<>();
        slotCol = 0;
        slotRow = 0;
    }

    public void addMessage(String text) {
        messages.add(text);
        messagesCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setColor(Color.WHITE);

        if(gamePanel.gameState == GameState.TITLE) {
            drawPlayerLife();
            drawTitleScreen();
            return;
        }

        if(gamePanel.gameState == GameState.PAUSE) {
            g2.setFont(purisaBold);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
            drawPlayerLife();
            drawPauseScreen();
            return;
        }

        if(gamePanel.gameState == GameState.DIALOGUE) {
            drawDialogueScreen();
            return;
        }

        if(gamePanel.gameState == GameState.CHARACTER) {
            drawCharacterScreen();
            drawInventory();
            return;
        }

        drawPlayerLife();
        drawMessages();

        if(keyHandler.debugMode) {
            g2.setFont(purisaBold);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25F));
            drawText("Debug Mode", gamePanel.TILE_SIZE * 10 + 36, gamePanel.TILE_SIZE);

            playTime += (double) 1/60;
            g2.drawString("Time: " + decimalFormat.format(playTime), gamePanel.TILE_SIZE * 11, gamePanel.TILE_SIZE * 2);

            drawText("FPS: " + gamePanel.drawnFrames, gamePanel.TILE_SIZE * 12 + 18, gamePanel.TILE_SIZE * 10 + 20);
        }
    }

    private void drawMessages() {
        int messageX = gamePanel.TILE_SIZE;
        int messageY = gamePanel.TILE_SIZE * 4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32f));

        int start = Math.max(0, messages.size() - 5);

        for (int i = start; i < messages.size(); i++) {
            String message = messages.get(i);
            if (message != null) {
                g2.setColor(Color.BLACK);
                g2.drawString(message, messageX+2, messageY+2);
                g2.setColor(Color.WHITE);
                g2.drawString(message, messageX, messageY);
                messageY += 50;
            }
        }

        for (int i = messagesCounter.size() - 1; i >= 0; i--) {
            messagesCounter.set(i, messagesCounter.get(i) + 1);
            if (messagesCounter.get(i) > 180) {
                messages.remove(i);
                messagesCounter.remove(i);
            }
        }
    }


    private void drawPlayerLife() {
        int fullLife = 0, halfLife = 0;
        if(gamePanel.player.life >= 0) {
            fullLife = gamePanel.player.life/2;
            halfLife = gamePanel.player.life % 2;
        }
        int remainingLife = (gamePanel.player.maxLife/2) - (fullLife + halfLife);

        int x = gamePanel.TILE_SIZE/2;
        int y = gamePanel.TILE_SIZE/2;

        for (int i = 0; i < fullLife; i++) {
            g2.drawImage(this.heartFull, x, y, null);
            x += gamePanel.TILE_SIZE;
        }

        if (halfLife > 0) {
            g2.drawImage(this.heartHalf, x, y, null);
            x += gamePanel.TILE_SIZE;
        }

        for (int i = 0; i < remainingLife; i++) {
            g2.drawImage(this.heartBlank, x, y, null);
            x += gamePanel.TILE_SIZE;
        }
    }

    private int getXForCenteredText(String text) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanel.SCREEN_WIDTH / 2 - textLength / 2;
    }

    private int getXForAlignToRightText(String text, int tailX) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - textLength;
    }

    private void drawText(String text, int x, int y) {
        g2.drawString(text, x, y);
    }

    private void drawTitleScreen() {
        setBackgroundTitleScreen();
        setTitleTitleScreen();
        setImageTitleScreen();
        setMenuTitleScreen();
    }

    private void setBackgroundTitleScreen() {
        g2.setColor(new Color(70,120,80));
        g2.fillRect(0, 0, gamePanel.WORLD_WIDTH, gamePanel.WORLD_HEIGHT);
    }

    private void setTitleTitleScreen() {
        String[] title = MainLauncher.GAME_TITLE.split(" - ");
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 96F));
        drawTitleName(title, true);
    }

    private void drawTitleName(String[] titleParts, boolean shadows) {
        int y = 0;
        for(String title : titleParts) {
            int x = getXForCenteredText(title);
            y += gamePanel.TILE_SIZE * 2;
            if(shadows) {
                g2.setColor(Color.BLACK);
                g2.drawString(title, x + 5, y + 5);
            }
            g2.setColor(Color.WHITE);
            g2.drawString(title, x, y);
        }
    }

    private void setImageTitleScreen() {
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
    }

    private void setMenuTitleScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        String text = "New Game";
        int x = getXForCenteredText(text);
        int y = gamePanel.TILE_SIZE * 9;
        g2.drawString(text, x, y);
        if(commandNumber == 0) g2.drawString(">", x-gamePanel.TILE_SIZE, y);

        text = "Load Game";
        x = getXForCenteredText(text);
        y += gamePanel.TILE_SIZE;
        g2.drawString(text, x, y);
        if(commandNumber == 1) g2.drawString(">", x-gamePanel.TILE_SIZE, y);

        text = "Quit";
        x = getXForCenteredText(text);
        y += gamePanel.TILE_SIZE;
        g2.drawString(text, x, y);
        if(commandNumber == 2) g2.drawString(">", x-gamePanel.TILE_SIZE, y);
    }

    private void drawPauseScreen() {
        String pauseText = "P A U S E D";
        drawCenteredText(pauseText, 0);
    }

    private void drawCenteredText(String text, int yOffset) {
        int x = getXForCenteredText(text);
        int y = gamePanel.SCREEN_HEIGHT / 2 + yOffset;
        g2.drawString(text, x, y);
    }

    private void drawDialogueScreen() {
        int x = gamePanel.TILE_SIZE * 2;
        int y = gamePanel.TILE_SIZE / 2;
        int width = gamePanel.SCREEN_WIDTH - (gamePanel.TILE_SIZE * 4);
        int height = gamePanel.TILE_SIZE * 4;
        drawSubWindow(x, y, width, height);

        x += gamePanel.TILE_SIZE;
        y += gamePanel.TILE_SIZE;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    private void drawCharacterScreen() {
        int frameX = gamePanel.TILE_SIZE;
        int frameY = gamePanel.TILE_SIZE;
        int frameWidth = gamePanel.TILE_SIZE * 5;
        int frameHeight = gamePanel.TILE_SIZE * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));
        int textX = frameX + 20;
        int textY = frameY + gamePanel.TILE_SIZE;
        final int lineHeight = 35;
        int tailX = (frameX + frameWidth) - 30;

        String[] labels = {"Level", "Life", "Strength", "Dexterity", "Attack", "Defense", "Exp", "Next Level", "Coins"};
        String[] values = {
                String.valueOf(gamePanel.player.level),
                gamePanel.player.life + "/" + gamePanel.player.maxLife,
                String.valueOf(gamePanel.player.strength),
                String.valueOf(gamePanel.player.dexterity),
                String.valueOf(gamePanel.player.attack),
                String.valueOf(gamePanel.player.defense),
                String.valueOf(gamePanel.player.exp),
                String.valueOf(gamePanel.player.nextLevelExp),
                String.valueOf(gamePanel.player.coins)
        };

        for (int i = 0; i < labels.length; i++) {
            g2.drawString(labels[i], textX, textY);
            int alignedTextX = getXForAlignToRightText(values[i], tailX);
            g2.drawString(values[i], alignedTextX, textY);
            textY += lineHeight;
        }

        g2.drawString("Weapon", textX, textY);
        g2.drawImage(gamePanel.player.currentWeapon.image1, tailX - gamePanel.TILE_SIZE, textY - 20, null);
        textY += gamePanel.TILE_SIZE;
        g2.drawString("Shield", textX, textY);
        g2.drawImage(gamePanel.player.currentShield.image1, tailX - gamePanel.TILE_SIZE, textY - 20, null);
    }

    private void drawInventory() {
        int frameX = gamePanel.TILE_SIZE * 9;
        int frameY = gamePanel.TILE_SIZE;
        int frameWidth = gamePanel.TILE_SIZE * 6;
        int frameHeight = gamePanel.TILE_SIZE * 5;
        drawInventoryFrame(frameX, frameY, frameWidth, frameHeight);

        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        int slotSize = gamePanel.TILE_SIZE + 3;

        drawPlayerItems(slotX, slotY, slotSize, slotXStart);

        drawInventoryCursor(slotXStart, slotYStart, slotSize);
        drawDescriptionText(frameX, frameY, frameWidth, frameHeight);
    }

    private void drawInventoryFrame(int frameX, int frameY, int frameWidth, int frameHeight) {
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    }

    private void drawPlayerItems(int slotX, int slotY, final int slotSize, final int slotXStart) {
        for (int i = 0; i < gamePanel.player.inventory.size(); i++) {
            var currentItem = gamePanel.player.inventory.get(i);
            if(gamePanel.player.currentWeapon.equals(currentItem) || gamePanel.player.currentShield.equals(currentItem)) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, 10, 10);
            }

            g2.drawImage(gamePanel.player.inventory.get(i).image1, slotX, slotY, null);
            slotX += slotSize;

            if(i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += slotSize;
            }
        }
    }

    private void drawInventoryCursor(final int slotXStart, final int slotYStart, final int slotSize) {
        int cursorX = slotXStart + slotSize * slotCol;
        int cursorY = slotYStart + slotSize * slotRow;
        int cursorWidth = gamePanel.TILE_SIZE, cursorHeight = gamePanel.TILE_SIZE;
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    }

    private void drawDescriptionFrame(int frameX, int frameY, int frameWidth, int frameHeight) {
        int dFrameY = frameY + frameHeight;
        int dFrameHeight = gamePanel.TILE_SIZE * 3;
        drawSubWindow(frameX, dFrameY, frameWidth, dFrameHeight);
    }

    private void drawDescriptionText(int frameX, int frameY, int frameWidth, int frameHeight) {
        int textX = frameX + 20;
        int textY = frameY + frameHeight + gamePanel.TILE_SIZE;
        g2.setFont(g2.getFont().deriveFont(28f));

        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gamePanel.player.inventory.size()) {
            drawDescriptionFrame(frameX, frameY, frameWidth, frameHeight);
            String description = gamePanel.player.inventory.get(itemIndex).description;
            for(String line : description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    public int getItemIndexOnSlot() {
        return slotCol + (slotRow * 5);
    }

    private void drawSubWindow(int x, int y, int width, int height) {
        Color background = new Color(0, 0, 0,180);
        g2.setColor(background);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        Color whiteBorder = new Color(255, 255, 255);
        g2.setColor(whiteBorder);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
}
