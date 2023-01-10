package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class RedSlime extends Monster {


    GamePanel gp;

    public RedSlime(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = 2;
        name = "Red Slime";
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 7;
        life = maxLife;
        attack = 6;
        defence = 2;
        exp = 3;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 6;
        solidArea.width = 42;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public RedSlime(GamePanel gp, RedSlime redSlime) {

        super(gp, redSlime);
        this.gp = gp;
        type = redSlime.type;
        name = redSlime.name;
        defaultSpeed = redSlime.defaultSpeed;
        speed = defaultSpeed;
        maxLife = redSlime.maxLife;
        life = maxLife;
        attack = redSlime.attack;
        defence = redSlime.defence;
        exp = redSlime.exp;
        projectile = new OBJ_Rock(gp);

        solidArea.x = redSlime.solidArea.x;
        solidArea.y = redSlime.solidArea.y;
        solidArea.width = redSlime.solidArea.width;
        solidArea.height = redSlime.solidArea.height;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        up1 = setup("/monster/RedSlime/RedSlime_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/RedSlime/RedSlime_up_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/RedSlime/RedSlime_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/RedSlime/RedSlime_left_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/RedSlime/RedSlime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/RedSlime/RedSlime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/RedSlime/RedSlime_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/RedSlime/RedSlime_right_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

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
        //direction = getOppositeDirection(gp.player.lastMeleeHitDirection);
    }

    public void checkDrop() {

        int i = new Random().nextInt(100) + 1;

        //SET THE MONSTER DROP
        if (i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }

    @Override
    public Monster clone() {
        return new RedSlime(this.gp, this);
    }
}
