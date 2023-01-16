package main;

import entity.Entity;
import monster.*;
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
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 22 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;


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
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;


        mapNum++;
        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 29 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 27 * gp.tileSize;

        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;

    }

    public void setMonster(){
        MonsterFactory factory = new MonsterFactory();
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = factory.create(gp,MonsterClass.GreenSlime);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 13;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 6;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 6;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 7;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;

        i++;
        gp.monster[mapNum][i] = factory.create(gp,MonsterClass.RedSlime);
        gp.monster[mapNum][i].worldX = gp.tileSize * 31;
        gp.monster[mapNum][i].worldY = gp.tileSize * 7;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 40;
        gp.monster[mapNum][i].worldY = gp.tileSize * 44;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 8;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;

        i++;

        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 34;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 19;
        gp.monster[mapNum][i].worldY = gp.tileSize * 19;

        i++;
        gp.monster[mapNum][i] = factory.create(gp,MonsterClass.Sunflower);
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 6;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 32;
        gp.monster[mapNum][i].worldY = gp.tileSize * 32;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 18;
        gp.monster[mapNum][i].worldY = gp.tileSize * 17;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 41;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 40;
        gp.monster[mapNum][i].worldY = gp.tileSize * 32;

        i++;
        gp.monster[mapNum][i] = factory.create(gp,MonsterClass.Wolf);
        gp.monster[mapNum][i].worldX = gp.tileSize * 14;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 12;
        gp.monster[mapNum][i].worldY = gp.tileSize * 7;

        mapNum++;
        i++;
        gp.monster[mapNum][i] = factory.create(gp,MonsterClass.RedSlime);
        gp.monster[mapNum][i].worldX = gp.tileSize * 17;
        gp.monster[mapNum][i].worldY = gp.tileSize * 28;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 17;
        gp.monster[mapNum][i].worldY = gp.tileSize * 17;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 17;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 28;

        i++;
        gp.monster[mapNum][i] = factory.create(gp,MonsterClass.SandSlime);
        gp.monster[mapNum][i].worldX = gp.tileSize * 20;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 15;
        gp.monster[mapNum][i].worldY = gp.tileSize * 24;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 15;
        gp.monster[mapNum][i].worldY = gp.tileSize * 15;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 21;
        gp.monster[mapNum][i].worldY = gp.tileSize * 15;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 21;
        gp.monster[mapNum][i].worldY = gp.tileSize * 27;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31;

        i++;
        gp.monster[mapNum][i] = factory.create(gp,MonsterClass.Wolf);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37;

        i++;
        gp.monster[mapNum][i] = factory.create(gp,MonsterClass.Sunflower);
        gp.monster[mapNum][i].worldX = gp.tileSize * 20;
        gp.monster[mapNum][i].worldY = gp.tileSize * 8;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 22;
        gp.monster[mapNum][i].worldY = gp.tileSize * 8;

        i++;
        gp.monster[mapNum][i] = gp.monster[mapNum][i-1].clone();
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 8;
    }
}
