package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 20;
        worldY = gp.tileSize * 20;
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        up1 = setup("/player/Walking sprites/boy_up_1");
        up2 = setup("/player/Walking sprites/boy_up_2");
        down1 = setup("/player/Walking sprites/boy_down_1");
        down2 = setup("/player/Walking sprites/boy_down_2");
        left1 = setup("/player/Walking sprites/boy_left_1");
        left2 = setup("/player/Walking sprites/boy_left_2");
        right1 = setup("/player/Walking sprites/boy_right_1");
        right2 = setup("/player/Walking sprites/boy_right_2");
    }


    public void update() {

        if(keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed) {

                if (keyH.upPressed) {
                    direction = "up";
                } else if (keyH.downPressed) {
                    direction = "down";
                } else if (keyH.leftPressed) {
                    direction = "left";
                } else if (keyH.rightPressed) {
                    direction = "right";
                }

                // CHECK TILE COLLISION
                collisionOn = false;
                gp.colChecker.checkTile(this);

                // CHECK TILE COLLISION
                int objIndex = gp.colChecker.checkObject(this, true);

                //CHECK MONSTER COLLISION
                int monsterIndex = gp.colChecker.checkEntity(this,gp.monster);
                contactMonster(monsterIndex);

                //CHECK EVENT
                gp.eHandler.checkEvent();

                pickUpObject(objIndex);

                if(!collisionOn) {
                    switch (direction) {

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

                spriteCounter++;
                if (spriteCounter > 12) {
                    if (spriteNumber == 1) {
                        spriteNumber = 2;
                    } else if (spriteNumber == 2) {
                        spriteNumber = 1;
                    }
                    spriteCounter = 0;
                }
        }

        //
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {

        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey > 0) {

                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else {
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":

                    gp.playSE(2);
                    speed += 2;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed up!");
                    break;
                case "Chest":

                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNumber == 1) {
                    image = up1;
                }
                if(spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNumber == 1) {
                    image = down1;
                }
                if(spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber == 1) {
                    image = left1;
                }
                if(spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1) {
                    image = right1;
                }
                if(spriteNumber == 2) {
                    image = right2;
                }
                break;

        }

       if(invincible)
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));

        g2.drawImage(image, screenX, screenY, null);

        //RESET ALPHA AFTER DRAWING INVINCIBLE PLAYER
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }
    public void contactMonster(int i){
        if(i != 999){
            if(!invincible){
                life -=1;
                invincible = true;
            }
        }
    }
}
