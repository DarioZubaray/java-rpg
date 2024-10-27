package io.github.dariozubaray;

import static io.github.dariozubaray.sound.Music.MAIN_MUSIC_INDEX;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;
    public boolean upPressed, downPressed, rightPressed, leftPressed;
    public boolean enterPressed;
    public boolean debugMode;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(gamePanel.gameState == GameState.TITLE) {
            titleState(code);
            return;
        }

        if(code == KeyEvent.VK_ESCAPE) {
            if(gamePanel.gameState == GameState.PLAY) gamePanel.gameState = GameState.PAUSE;
            else gamePanel.gameState = GameState.PLAY;
        }
        if(gamePanel.gameState == GameState.PAUSE) return;

        if(gamePanel.gameState == GameState.DIALOGUE) {
            if(code == KeyEvent.VK_ENTER) {
                gamePanel.gameState = GameState.PLAY;
            }
            return;
        }

        if(gamePanel.gameState == GameState.CHARACTER) {
            if(code == KeyEvent.VK_C) {
                gamePanel.gameState = GameState.PLAY;
            }
            return;
        }

        playState(code);
    }

    public void titleState(int code) {
        if(code == KeyEvent.VK_W) {
            gamePanel.ui.commandNumber--;
            if(gamePanel.ui.commandNumber < 0) gamePanel.ui.commandNumber = 2;
        }
        if(code == KeyEvent.VK_S) {
            gamePanel.ui.commandNumber++;
            if(gamePanel.ui.commandNumber > 2) gamePanel.ui.commandNumber = 0;
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gamePanel.ui.commandNumber == 0) {
                gamePanel.gameState = GameState.PLAY;
                gamePanel.playMusic(MAIN_MUSIC_INDEX);
            }
            if(gamePanel.ui.commandNumber == 1) {
                System.out.println("Not implemented yet ");
            }
            if(gamePanel.ui.commandNumber == 2) {
                System.exit(0);
            }
        }
    }

    public void playState(int code) {
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_C) {
            gamePanel.gameState = GameState.CHARACTER;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if(code == KeyEvent.VK_F3) {
            debugMode = !debugMode;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
    }
}
