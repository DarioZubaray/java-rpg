package io.github.dariozubaray.tiles;

import io.github.dariozubaray.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;

    int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];

        getTilesImage();
        loadMap();
    }

    public void getTilesImage() {
        try {
            this.tiles[0] = new Tile();
            this.tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            this.tiles[1] = new Tile();
            this.tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

            this.tiles[2] = new Tile();
            this.tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            this.tiles[3] = new Tile();
            this.tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            this.tiles[4] = new Tile();
            this.tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            this.tiles[5] = new Tile();
            this.tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        String line = null;
        int col = 0;
        int row = 0;
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
                line = br.readLine();

                while (col < gamePanel.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gamePanel.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tiles[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;

            if (col  == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }
}
