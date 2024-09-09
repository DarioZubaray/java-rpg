package io.github.dariozubaray;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;

public class GamePanel extends JPanel {

    //Screen Settings
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public GamePanel () {
        System.out.println(screenWidth + " - " + screenHeight);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
    }
}
