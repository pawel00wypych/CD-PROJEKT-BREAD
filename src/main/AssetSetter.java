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
        gp.obj[i].worldX = 33 * gp.tileSize;
        gp.obj[i].worldY = 13 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 34 * gp.tileSize;
        gp.obj[i].worldY = 13 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = 25 * gp.tileSize;
        gp.obj[i].worldY = 6 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = 35 * gp.tileSize;
        gp.obj[i].worldY = 13 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = 23 * gp.tileSize;
        gp.obj[i].worldY = 39 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = 26 * gp.tileSize;
        gp.obj[i].worldY = 35 * gp.tileSize;

        i++;
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = 24 * gp.tileSize;
        gp.obj[i].worldY = 14 * gp.tileSize;


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

        int i = 0;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 33;
        gp.monster[i].worldY = gp.tileSize * 13;

        i++;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 25;
        gp.monster[i].worldY = gp.tileSize * 6;

        i++;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 27;
        gp.monster[i].worldY = gp.tileSize * 6;

        i++;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 30;
        gp.monster[i].worldY = gp.tileSize * 6;

        i++;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 36;
        gp.monster[i].worldY = gp.tileSize * 11;
    }
}
