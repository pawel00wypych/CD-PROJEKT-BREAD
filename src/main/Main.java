package main;

import javax.swing.*;

public class Main {
    public static JFrame window;

    public static void main(String[] args) {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = GamePanel.getInstance();
        window.add(gamePanel);

        ConfigMemento memento = gamePanel.config.loadConfig();
        gamePanel.music.volumeScale = memento.musicVolume;
        gamePanel.soundEffect.volumeScale = memento.effectVolume;
        System.out.println(memento.fullScreenOn);
        if(memento.fullScreenOn){
            window.setUndecorated(true);
            gamePanel.fullScreenOn = true;
        }

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

        //GARBAGE COLLECTION REQUEST
        System.gc();
    }
}
