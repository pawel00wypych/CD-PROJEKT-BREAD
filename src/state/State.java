package state;

import main.GamePanel;

public abstract class State {

    GamePanel gp;

    int code;

    public State(GamePanel gp) {
        this.gp = gp;
    }

    public abstract void titleState();

    public abstract void playState();

    public abstract void pauseState();

    public abstract void characterState();

    public abstract void optionsState();

    public abstract void gameOverState();

    public abstract void levelUpState();

    public abstract void transitionState();

    public abstract void gameEndState();

    public abstract void drawState(int code);

    public abstract void drawToTempScreen();
}
