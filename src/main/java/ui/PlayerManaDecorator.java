package ui;

public class PlayerManaDecorator extends PlayerResourcesDecorator{


    public PlayerManaDecorator(PlayerResources wrappee, UI ui) {
        super(wrappee, ui);
    }

    @Override
    public void drawPlayerResources() {
        super.drawPlayerResources();
        drawMana();
    }

    public void drawMana() {
        int x = ui.gp.tileSize/2 - 5;
        int y = ui.gp.tileSize*2 - 20;
        int i = 0;

        // DRAW MAX MANA
        while(i < ui.gp.player.maxMana) {
            ui.g2.drawImage(ui.crystal_blank, x, y, null);
            i++;
            x += 35;
        }

        x = ui.gp.tileSize/2 - 5;
        y = ui.gp.tileSize*2 -20;
        i = 0;

        // DRAW CURRENT MANA
        while(i < ui.gp.player.mana) {

            ui.g2.drawImage(ui.crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }

}
