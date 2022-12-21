package main;

import monster.GreenSlime;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        //gp.obj[0] = new OBJ_Key(gp);
        //gp.obj[0].worldX = 20 * gp.tileSize;
        //gp.obj[0].worldY = 20 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);

        gp.obj[1].worldX = 25 * gp.tileSize;
        gp.obj[1].worldY = 15 * gp.tileSize;

        gp.obj[2] = new OBJ_Door(gp);
        gp.obj[2].worldX = 15 * gp.tileSize;
        gp.obj[2].worldY = 15 * gp.tileSize;

        gp.obj[3] = new OBJ_Chest(gp);
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 10 * gp.tileSize;

        gp.obj[4] = new OBJ_Boots(gp);
        gp.obj[4].worldX = 11 * gp.tileSize;
        gp.obj[4].worldY = 11 * gp.tileSize;

    }

    public void setMonster(){

        int i;
        for(i = 0; i < 5; i++) {

                gp.monster[i] = new GreenSlime(gp);
                gp.monster[i].worldX = gp.tileSize * (10 + i * 2);
                gp.monster[i].worldY = gp.tileSize * (10 + i);
        }
    }
}
