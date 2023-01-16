package entity;

import main.GamePanel;

public class Projectile extends Entity{
    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = maxLife;
        this.type = type_projectile;
    }
    public void update(){

        gp.colChecker.checkTile(this);
        if(collisionOn) {
            alive = false;
            collisionOn = false;
        }

        if(user == gp.player){
            int monsterIndex = gp.colChecker.checkEntity(this,gp.monster);
            if(monsterIndex != 999){
                gp.projectileDamage = true;
                gp.player.damageMonster(monsterIndex, gp.monster[gp.currentMap][monsterIndex].defence + this.attack);
                generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
                alive = false;
                collisionOn = false;
            }
        }
        else {
            boolean contactPlayer = gp.colChecker.checkPlayer(this);

            if(!gp.player.invincible && contactPlayer) {
                damagePlayer(gp.player.defence + attack - 1);
                generateParticle(user.projectile, gp.player);
                alive = false;
            }

        }

        adjustPosition();
        life--;
        if(life <= 0){
            alive = false;
        }
        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNumber == 1){
                spriteNumber = 2;
            }
            else if(spriteNumber == 2){
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }

    }



    public boolean haveResource(Entity user) {

        return false;
    }

    public void subtractResource(Entity user) {}

}
