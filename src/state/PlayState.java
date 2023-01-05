package state;

import main.GamePanel;

import java.awt.event.KeyEvent;

public class PlayState extends State{

    public PlayState(GamePanel gp) {
        super(gp);
    }

    @Override
    public void titleState() {

    }

    @Override
    public void playState() {
        if (code == KeyEvent.VK_W) {
            gp.keyH.upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            gp.keyH.downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            gp.keyH.leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            gp.keyH.rightPressed = true;
        }
        //P - show stats
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.characterState;
        }
        // F - shot projectile
        if (code == KeyEvent.VK_F) {
            gp.keyH.shotKeyPressed = true;
        }
        // ESC - options
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }
        if (code == KeyEvent.VK_ENTER) {
            gp.keyH.enterPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {

            gp.gameState = gp.pauseState;
        }

        // DEBUG
        if (code == KeyEvent.VK_T) {
            if (!gp.keyH.showDebugText) {
                gp.keyH.showDebugText = true;
            } else {
                gp.keyH.showDebugText = false;
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

    @Override
    public void pauseState() {

    }

    @Override
    public void characterState() {

    }

    @Override
    public void optionsState() {

    }

    @Override
    public void gameOverState() {

    }

    @Override
    public void levelUpState() {

    }

    @Override
    public void transitionState() {

    }

    @Override
    public void gameEndState() {

    }

    @Override
    public void drawState(int code) {
        this.code = code;

        playState();

    }

    @Override
    public void drawToTempScreen() {

    }
}
