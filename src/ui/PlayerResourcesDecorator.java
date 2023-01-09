package ui;

import main.GamePanel;

import java.awt.*;

public class PlayerResourcesDecorator implements PlayerResources{

    private PlayerResources wrappee;
    UI ui;

    public PlayerResourcesDecorator(PlayerResources wrappee, UI ui) {
        this.wrappee = wrappee;
        this.ui = ui;
    }

    @Override
    public void drawPlayerResources() {
        wrappee.drawPlayerResources();
    }
}
