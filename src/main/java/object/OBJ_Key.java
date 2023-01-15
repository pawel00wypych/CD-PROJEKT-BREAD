package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    GamePanel gp;
    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickUpOnly;

        name = "Key";
        down1 = setup("/objects/Object/key", gp.tileSize, gp.tileSize);

    }
}
