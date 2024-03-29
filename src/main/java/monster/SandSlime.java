package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;
public class SandSlime extends Monster {


    GamePanel gp;

    public SandSlime(GamePanel gp) {

        super(gp);
        this.gp = gp;
        type = 2;
        name = "Sand Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 12;
        life = maxLife;
        attack = 8;
        defence = 5;
        exp = 5;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public SandSlime(GamePanel gp, SandSlime sandSlime) {

        super(gp, sandSlime);
        this.gp = gp;
        type = sandSlime.type;
        name = sandSlime.name;
        defaultSpeed = sandSlime.defaultSpeed;
        speed = defaultSpeed;
        maxLife = sandSlime.maxLife;
        life = maxLife;
        attack = sandSlime.attack;
        defence = sandSlime.defence;
        exp = sandSlime.exp;
        projectile = new OBJ_Rock(gp);

        solidArea.x = sandSlime.solidArea.x;
        solidArea.y = sandSlime.solidArea.y;
        solidArea.width = sandSlime.solidArea.width;
        solidArea.height = sandSlime.solidArea.height;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/SandSlime/sandslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/SandSlime/sandslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/SandSlime/sandslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/SandSlime/sandslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/SandSlime/sandslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/SandSlime/sandslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/SandSlime/sandslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/SandSlime/sandslime_down_2", gp.tileSize, gp.tileSize);
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
    public SandSlime clone() {
        return new SandSlime(this.gp, this);
    }
}