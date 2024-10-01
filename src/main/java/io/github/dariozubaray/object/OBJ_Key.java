package io.github.dariozubaray.object;

import io.github.dariozubaray.ImageLoader;

public class OBJ_Key extends SuperObject {

    public OBJ_Key(int width, int height) {
        this.name = ObjectLabel.KEY;
        this.image = ImageLoader.loadSprite("/objects/key.png", width, height);
    }
}

