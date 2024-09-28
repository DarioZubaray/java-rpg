package io.github.dariozubaray.object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Boot extends SuperObject {

    public OBJ_Boot() {
        this.name = ObjectLabel.BOOT;

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
