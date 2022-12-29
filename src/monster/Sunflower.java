package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class Sunflower extends Entity {



    GamePanel gp;

    public Sunflower(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = 2;
        name = "Sunflower";
        speed = 1;
        maxLife = 3;
        life = maxLife;
        attack = 2;
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

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25)
                direction = "up";
            if (i <= 50 && i > 25)
                direction = "down";
            if (i <= 75 && i > 50)
                direction = "left";
            if (i > 75)
                direction = "right";
            actionLockCounter = 0;
        }

        int i = new Random().nextInt(100) + 1;
        if (i > 40 && !projectile.alive && shotAvailableCounter == 30) {

            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
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
}