package io.github.dariozubaray;

import io.github.dariozubaray.entities.Player;
import io.github.dariozubaray.object.SuperObject;
import io.github.dariozubaray.tiles.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    final int ORIGINAL_TILE_SIZE = 16;
    final int SCALE = 3;

    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public final int MAX_SCREEN_COL = 16;
    public final int MAX_SCREEN_ROW = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public final int MAX_WORLD_COL = 50;
    public final int MAX_WORLD_ROW = 50;

    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler;
    TileManager tileManager;
    public CollisionChecker collisionChecker;
    public SuperObject[] objects;
    public AssetSetter assetSetter;
    public Player player;

    public GamePanel () {
        System.out.print("GamePanel: screen dimensions: ");
        System.out.println(SCREEN_WIDTH + " x " + SCREEN_HEIGHT);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        this.tileManager = new TileManager(this);
        this.collisionChecker = new CollisionChecker(this);
        this.objects = new SuperObject[10];
        this.assetSetter = new AssetSetter(this);
        this.player = new Player(this, keyHandler);
    }

    public void setupGame() {
        assetSetter.setObject();
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        this.tileManager.draw(g2);

        for (int i = 0; i < objects.length; i++) {
            if (Objects.nonNull(objects[i])) {
                objects[i].draw(g2, this);
            }
        }
        
        this.player.draw(g2);
        g2.dispose();
    }
}
