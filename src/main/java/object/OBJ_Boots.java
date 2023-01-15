package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {

    GamePanel gp;
     public OBJ_Boots(GamePanel gp) {

         super(gp);
         this.gp = gp;
         type = type_pickUpOnly;

         name = "Boots";
         down1 = setup("/objects/Object/boots", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {

        entity.speed += 2;
        gp.playSE(2);
    }
}
