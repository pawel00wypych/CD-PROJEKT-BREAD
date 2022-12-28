package main;

import monster.GreenSlime;
import monster.RedSlime;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int mapNum = 0;

        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 39 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 35 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;


        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;


    }

    public void setMonster(){
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 13;

        i++;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 6;

        i++;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 6;

        i++;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 6;

        i++;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;

        i++;
        gp.monster[mapNum][i] = new RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 7;

        i++;
        gp.monster[mapNum][i] = new RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 8;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;

        i++;
        gp.monster[mapNum][i] = new RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 34;
    }
}
