package io.github.dariozubaray.entities;

public enum EntityLabel {
    CHEST("Chest", 3),
    DOOR("Door", 2),
    KEY("Key", 0),
    BOOT("Boot", 1),
    HEART("Heart", -1),
    GREEN_SLIME("Green Slime", -1);

    private final String name;
    private final int audioIndex;

    EntityLabel(String name, int audioIndex) {
        this.name = name;
        this.audioIndex = audioIndex;
    }

    public String getName() {
        return name;
    }

    public int getAudioIndex() {
        return audioIndex;
    }
}
