package monster;

import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class GreenSlime extends Monster{

    GamePanel gp;
    public GreenSlime(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = 2;
        name = "Green Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        attack = 4;
        defence = 1;
        exp = 2;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public GreenSlime(GamePanel gp, GreenSlime greenSlime) {

        super(gp, greenSlime);
        this.gp = gp;
        type = greenSlime.type;
        name = greenSlime.name;
        defaultSpeed = greenSlime.defaultSpeed;
        speed = defaultSpeed;
        maxLife = greenSlime.maxLife;
        life = maxLife;
        attack = greenSlime.attack;
        defence = greenSlime.defence;
        exp = greenSlime.exp;
        projectile = new OBJ_Rock(gp);

        solidArea.x = greenSlime.solidArea.x;
        solidArea.y = greenSlime.solidArea.y;
        solidArea.width = greenSlime.solidArea.width;
        solidArea.height = greenSlime.solidArea.height;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setup("/monster/GreenSlime/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/GreenSlime/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/GreenSlime/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/GreenSlime/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/GreenSlime/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/GreenSlime/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/GreenSlime/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/GreenSlime/greenslime_down_2", gp.tileSize, gp.tileSize);
    }
    public void setAction(){

        randomMove();

        int i = new Random().nextInt(100) + 1;
        if (i > 99 && !projectile.alive && shotAvailableCounter == 30) {

            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    public void damageReaction() {

        actionLockCounter = 0;
    }

    public void checkDrop() {

        int i = new Random().nextInt(100) + 1;

        //SET THE MONSTER DROP
        if(i < 75) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if(i >= 75 && i <90) {
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 90 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }

    @Override
    public GreenSlime clone() {
        return new GreenSlime(this.gp, this);
    }
}
