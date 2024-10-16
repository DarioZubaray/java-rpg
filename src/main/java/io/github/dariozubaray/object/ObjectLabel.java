package io.github.dariozubaray.object;

public enum ObjectLabel {
    CHEST("Chest", 3),
    DOOR("Door", 2),
    KEY("Key", 0),
    BOOT("Boot", 1),
    HEART("Heart", -1);

    private final String name;
    private final int audioIndex;

    ObjectLabel(String name, int audioIndex) {
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
