package main;

public class ConfigMemento {
    public boolean fullScreenOn;
    public int musicVolume;
    public int effectVolume;

    public ConfigMemento(boolean fullScreenOn, int musicVolume, int effectVolume) {
        this.fullScreenOn = fullScreenOn;
        this.musicVolume = musicVolume;
        this.effectVolume = effectVolume;
    }
}
