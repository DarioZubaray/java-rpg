package io.github.dariozubaray.object;

import io.github.dariozubaray.ImageLoader;

public class OBJ_Heart extends SuperObject {

    public OBJ_Heart(int width, int height) {
        this.name = ObjectLabel.KEY;
        this.image = ImageLoader.loadSprite("/objects/heart_full.png", width, height);
        this.image2 = ImageLoader.loadSprite("/objects/heart_half.png", width, height);
        this.image3 = ImageLoader.loadSprite("/objects/heart_blank.png", width, height);
    }
}
