package io.github.dariozubaray.object;

import io.github.dariozubaray.ImageLoader;

public class OBJ_Key extends SuperObject {

    public OBJ_Key() {
        this.name = ObjectLabel.KEY;
        this.image = ImageLoader.loadSprite("/objects/key.png");
    }
}

