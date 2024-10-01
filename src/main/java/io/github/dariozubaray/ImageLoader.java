package io.github.dariozubaray;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

public class ImageLoader {

    public static BufferedImage loadSprite(String spritePath, int width, int height) {
        BufferedImage scaledImage = null;
        try {
            var image = ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResourceAsStream(spritePath)));
            scaledImage = new BufferedImage(width, height, image.getType());
            Graphics2D g2 = scaledImage.createGraphics();
            g2.drawImage(image, 0, 0, width, height, null);
            g2.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scaledImage;
    }
}
