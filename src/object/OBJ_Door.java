package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {

    GamePanel gp;
    public OBJ_Door(GamePanel gp) {

        super(gp);
        this.gp = gp;
        name = "Door";
        down1 = setup("/objects/Object/door", gp.tileSize, gp.tileSize);

        collision = true;
    }
}
