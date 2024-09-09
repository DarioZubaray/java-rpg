package io.github.dariozubaray;

import javax.swing.JFrame;

public class MainLauncher {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JavaRPG - 2D Adventure");

        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
