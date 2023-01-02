package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;

    public boolean showDebugText = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState) {
            titleState(code);
        }
        // PLAY STATE
        else if(gp.gameState == gp.playState) {
            playState(code);
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        //CHARACTER STATE
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }
        //LEVEL UP
        else if(gp.gameState == gp.levelUpState){
            levelUpState(code);
        }
        //OPTIONS
        else if(gp.gameState == gp.optionsState){
            optionsState(code);
        }
        //GAME OVER
        else if(gp.gameState == gp.gameOverState){
            gameOverState(code);
        }
        //GAME Finished
        else if(gp.gameState == gp.gameEndState){
            gameEndState(code);
        }
    }

    public void gameEndState(int code){
        gp.ui.commandNum = 0;
        if(code == KeyEvent.VK_W){
            gp.playSE(11);
        }
        if(code == KeyEvent.VK_S){
            gp.playSE(11);
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }
    public void gameOverState(int code){
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum<0){
                gp.ui.commandNum = 1;
            }
            gp.playSE(11);
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum>1){
                gp.ui.commandNum = 0;
            }
            gp.playSE(11);
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.retry();
            }else if(gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }
    public void titleState(int code){
        if(gp.ui.titleScreenState == 0) {

            if (code == KeyEvent.VK_W) {
                gp.playSE(11);
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.playSE(11);
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {

                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;
                }
                if (gp.ui.commandNum == 1) {

                    // todo load game
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }
        else if(gp.ui.titleScreenState == 1) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {

                if (gp.ui.commandNum == 0) {

                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(6);
                }
                if (gp.ui.commandNum == 1) {
                    System.out.println("RETURN");
                    gp.ui.titleScreenState = 0;
                }
            }

        }
    }
    public void  playState(int code){

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        //P - show stats
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.characterState;
        }
        // F - shot projectile
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        // ESC - options
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {

            gp.gameState = gp.pauseState;
        }

        // DEBUG
        if (code == KeyEvent.VK_T) {
            if (!showDebugText) {
                showDebugText = true;
            } else {
                showDebugText = false;
            }
        }
        // REFRESH MAP // to save edited map: CTRL + F9 in IntelliJ
        if (code == KeyEvent.VK_R) {
            switch (gp.currentMap){
                case 0: gp.tileM.loadMap("/maps/map01.txt",0);
                case 1: gp.tileM.loadMap("/maps/map_house.txt",1);
            }
        }
    }
    public void  pauseState(int code){
        if (code == KeyEvent.VK_SPACE) {
            gp.gameState = gp.playState;
        }

    }

    public void  levelUpState(int code){
        if (code == KeyEvent.VK_SPACE) {
            gp.gameState = gp.playState;
        }

    }
    public void  characterState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }
    public void optionsState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gp.ui.subState){
            case 0: maxCommandNum = 5;break;
            case 3: maxCommandNum = 1;break;
        }
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum --;
            gp.playSE(11);
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum ++;
            gp.playSE(11);
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(11);
                }
            }
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 2 && gp.soundEffect.volumeScale > 0){
                    gp.soundEffect.volumeScale--;
                    gp.playSE(11);
                }
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(11);
                }
            }
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 2 && gp.soundEffect.volumeScale < 5){
                    gp.soundEffect.volumeScale++;
                    gp.playSE(11);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }
}
