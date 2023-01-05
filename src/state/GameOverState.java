package state;

import main.GamePanel;

import java.awt.event.KeyEvent;

public class GameOverState extends State{

    public GameOverState(GamePanel gp) {
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
        gameOverState();
    }

    @Override
    public void drawToTempScreen() {

    }
}
