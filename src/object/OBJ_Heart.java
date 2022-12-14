package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Heart extends Entity {

    GamePanel gp;
    public OBJ_Heart(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = type_pickUpOnly;

        value = 2;
        name = "Heart";
        down1 = setup("/objects/Object/heart_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/Object/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/Object/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/Object/heart_blank", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity) {

        entity.life += value;
        gp.playSE(2);
    }
}
