package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public abstract class Entity {

    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2,
            attackLeft1, attackLeft2, attackRight1, attackRight2;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public BufferedImage image, image2, image3;

    public boolean collision = false;
    // STATE
    public int worldX, worldY;
    public String direction="down";
    public boolean invincible = false;
    public int spriteNumber = 1;
    public  boolean collisionOn = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public boolean knockBack = false;
    public boolean jumpingState = true;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int knockBackCounter = 0;
    public int shotAvailableCounter = 0;
    public int pickupFloatCounter = 0;


    // CHARACTER ATTRIBUTES
    public String name;
    public int type; // 0 = player , 2 = monster
    public final int type_projectile = 1;
    public final int type_pickUpOnly = 3;
    public int maxLife;
    public int life;
    public int speed;
    public int defaultSpeed;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int exp;
    public int defence;
    public int coin;
    public int nextLevelExp;
    public Entity currentWeapon;
    public Entity currentShield;
    public int maxMana;
    public int mana;
    public int ammo;
    public Projectile projectile;
    public Entity user;

    //ITEM ATTRIBUTES
    public int value;
    public int attackValue;
    public int defenceValue;
    public int useCost;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction(){
        if(type == 2) {
            randomMove();

            int i = new Random().nextInt(100) + 1;
            if (i > 99 && !projectile.alive && shotAvailableCounter == 30) {

                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shotAvailableCounter = 0;
            }
        }
    }

    public void randomMove() {
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
    }

    public void damageReaction() {}

    public void update(){


        if(knockBack) {

            collisionOn = false;

            if(gp.projectileDamage) {

                direction = gp.player.projectileHitDirection;
            } else {

                direction = gp.player.lastMeleeHitDirection;
            }

            gp.colChecker.checkTile(this);
            gp.colChecker.checkObject(this,false);
            gp.colChecker.checkEntity(this,gp.monster);

            if(gp.projectileDamage) {

                direction = getOppositeDirection(gp.player.projectileHitDirection);
            } else {

                direction = getOppositeDirection(gp.player.lastMeleeHitDirection);
            }


            if(collisionOn) {

                gp.projectileDamage = false;
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;

            } else {
                if(gp.projectileDamage) {

                    switch (gp.player.projectileHitDirection) {
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }

                } else {

                    switch (gp.player.lastMeleeHitDirection) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }
            }

            knockBackCounter++;
            if(knockBackCounter >= 10) {

                gp.projectileDamage = false;
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        } else {

            setAction();
            collisionOn = false;
            gp.colChecker.checkTile(this);
            gp.colChecker.checkObject(this,false);
            gp.colChecker.checkEntity(this,gp.monster);
            boolean contactPlayer = gp.colChecker.checkPlayer(this);

            if(this.type == 2 && contactPlayer){
                damagePlayer(attack);
            }

            if(!collisionOn) {
                adjustPosition();
            }
        }


        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    public void adjustPosition() {
        switch (direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }
    }

    public void damagePlayer(int attack) {
        if(!gp.player.invincible){
            gp.playSE(8);


            int damage = attack - gp.player.defence;
            if(damage<0)
                damage = 1;

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "up":
                    if(spriteNumber == 1) {image = up1;}
                    if(spriteNumber == 2) {image = up2;}
                    break;
                case "down":
                    if(spriteNumber == 1) {image = down1;}
                    if(spriteNumber == 2) {image = down2;}
                    break;
                case "left":
                    if(spriteNumber == 1) {image = left1;}
                    if(spriteNumber == 2) {image = left2;}
                    break;
                case "right":
                    if(spriteNumber == 1) {image = right1;}
                    if(spriteNumber == 2) {image = right2;}
                    break;
            }

            //MONSTER HP BAR
            if(type == 2 && hpBarOn){
                double oneScale = (double) gp.tileSize/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1,screenY-16,gp.tileSize+2,12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX,screenY - 15,(int)hpBarValue,10);

                hpBarCounter++;
                if(hpBarCounter>600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }

            }

            if(invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2,0.4f);
            }

            if(dying && type==2){
                dyingAnimation(g2);
            }
            if(type == type_pickUpOnly){
                if(pickupFloatCounter <=40 && pickupFloatCounter >=0 ){

                    g2.drawImage(image, screenX, screenY+((int)pickupFloatCounter/4), null);
                    if(jumpingState)
                        pickupFloatCounter ++;
                    else
                        pickupFloatCounter--;
                }else {
                    g2.drawImage(image, screenX, screenY+((int)pickupFloatCounter/4), null);
                    jumpingState = !jumpingState;
                    if(jumpingState)
                        pickupFloatCounter ++;
                    else
                        pickupFloatCounter--;
                }
            }else
                g2.drawImage(image, screenX, screenY, null);

            changeAlpha(g2,1f);

        }

    }
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        //FLASHING INTERVAL
        int i = 5;

        if(dyingCounter <= i)
            changeAlpha(g2,0f);
        if(dyingCounter > i &&dyingCounter<=i*2)
            changeAlpha(g2,1f);
        if(dyingCounter > i*2 &&dyingCounter<=i*3)
            changeAlpha(g2,0f);
        if(dyingCounter > i*3 &&dyingCounter<=i*4)
            changeAlpha(g2,1f);
        if(dyingCounter > i*4 &&dyingCounter<=i*5)
            changeAlpha(g2,0f);
        if(dyingCounter > i*5 &&dyingCounter<=i*6)
            changeAlpha(g2,1f);
        if(dyingCounter > i*6 &&dyingCounter<=i*7)
            changeAlpha(g2,0f);
        if(dyingCounter > i*7 &&dyingCounter<=i*8)
            changeAlpha(g2,1f);

        if(dyingCounter > i*8){
            alive = false;
        }

    }

    public void checkDrop() {}
    public void dropItem(Entity droppedItem) {

        for(int i = 0; i < gp.obj.length; i++) {

            if(gp.obj[gp.currentMap][i] == null) {

                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX; // the dead monster's worldX
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public void changeAlpha(Graphics2D g2,float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image = uTool.scaleImage(image, width, height);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
    public void use(Entity entity) {}

    public Color getParticleColor() {
        return null;
    }

    public int getParticleSize() {
        return 0;
    }

    public int getParticleSpeed() {
        return 0;
    }

    public int getParticleMaxLife() {
        return 0;
    }

    public void generateParticle(Entity generator, Entity target) {

        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color,size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color,size, speed, maxLife, -2, 1);
        Particle p3 = new Particle(gp, target, color,size, speed, maxLife, 2, -1);
        Particle p4 = new Particle(gp, target, color,size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public String getOppositeDirection(String direction) {

        switch (direction) {

            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }

        return direction;
    }

    public int getMana() {
        return mana;
    }
}
