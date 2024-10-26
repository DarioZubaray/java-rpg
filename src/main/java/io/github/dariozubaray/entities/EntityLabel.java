package io.github.dariozubaray.entities;

public enum EntityLabel {
    CHEST("Chest"),
    DOOR("Door"),
    KEY("Key"),
    BOOT("Boot"),
    HEART("Heart"),
    GREEN_SLIME("Green Slime");

    private final String name;

    EntityLabel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
