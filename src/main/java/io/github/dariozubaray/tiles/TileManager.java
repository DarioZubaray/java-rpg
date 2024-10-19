package io.github.dariozubaray.tiles;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = new Tile[50];
        this.mapTileNum = new int[gamePanel.MAX_WORLD_COL][gamePanel.MAX_WORLD_ROW];
        getTilesImage();
        loadMap();
    }

    public void getTilesImage() {
        setupTiles(0, "placeholder", false);
        setupTiles(1, "placeholder", false);
        setupTiles(2, "placeholder", false);
        setupTiles(3, "placeholder", false);
        setupTiles(4, "placeholder", false);
        setupTiles(5, "placeholder", false);
        setupTiles(6, "placeholder", false);
        setupTiles(7, "placeholder", false);
        setupTiles(8, "placeholder", false);
        setupTiles(9, "placeholder", false);

        setupTiles(10, "grass00", false);
        setupTiles(11, "grass01", false);
        setupTiles(12, "water00", true);
        setupTiles(13, "water01", true);
        setupTiles(14, "water02", true);
        setupTiles(15, "water03", true);
        setupTiles(16, "water04", true);
        setupTiles(17, "water05", true);
        setupTiles(18, "water06", true);
        setupTiles(19, "water07", true);

        setupTiles(20, "water08", true);
        setupTiles(21, "water09", true);
        setupTiles(22, "water10", true);
        setupTiles(23, "water11", true);
        setupTiles(24, "water12", true);
        setupTiles(25, "water13", true);
        setupTiles(26, "road00", false);
        setupTiles(27, "road01", false);
        setupTiles(28, "road02", false);
        setupTiles(29, "road03", false);

        setupTiles(30, "road04", false);
        setupTiles(31, "road05", false);
        setupTiles(32, "road06", false);
        setupTiles(33, "road07", false);
        setupTiles(34, "road08", false);
        setupTiles(35, "road09", false);
        setupTiles(36, "road10", false);
        setupTiles(37, "road11", false);
        setupTiles(38, "road12", false);
        setupTiles(39, "earth", false);

        setupTiles(40, "wall", true);
        setupTiles(41, "tree", true);
        setupTiles(42, "hole", false);
    }

    public void setupTiles(int index, String name, boolean collisionable){
        this.tiles[index] = new Tile();
        this.tiles[index].image = ImageLoader.loadSprite("/tiles/"+name+".png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.tiles[index].collision = collisionable;
    }

    public void loadMap() {
        String line;
        int col = 0;
        int row = 0;

        try (InputStream is = getClass().getResourceAsStream("/maps/world01-v2.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)))) {

            while (col < gamePanel.MAX_WORLD_COL && row < gamePanel.MAX_WORLD_ROW) {
                line = br.readLine();

                while (col < gamePanel.MAX_WORLD_COL) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gamePanel.MAX_WORLD_COL) {
                    col = 0;
                    row++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gamePanel.MAX_WORLD_COL && worldRow < gamePanel.MAX_WORLD_ROW) {
            int tileNum = mapTileNum[worldCol][worldRow];
            BufferedImage image = tiles[tileNum].image;

            int worldX = worldCol * gamePanel.TILE_SIZE;
            int worldY = worldRow * gamePanel.TILE_SIZE;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.SCREEN_X;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.SCREEN_Y;

            //Stop moving camera at the edge
            if(gamePanel.player.SCREEN_X > gamePanel.player.worldX) screenX = worldX;
            if(gamePanel.player.SCREEN_Y > gamePanel.player.worldY) screenY = worldY;

            int rightOffset = gamePanel.SCREEN_WIDTH - gamePanel.player.SCREEN_X;
            if(rightOffset > gamePanel.WORLD_WIDTH - gamePanel.player.worldX) screenX = gamePanel.SCREEN_WIDTH - (gamePanel.WORLD_WIDTH - worldX);
            int bottomOffset = gamePanel.SCREEN_HEIGHT - gamePanel.player.SCREEN_Y;
            if(bottomOffset > gamePanel.WORLD_HEIGHT - gamePanel.player.worldY) screenY = gamePanel.SCREEN_HEIGHT - (gamePanel.WORLD_HEIGHT - worldY);

            boolean isWithinXBounds = (worldX + gamePanel.TILE_SIZE > gamePanel.player.worldX - gamePanel.player.SCREEN_X) &&
                    (worldX - gamePanel.TILE_SIZE < gamePanel.player.worldX + gamePanel.player.SCREEN_X);
            boolean isWithinYBounds = (worldY + gamePanel.TILE_SIZE > gamePanel.player.worldY - gamePanel.player.SCREEN_Y) &&
                    (worldY - gamePanel.TILE_SIZE < gamePanel.player.worldY + gamePanel.player.SCREEN_Y);

            if (isWithinXBounds && isWithinYBounds) {
                g2.drawImage(image, screenX, screenY, null);
            } else if(gamePanel.player.SCREEN_X > gamePanel.player.worldX ||
                    gamePanel.player.SCREEN_Y > gamePanel.player.worldY ||
                    rightOffset > gamePanel.WORLD_WIDTH - gamePanel.player.worldX ||
                    bottomOffset > gamePanel.WORLD_HEIGHT - gamePanel.player.worldY) {
                g2.drawImage(image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol  == gamePanel.MAX_WORLD_COL) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
