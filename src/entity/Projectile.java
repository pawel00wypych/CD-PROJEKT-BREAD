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
    }
    public void update(){

        if(user == gp.player){
            int monsterIndex = gp.colChecker.checkEntity(this,gp.monster);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex,this.attack);
                generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
                alive = false;
            }
        }
        if(user != gp.player) {
            boolean contactPlayer = gp.colChecker.checkPlayer(this);

            if(!gp.player.invincible && contactPlayer) {
                damagePlayer(attack);
                generateParticle(user.projectile, gp.player);
                alive = false;
            }

        }

        switch (direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }
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

        boolean haveResource = false;
        return haveResource;
    }

    public void subtractResource(Entity user) {}

}
