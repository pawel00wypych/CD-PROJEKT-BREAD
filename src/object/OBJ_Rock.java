package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Rock extends Projectile {

    GamePanel gp;
    public OBJ_Rock(GamePanel gp) {

        super(gp);
        this.gp = gp;

        solidArea = new Rectangle(10,10,28,28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        type = type_projectile;
        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("/projectiles/Fireball/rock_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/projectiles/Fireball/rock_down_1",gp.tileSize,gp.tileSize);
        down1 = setup("/projectiles/Fireball/rock_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/projectiles/Fireball/rock_down_1",gp.tileSize,gp.tileSize);
        right1 = setup("/projectiles/Fireball/rock_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/projectiles/Fireball/rock_down_1",gp.tileSize,gp.tileSize);
        left1 = setup("/projectiles/Fireball/rock_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/projectiles/Fireball/rock_down_1",gp.tileSize,gp.tileSize);
    }

    public boolean haveResource(Entity user) {

        boolean haveResource = false;

        if(user.ammo >= useCost)
            haveResource = true;

        return haveResource;
    }

    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }

    public Color getParticleColor() {
        return new Color(40, 50, 0);
    }

    public int getParticleSize() {
        return 6;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 15;
    }
}
