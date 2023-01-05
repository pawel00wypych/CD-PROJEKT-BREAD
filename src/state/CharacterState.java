package state;

import main.GamePanel;

import java.awt.event.KeyEvent;

public class CharacterState extends State{


    public CharacterState(GamePanel gp) {
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
        if(code == KeyEvent.VK_P){

            gp.gameState = gp.playState;
        }
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

        characterState();
    }

    @Override
    public void drawToTempScreen() {

    }
}
