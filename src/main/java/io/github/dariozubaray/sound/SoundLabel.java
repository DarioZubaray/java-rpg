package io.github.dariozubaray.sound;

public enum SoundLabel {
    COIN("coin", "/sound/coin.wav", 0),
    POWER_UP("powerup", "/sound/powerup.wav", 1),
    UNLOCK("unlock", "/sound/unlock.wav", 2),
    FANFARE("fanfare", "/sound/fanfare.wav", 3),
    HIT_MONSTER("hitmonster", "/sound/hitmonster.wav", 4),
    RECEIVE_DAMAGE("receivedamage", "/sound/receivedamage.wav", 5),
    SWING_WEAPON("swingweapon", "/sound/swingweapon.wav", 6),
    LEVEL_UP("levelup", "/sound/levelup.wav", 7),
    CURSOR("cursor", "/sound/cursor.wav", 8),
    BURNING("burning", "/sound/burning.wav", 9),
    CUT_TREE("cuttree", "/sound/cuttree.wav", 10);

    private final String name;
    private final String path;
    private final int audioIndex;

    SoundLabel(String name, String path, int audioIndex) {
        this.name = name;
        this.path = path;
        this.audioIndex = audioIndex;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getAudioIndex() {
        return audioIndex;
    }

}
