package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Chest extends Entity {

    GamePanel gp;
    public OBJ_Chest(GamePanel gp) {

        super(gp);
        this.gp = gp;
        collisionOn = true;
        name = "Chest";
        down1 = setup("/objects/Object/chest", gp.tileSize, gp.tileSize);

    }

    @Override
    public void use(Entity entity) {
        gp.stopMusic();
        gp.playMusic(4);
        //gp.gamestate = GameEndState;
        gp.gameState = gp.gameEndState;
    }
}
