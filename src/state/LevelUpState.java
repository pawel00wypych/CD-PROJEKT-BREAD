package state;

import main.GamePanel;

import java.awt.event.KeyEvent;

public class LevelUpState extends State{

    public LevelUpState(GamePanel gp) {
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
        if (code == KeyEvent.VK_SPACE) {

            gp.gameState = gp.playState;
        }
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

        levelUpState();
    }

    @Override
    public void drawToTempScreen() {

    }
}
