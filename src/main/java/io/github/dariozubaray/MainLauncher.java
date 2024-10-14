package io.github.dariozubaray;

import javax.swing.JFrame;

public class MainLauncher {

    public static final String GAME_TITLE = "JavaRPG - 2D Adventure";

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        System.out.println(GAME_TITLE);
        window.setTitle(GAME_TITLE);

        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamepanel.setupGame();
        gamepanel.startGameThread();
    }
}
