package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity {

    GamePanel gp;

    public OBJ_Coin_Bronze(GamePanel gp) {

        super(gp);
        this.gp = gp;
        value = 1;

        type = type_pickUpOnly;
        name = "Bronze Coin";
        down1 = setup("/objects/Object/coin_bronze", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {

        gp.playSE(1);
        gp.player.coin += value;

    }
}
