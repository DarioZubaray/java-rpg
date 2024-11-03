package io.github.dariozubaray;

import io.github.dariozubaray.entities.Entity;
import io.github.dariozubaray.entities.Player;
import io.github.dariozubaray.entities.Projectile;
import io.github.dariozubaray.sound.Music;
import io.github.dariozubaray.sound.Sound;
import io.github.dariozubaray.tiles.TileManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    public final int ORIGINAL_TILE_SIZE = 16;
    public final int SCALE = 3;

    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public final int MAX_SCREEN_COL = 16;
    public final int MAX_SCREEN_ROW = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public final int MAX_WORLD_COL = 50;
    public final int MAX_WORLD_ROW = 50;
    public final int WORLD_WIDTH = TILE_SIZE * MAX_WORLD_COL;
    public final int WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;

    public final int FPS = 60;
    public int drawnFrames;

    public GameState gameState;

    Thread gameThread;
    public KeyHandler keyHandler;
    public EventHandler eventHandler;
    TileManager tileManager;
    Music music;
    Sound sound;
    public UI ui;
    public CollisionChecker collisionChecker;
    public List<Entity> entityList;
    public List<Projectile> projectileList;
    public Entity[] objectsArray;
    public Entity[] npcsArray;
    public Entity[] monstersArray;
    public AssetSetter assetSetter;
    public Player player;

    public GamePanel () {
        System.out.print("GamePanel: screen dimensions: ");
        System.out.println(SCREEN_WIDTH + " x " + SCREEN_HEIGHT);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.keyHandler = new KeyHandler(this);
        this.addKeyListener(keyHandler);
        this.eventHandler =  new EventHandler(this);
        this.setFocusable(true);

        this.tileManager = new TileManager(this);
        this.music = new Music();
        this.sound = new Sound();
        this.ui = new UI(this, keyHandler);
        this.collisionChecker = new CollisionChecker(this);

        this.entityList = new ArrayList<>();
        this.projectileList = new ArrayList<>();

        this.objectsArray = new Entity[10];
        this.npcsArray = new Entity[10];
        this.monstersArray = new Entity[20];

        this.assetSetter = new AssetSetter(this);
        this.player = new Player(this, keyHandler);
//        this.gameState = GameState.TITLE;
        this.gameState = GameState.PLAY;
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNpc();
        assetSetter.setMonster();
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000.0 /FPS;
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
                drawnFrames = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        if(gameState == GameState.PLAY) {
            player.update();

            Arrays.stream(npcsArray).filter(Objects::nonNull).forEach(Entity::update);
            for (int i = 0; i < monstersArray.length; i++) {
                if (monstersArray[i] != null) {
                    if (monstersArray[i].alive && !monstersArray[i].dying) monstersArray[i].update();
                    if (!monstersArray[i].alive) monstersArray[i] = null;
                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive) projectileList.get(i).update();
                    if (!projectileList.get(i).alive) projectileList.remove(i);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(gameState == GameState.TITLE) {
            this.ui.draw(g2);
            return;
        }

        long drawStart = 0;
        if(keyHandler.debugMode) {
            drawStart = System.nanoTime();
        }

        this.tileManager.draw(g2);

        entityList.add(player);
        Arrays.stream(npcsArray).filter(Objects::nonNull).forEach(entityList::add);
        Arrays.stream(objectsArray).filter(Objects::nonNull).forEach(entityList::add);
        Arrays.stream(monstersArray).filter(Objects::nonNull).forEach(entityList::add);
        projectileList.stream().filter(Objects::nonNull).forEach(entityList::add);

        entityList.sort(Comparator.comparingInt(e -> e.worldY));
        entityList.forEach(e -> e.draw(g2));
        entityList.clear();

        this.ui.draw(g2);
        if(keyHandler.debugMode && gameState == GameState.PLAY) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + player.worldX, x, y);
            y += lineHeight;
            g2.drawString("WorldY: " + player.worldY, x, y);
            y += lineHeight;
            int col = (player.worldX + player.solidArea.x) / TILE_SIZE;
            g2.drawString("Col: " + col, x, y);
            y += lineHeight;
            int row = (player.worldY + player.solidArea.y) / TILE_SIZE;
            g2.drawString("Row: " + row, x, y);
            y += lineHeight;
            g2.drawString("DrawTime: " + passed, x, y);
        }
        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }

}
