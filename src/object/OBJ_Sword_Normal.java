package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    GamePanel gp;
    public OBJ_Sword_Normal(GamePanel gp) {

        super(gp);
        this.gp = gp;

        name = "Normal Sword";
        down1 = setup("/objects/Object/sword_normal",gp.tileSize,gp.tileSize);
        attackValue = 2;

        attackArea.width = 48;
        attackArea.height = 48;
    }


}
