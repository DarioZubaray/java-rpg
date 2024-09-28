package io.github.dariozubaray.object;

import io.github.dariozubaray.ImageLoader;

public class OBJ_Chest extends SuperObject {

    public OBJ_Chest() {
        this.name = ObjectLabel.CHEST;
        this.image = ImageLoader.loadSprite("/objects/chest.png");
    }
}
