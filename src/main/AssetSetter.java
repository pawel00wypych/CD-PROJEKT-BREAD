package main;

import monster.GreenSlime;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        int i = 0;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 25 * gp.tileSize;
        gp.obj[i].worldY = 15 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = 25 * gp.tileSize;
        gp.obj[i].worldY = 20 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Boots(gp);
        gp.obj[i].worldX = 25 * gp.tileSize;
        gp.obj[i].worldY = 25 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Heart(gp);
        gp.obj[i].worldX = 30 * gp.tileSize;
        gp.obj[i].worldY = 30 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = 15 * gp.tileSize;
        gp.obj[i].worldY = 25 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Coin_Bronze(gp);
        gp.obj[i].worldX = 30 * gp.tileSize;
        gp.obj[i].worldY = 25 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_ManaCrystal(gp);
        gp.obj[i].worldX = 35 * gp.tileSize;
        gp.obj[i].worldY = 25 * gp.tileSize;


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
