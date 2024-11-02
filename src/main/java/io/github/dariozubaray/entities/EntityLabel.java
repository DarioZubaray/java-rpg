package io.github.dariozubaray.entities;

public enum EntityLabel {
    CHEST("Chest", "[ Chest ]", "chest"),
    DOOR("Door", "[ Door ]", "door"),
    KEY("Key", "[ Key ]\nAn small key,\nit opens a door.", "key"),
    BOOT("Boot", "[ Boots ]\nPerfect to run faster.", "boots"),
    HEART("Heart", "", ""),
    POTION_RED("Red Potion", "[ Red Potion ]\nHeals your life by ", "potion_red"),

    GREEN_SLIME("Green Slime", "", ""),

    SWORD_NORMAL("Normal Sword", "[ Normal Sword ]\nAn old sword.", "sword_normal"),
    SHIELD_WOOD("Wood Shield", "[ Shield Wood ]\nMade by wood.", "shield_wood"),
    AXE("Woodcutter's Axe", "[ Woodcutter's Axe ]\nA bit rusty but still\ncan cut some trees.", "axe"),
    SHIELD_BLUE("Blue Shield", "[ Blue Shield ]\nA Shiny blue shield", "shield_blue");

    private final String name;
    private final String description;
    private final String path;

    EntityLabel(String name, String description, String path) {
        this.name = name;
        this.description = description;
        this.path = "/objects/" + path + ".png";
    }

    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
    public String getPath() { return path; }

}
