package io.github.dariozubaray.tiles;

import java.util.Random;

public class RandomMapWorldGenerator {

    public static void main(String[] args) {
        int maxWorldX = 50;
        int maxWorldY = 50;
        Random rn = new Random();

        for (int i = 1; i <= maxWorldY; i++) {
            for (int j = 1; j <= maxWorldX; j++) {
                int randomTile = rn.nextInt(6);
                if(j != maxWorldY) {
                    System.out.print(randomTile + " ");
                } else {
                    System.out.println(randomTile);
                }


            }
        }
    }
}
