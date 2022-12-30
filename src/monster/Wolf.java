package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class Wolf extends Entity {

    GamePanel gp;

    public Wolf(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = 2;
        name = "Wolf";
        speed = 2;
        maxLife = 12;
        life = maxLife;
        attack = 8;
        defence = 2;
        exp = 6;

        solidArea.x = 3;
        solidArea.y = 6;
        solidArea.width = 42;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/wolf/wolf_up_1", gp.tileSize, gp.tileSize * 2);
        up2 = setup("/monster/wolf/wolf_up_2", gp.tileSize, gp.tileSize * 2);
        left1 = setup("/monster/wolf/wolf_left_1", gp.tileSize * 2, gp.tileSize);
        left2 = setup("/monster/wolf/wolf_left_2", gp.tileSize * 2, gp.tileSize);
        down1 = setup("/monster/wolf/wolf_down_1", gp.tileSize, gp.tileSize * 2);
        down2 = setup("/monster/wolf/wolf_down_2", gp.tileSize, gp.tileSize * 2);
        right1 = setup("/monster/wolf/wolf_right_1", gp.tileSize * 2, gp.tileSize);
        right2 = setup("/monster/wolf/wolf_right_2", gp.tileSize * 2, gp.tileSize);
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
                solidArea.x = 9;
                solidArea.y = 12;
                solidArea.width = 30;
                solidArea.height = 75;
            }
            if (i <= 50 && i > 25) {
                direction = "down";
                solidArea.x = 9;
                solidArea.y = 14;
                solidArea.width = 30;
                solidArea.height = 75;
            }
            if (i <= 75 && i > 50) {
                direction = "left";
                solidArea.x = 6;
                solidArea.y = 12;
                solidArea.width = 84;
                solidArea.height = 34;
            }
            if (i > 75) {
                direction = "right";
                solidArea.x = 10;
                solidArea.y = 12;
                solidArea.width = 84;
                solidArea.height = 34;
            }
            actionLockCounter = 0;
        }

    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void checkDrop() {

        int i = new Random().nextInt(100) + 1;

        //SET THE MONSTER DROP
        if (i < 75) {
            dropItem(new OBJ_Key(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_Heart(gp));
        }
    }
}
