package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public boolean checkDrawTime = false;

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

    }
    public void titleState(int code){
        if(gp.ui.titleScreenState == 0) {

            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
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

                    System.out.println("Do some fighter stuff");
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
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {

            gp.gameState = gp.pauseState;
        }

        // DEBUG
        if (code == KeyEvent.VK_T) {
            if (!checkDrawTime) {
                checkDrawTime = true;
            } else {
                checkDrawTime = false;
            }
        }
    }
    public void  pauseState(int code){
        if (code == KeyEvent.VK_SPACE) {
            gp.gameState = gp.playState;
        }

    }
    public void  characterState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
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
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }
}
