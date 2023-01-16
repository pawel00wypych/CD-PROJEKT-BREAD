package state;

import main.GamePanel;

import java.awt.event.KeyEvent;

public class OptionsState extends State{

    public OptionsState(GamePanel gp) {
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
        if(code == KeyEvent.VK_ESCAPE){

            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            gp.keyH.enterPressed = true;
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

        optionsState();
    }

    @Override
    public void drawToTempScreen() {

    }
}
