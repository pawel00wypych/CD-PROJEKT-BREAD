package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

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


    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    public int shotAvailableCounter = 0;


    // CHARACTER ATTRIBUTES
    public String name;
    public int type; // 0 = player , 2 = monster

    public final int type_pickUpOnly = 3;
    public final int type_consumable = 4;
    public int maxLife;
    public int life;
    public int speed;
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

    public void setAction(){}

    public void damageReaction(){}

    public void update(){

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
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
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

    public void damagePlayer(int attack) {
        if(!gp.player.invincible){
            gp.playSE(8);


            int damage = attack - gp.player.defence;
            if(damage<0)
                damage = 0;

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

            if(dying){
                dyingAnimation(g2);
            }
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

            if(gp.obj[i] == null) {

                gp.obj[i] = droppedItem;
                gp.obj[i].worldX = worldX; // the dead monster's worldX
                gp.obj[i].worldY = worldY;
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

}
