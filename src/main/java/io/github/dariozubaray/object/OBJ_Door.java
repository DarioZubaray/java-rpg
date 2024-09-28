package io.github.dariozubaray.object;

import io.github.dariozubaray.ImageLoader;

public class OBJ_Door extends SuperObject {

    public OBJ_Door() {
        this.name = ObjectLabel.DOOR;
        this.image = ImageLoader.loadSprite("/objects/door.png");
        collision = true;
    }
}
