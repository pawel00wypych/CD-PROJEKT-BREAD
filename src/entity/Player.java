package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Fireball;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    String projectileHitDirection;
    public String lastMeleeHitDirection;

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
        getPlayerAttackImage();

    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 24;
        defaultSpeed = 4;
        speed = defaultSpeed;
        direction = "down";

        // PLAYER STATUS
        maxLife = 14;
        type = 0;
        life = maxLife;
        maxMana = 18;
        mana = maxMana;
        level = 1;
        strength = 0; //more strength -> more damage
        dexterity = 11; //more dexterity -> more defence
        exp = 0;
        nextLevelExp = 6;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack();
        defence = getDefence();
        projectile = new OBJ_Fireball(gp);

    }
    public void setDefaultPositions(){
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 24;
        direction = "down";
    }
    public void restoreLifeAndMana(){
        life = maxLife;
        mana = maxMana;
        invincible = false;
        //THOSE SHOULD BE IN START LEVEL SETUP OR SMTH LIKE THIS
        level = 1;
        exp = 0;
        nextLevelExp = 4;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        speed = 4;

    }

    public int getAttack(){

        attackArea = currentWeapon.attackArea;

        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefence(){
        return defence = dexterity * currentShield.defenceValue;
    }

    public void getPlayerImage() {

        up1 = setup("/player/Walking sprites/bob_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/Walking sprites/bob_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/Walking sprites/bob_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/Walking sprites/bob_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/Walking sprites/bob_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/Walking sprites/bob_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/Walking sprites/bob_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/Walking sprites/bob_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {

        attackUp1 = setup("/player/Attacking sprites/bob_attack_up_1", gp.tileSize, gp.tileSize *2);
        attackUp2 = setup("/player/Attacking sprites/bob_attack_up_2", gp.tileSize, gp.tileSize *2);
        attackDown1 = setup("/player/Attacking sprites/bob_attack_down_1", gp.tileSize, gp.tileSize *2);
        attackDown2 = setup("/player/Attacking sprites/bob_attack_down_2", gp.tileSize, gp.tileSize *2);
        attackLeft1 = setup("/player/Attacking sprites/bob_attack_left_1", gp.tileSize *2, gp.tileSize);
        attackLeft2 = setup("/player/Attacking sprites/bob_attack_left_2", gp.tileSize *2, gp.tileSize);
        attackRight1 = setup("/player/Attacking sprites/bob_attack_right_1", gp.tileSize *2, gp.tileSize);
        attackRight2 = setup("/player/Attacking sprites/bob_attack_right_2", gp.tileSize *2, gp.tileSize);


    }


    public void update() {

        if(attacking) {

            attacking();
        }
        else if(keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {

                if (keyH.upPressed) {
                    direction = "up";
                }  if (keyH.downPressed) {
                    direction = "down";
                }  if (keyH.leftPressed) {
                    direction = "left";
                }if (keyH.rightPressed) {
                direction = "right";
                } if (keyH.enterPressed) {
                    gp.playSE(9);
                    attacking = true;
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

                // IF FALSE PLAYER CAN MOVE
                if(!collisionOn && !keyH.enterPressed) {
                    adjustPosition();
                }

                gp.keyH.enterPressed = false;
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
        //only 1 projectile at the time
        if(gp.keyH.shotKeyPressed && !projectile.alive
                && shotAvailableCounter == 30 && projectile.haveResource(this)){
            //SET DEFAULT COORDINATES DIRECTIONS AND USER
            projectile.set(worldX, worldY, direction,true,this);

            // SUBTRACT THE COST (MANA, AMMO, ETC..)
            projectile.subtractResource(this);

            //ADD IT TO THE LIST
            gp.projectileList.add(projectile);
            gp.playSE(10);
            shotAvailableCounter = 0;
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }

        // THIS NEEDS TO BE OUTSIDE OF KEY IF STATEMENT!
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(life > maxLife)
            life = maxLife;
        if(mana > maxMana)
            mana = maxMana;

        if(life<= 0 ){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(12);
        }

    }

    public void attacking() {

        spriteCounter++;

        if(spriteCounter <= 5) {
            spriteNumber = 1;
        }
        if(spriteCounter > 5 && spriteCounter <=25) {
            spriteNumber = 2;

            // SAVE
            int currentWorldX = worldX;
            int currentWorldY = worldY;

            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // ADJUST PLAYER'S WORLDX/Y  FOR ATTACK AREA
            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += gp.tileSize; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += gp.tileSize; break;

            }

            // ATTACK AREA BECOMES solidAREA
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;



            int monsterIndex = gp.colChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            // AFTER CHECKING COLLISION, RESTORE ORIGINAL DATA
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25) {
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int i) {

        if(i != 999) {

            String objectName = gp.obj[gp.currentMap][i].name;

            // PICKUP ONLY ITEMS
                switch (objectName) {
                    case "Key":
                        gp.playSE(1);
                        hasKey++;
                        gp.obj[gp.currentMap][i] = null;
                        break;
                    case "Door":
                        if (hasKey > 0) {

                            gp.playSE(3);
                            gp.obj[gp.currentMap][i] = null;
                            hasKey--;
                        }
                        break;
                    case "Boots":
                    case "Bronze Coin":

                        gp.obj[gp.currentMap][i].use(this);
                        gp.obj[gp.currentMap][i] = null;
                        break;
                    case "Heart":

                        if(life < maxLife) {
                            gp.obj[gp.currentMap][i].use(this);
                            gp.obj[gp.currentMap][i] = null;
                        }
                        break;
                    case "Mana Crystal":

                        if(mana < maxMana) {
                            gp.obj[gp.currentMap][i].use(this);
                            gp.obj[gp.currentMap][i] = null;
                        }
                        break;
                    case "Chest":

                        if(hasKey >= 1) {
                            gp.obj[gp.currentMap][i].use(this);
                            gp.obj[gp.currentMap][i] = null;
                        }
                        break;

                }
            }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if(!attacking) {
                    if (spriteNumber == 1) {image = up1;}
                    if (spriteNumber == 2) {image = up2;}
                }
                if(attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNumber == 1) {image = attackUp1;}
                    if (spriteNumber == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if(!attacking) {
                    if (spriteNumber == 1) {image = down1;}
                    if (spriteNumber == 2) {image = down2;}
                }
                if(attacking) {
                    if (spriteNumber == 1) {image = attackDown1;}
                    if (spriteNumber == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if(!attacking) {
                    if (spriteNumber == 1) {image = left1;}
                    if (spriteNumber == 2) {image = left2;}
                }
                if(attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNumber == 1) {image = attackLeft1;}
                    if (spriteNumber == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if(!attacking) {
                    if (spriteNumber == 1) {image = right1;}
                    if (spriteNumber == 2) {image = right2;}
                }
                if(attacking) {
                    if (spriteNumber == 1) {image = attackRight1;}
                    if (spriteNumber == 2) {image = attackRight2;}
                }
                break;


        }


       if(invincible)
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //RESET ALPHA AFTER DRAWING INVINCIBLE PLAYER
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }

    public void contactMonster(int i){
        if(i != 999){
            if(!invincible && !gp.monster[gp.currentMap][i].dying){
                gp.playSE(8);
                int damage = gp.monster[gp.currentMap][i].attack - defence;
                if(damage<0)
                    damage = 1;

                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack) {
        if(i != 999) {

            if(!gp.monster[gp.currentMap][i].invincible) {
                gp.playSE(7);

                knockBack(gp.monster[gp.currentMap][i]);

                int damage = attack - gp.monster[gp.currentMap][i].defence;

                if(damage < 0)
                    damage = 0;

                lastMeleeHitDirection = direction;
                gp.monster[gp.currentMap][i].life -= damage;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if(gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void knockBack(Entity entity) {

        entity.direction = direction;
        entity.speed += 10;
        entity.knockBack = true;
    }

    public void checkLevelUp() {

        if(exp >= nextLevelExp) {

            level++;
            nextLevelExp = nextLevelExp + 7;
            exp = 0;
            maxLife += 2;
            life = maxLife;
            strength++;
            dexterity++;
            attack = getAttack();
            defence = getDefence();


            gp.playSE(10);
            gp.gameState = gp.levelUpState;
        }
    }
}
