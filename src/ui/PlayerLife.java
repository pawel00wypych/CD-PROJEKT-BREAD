package ui;

public class PlayerLife implements PlayerResources {

    UI ui;

    public PlayerLife(UI ui) {
        this.ui = ui;
    }

    @Override
    public void drawPlayerResources() {
        int x = ui.gp.tileSize/2;
        int y = ui.gp.tileSize/2;
        int i = 0;

        // DRAW MAX LIFE
        while(i < ui.gp.player.maxLife/2) {
            ui.g2.drawImage(ui.heart_blank, x, y, null);
            i++;
            x += ui.gp.tileSize;
        }

        x = ui.gp.tileSize/2;
        i = 0;

        // DRAW CURRENT LIFE
        while(i < ui.gp.player.life) {

            ui.g2.drawImage(ui.heart_half, x, y, null);
            i++;

            if(i < ui.gp.player.life) {
                ui.g2.drawImage(ui.heart_full, x, y, null);
            }
            i++;
            x += ui.gp.tileSize;
        }
    }
}
