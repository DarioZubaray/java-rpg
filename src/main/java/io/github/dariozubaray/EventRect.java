package io.github.dariozubaray;

import java.awt.Rectangle;

public class EventRect extends Rectangle {
    int eventRectDefaultX, eventRectDefaultY;
    boolean eventDone;

    public EventRect() {
        this.x = 23;
        this.y = 23;
        this.width = 2;
        this.height = 2;
        this.eventRectDefaultX = this.x;
        this.eventRectDefaultY = this.y;
    }
}
