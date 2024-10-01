package io.github.dariozubaray.tiles;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.ImageLoader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.MAX_WORLD_COL][gamePanel.MAX_WORLD_ROW];
        getTilesImage();
        loadMap();
    }

    public void getTilesImage() {
        this.tiles[0] = new Tile();
        this.tiles[0].image = ImageLoader.loadSprite("/tiles/grass.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);

        this.tiles[1] = new Tile();
        this.tiles[1].image = ImageLoader.loadSprite("/tiles/wall.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.tiles[1].collision = true;

        this.tiles[2] = new Tile();
        this.tiles[2].image = ImageLoader.loadSprite("/tiles/water.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.tiles[2].collision = true;

        this.tiles[3] = new Tile();
        this.tiles[3].image = ImageLoader.loadSprite("/tiles/earth.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);

        this.tiles[4] = new Tile();
        this.tiles[4].image = ImageLoader.loadSprite("/tiles/tree.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        this.tiles[4].collision = true;

        this.tiles[5] = new Tile();
        this.tiles[5].image = ImageLoader.loadSprite("/tiles/sand.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }

    public void loadMap() {
        String line;
        int col = 0;
        int row = 0;

        try (InputStream is = getClass().getResourceAsStream("/maps/world01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

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

            boolean isWithinXBounds = (worldX + gamePanel.TILE_SIZE > gamePanel.player.worldX - gamePanel.player.SCREEN_X) &&
                    (worldX - gamePanel.TILE_SIZE < gamePanel.player.worldX + gamePanel.player.SCREEN_X);
            boolean isWithinYBounds = (worldY + gamePanel.TILE_SIZE > gamePanel.player.worldY - gamePanel.player.SCREEN_Y) &&
                    (worldY - gamePanel.TILE_SIZE < gamePanel.player.worldY + gamePanel.player.SCREEN_Y);

            if (isWithinXBounds && isWithinYBounds) {
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
