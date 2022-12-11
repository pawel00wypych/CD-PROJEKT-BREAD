package entity;

import main.GamePanel;
import main.KeyHandler;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
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
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/Walking sprites/walking sprites/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/Walking sprites/walking sprites/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/Walking sprites/walking sprites/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/Walking sprites/walking sprites/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/Walking sprites/walking sprites/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/Walking sprites/walking sprites/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/Walking sprites/walking sprites/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream(
                    "/player/Walking sprites/walking sprites/boy_right_2.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
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
    }

    public void pickUpObject(int i) {

        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door":
                    if(hasKey > 0) {

                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    break;
                case "Boots":

                    gp.playSE(2);
                    speed += 2;
                    gp.obj[i] = null;
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
