package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gp) {
        super(gp);
        name = "Door";
        down1 = setup("/objects/Object/door", gp.tileSize, gp.tileSize);

        collision = true;
    }
}
