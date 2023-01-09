package ui;

import main.GamePanel;

import java.awt.*;

public class PlayerKeysDecorator extends PlayerResourcesDecorator{


    public PlayerKeysDecorator(PlayerResources wrappee, UI ui) {
        super(wrappee, ui);
    }

    @Override
    public void drawPlayerResources() {
        super.drawPlayerResources();
        drawKeys();
    }

    public void drawKeys() {
        int x = ui.gp.tileSize/2 - 5;
        int y = ui.gp.tileSize * 3 - 20;
        ui.g2.setFont(ui.g2.getFont().deriveFont(Font.PLAIN, 28F));
        ui.g2.drawImage(ui.keyImage, x, y, null);
        x += ui.gp.tileSize - 10;
        y += 35;
        ui.g2.drawString(Integer.toString(ui.gp.player.hasKey),x,y);
    }
}
