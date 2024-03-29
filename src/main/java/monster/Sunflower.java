package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class Sunflower extends Monster {



    GamePanel gp;

    public Sunflower(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = 2;
        name = "Sunflower";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 3;
        defence = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 6;
        solidArea.y = 4;
        solidArea.width = 32;
        solidArea.height = 42;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public Sunflower(GamePanel gp, Sunflower sunflower) {

        super(gp, sunflower);
        this.gp = gp;
        type = sunflower.type;
        name = sunflower.name;
        defaultSpeed = sunflower.defaultSpeed;
        speed = defaultSpeed;
        maxLife = sunflower.maxLife;
        life = maxLife;
        attack = sunflower.attack;
        defence = sunflower.defence;
        exp = sunflower.exp;
        projectile = new OBJ_Rock(gp);

        solidArea.x = sunflower.solidArea.x;
        solidArea.y = sunflower.solidArea.y;
        solidArea.width = sunflower.solidArea.width;
        solidArea.height = sunflower.solidArea.height;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        up1 = setup("/monster/sunflower/sunflower_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/sunflower/sunflower_up_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/sunflower/sunflower_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/sunflower/sunflower_left_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/sunflower/sunflower_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/sunflower/sunflower_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/sunflower/sunflower_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/sunflower/sunflower_right_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

        randomMove();

        int i = new Random().nextInt(100) + 1;
        if (i > 50 && !projectile.alive && shotAvailableCounter == 30) {

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
        if (i < 70) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >= 70 && i < 90) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 90 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }

    @Override
    public Sunflower clone() {
        return new Sunflower(this.gp, this);
    }
}
