package io.github.dariozubaray;

import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

public class ImageLoader {

    public static BufferedImage loadSprite(String spritePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResourceAsStream(spritePath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
