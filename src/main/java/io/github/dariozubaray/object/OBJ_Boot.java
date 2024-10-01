package io.github.dariozubaray.object;

import io.github.dariozubaray.ImageLoader;

public class OBJ_Boot extends SuperObject {

    public OBJ_Boot(int width, int height) {
        this.name = ObjectLabel.BOOT;
        this.image = ImageLoader.loadSprite("/objects/boots.png", width, height);
    }
}
