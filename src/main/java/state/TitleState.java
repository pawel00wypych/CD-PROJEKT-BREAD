package state;

import main.GamePanel;

import java.awt.event.KeyEvent;

public class TitleState extends State{

    public TitleState(GamePanel gp) {
        super(gp);
    }

    @Override
    public void titleState() {
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

    public void drawState(int code) {

        this.code = code;

        titleState();
    }

    public void drawToTempScreen() {
        gp.ui.draw(gp.g2);
    }

    @Override
    public void playState() {

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
}
