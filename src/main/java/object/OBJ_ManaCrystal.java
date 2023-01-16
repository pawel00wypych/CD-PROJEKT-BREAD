package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    GamePanel gp;

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;
        value = 2;
        type = type_pickUpOnly;

        name = "Mana Crystal";
        down1 = setup("/objects/Object/manacrystal_full", gp.tileSize, gp.tileSize);
        image = setup("/objects/Object/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/Object/manacrystal_blank", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity) {

        entity.mana += value;
        gp.playSE(2);
    }
}
