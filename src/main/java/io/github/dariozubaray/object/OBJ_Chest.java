package io.github.dariozubaray.object;

import io.github.dariozubaray.ImageLoader;

public class OBJ_Chest extends SuperObject {

    public OBJ_Chest(int width, int height) {
        this.name = ObjectLabel.CHEST;
        this.image = ImageLoader.loadSprite("/objects/chest.png", width, height);
    }
}
