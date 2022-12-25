package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        name = "Normal Sword";
        down1 = setup("/objects/Object/sword_normal",gp.tileSize,gp.tileSize);
        attackValue = 2;

        attackArea.width = 36;
        attackArea.height = 36;
    }


}
