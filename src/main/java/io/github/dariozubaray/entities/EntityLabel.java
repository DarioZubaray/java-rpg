package io.github.dariozubaray.entities;

public enum EntityLabel {
    CHEST("Chest"),
    DOOR("Door"),
    KEY("Key"),
    BOOT("Boot"),
    HEART("Heart"),
    GREEN_SLIME("Green Slime"),
    SWORD_NORMAL("Normal Sword"),
    SHIELD_WOOD("Wood Shield");

    private final String name;

    EntityLabel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
