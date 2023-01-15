package state;

import main.GamePanel;

import java.awt.event.KeyEvent;

public class PauseState extends State{

    public PauseState(GamePanel gp) {
        super(gp);
    }

    @Override
    public void titleState() {

    }

    @Override
    public void playState() {

    }

    @Override
    public void pauseState() {
        if (code == KeyEvent.VK_SPACE) {

            gp.gameState = gp.playState;
        }
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

        pauseState();
    }

    @Override
    public void drawToTempScreen() {

    }
}
