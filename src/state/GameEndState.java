package state;

import main.GamePanel;

import java.awt.event.KeyEvent;

public class GameEndState extends State{

    public GameEndState(GamePanel gp) {
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

    }

    @Override
    public void transitionState() {

    }

    @Override
    public void gameEndState() {
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

    @Override
    public void drawState(int code) {

        this.code = code;

        gameEndState();
    }

    @Override
    public void drawToTempScreen() {

    }
}
