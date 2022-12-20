package main;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;

    Graphics2D g2;
    Font retroGaming;
//    BufferedImage keyImage;

    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.0");
    public int commandNum = 0;
    public int titleScreenState = 0; //0: first screen, 1: second screen

    public UI(GamePanel gp) {

        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/font/Retro Gaming.ttf");

        try {

            this.retroGaming = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        OBJ_Key key = new OBJ_Key(gp);
//        keyImage = key.image;

        OBJ_Heart heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(retroGaming);
        g2.setColor(Color.WHITE);
        // TITLE STATE
        if(gp.gameState == gp.titleState)
        {
            drawTitleScreen();
        }
        // PLAY STATE
        if(gp.gameState == gp.playState) {
            
            drawPlayerLife();
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        // CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
        }

//        if(gameFinished){
//
//            g2.setFont(arial_40);
//            g2.setColor(Color.WHITE);
//            String text;
//            int textLength;
//            int x, y;
//
//            text = "You found the treasure!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 - gp.tileSize*3;
//            g2.drawString(text, x, y);
//
//            text = "Your time is :"+dFormat.format(playTime) + "!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + gp.tileSize*4;
//            g2.drawString(text, x, y);
//
//
//            g2.setFont(arial_80B);
//            g2.setColor(Color.yellow);
//
//            text = "Congratulations!";
//            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLength/2;
//            y = gp.screenHeight/2 + gp.tileSize*2;
//            g2.drawString(text, x, y);
//
//            gp.gameThread = null;
//
//        }
//        else {
//
//            g2.setFont(arial_40);
//            g2.setColor(Color.WHITE);
//            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//            g2.drawString(" x "+gp.player.hasKey, 74, 65);
//
//            // TIME
//            playTime += (double) 1/60;
//            g2.drawString("Time:"+dFormat.format(playTime)+"s", gp.tileSize*11, 65);
//
//            // MESSAGE
//            if(messageOn) {
//
//                g2.setFont(g2.getFont().deriveFont(30F));
//                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
//                messageCounter++;
//
//                if(messageCounter > 120) {
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }

    }
    public void drawPlayerLife() {

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // DRAW MAX LIFE
        while(i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        // DRAW CURRENT LIFE
        while(i < gp.player.life) {

            g2.drawImage(heart_half, x, y, null);
            i++;

            if(i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }

    public void drawTitleScreen() {

        if(titleScreenState == 0) {


            g2.setColor(new Color(0, 0, 60));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 76F));
            String text1 = "Polibuda";
            String text2 = "Boy Adventure";
            int x1 = getXforCenteredText(text1);
            int x2 = getXforCenteredText(text2);
            int y1 = gp.tileSize * 2;
            int y2 = gp.tileSize * 4;

            // SHADOW
            g2.setColor(Color.BLACK);
            g2.drawString(text1, x1 + 5, y1 + 5);
            g2.drawString(text2, x2 + 5, y2 + 5);

            // MAIN COLOR
            g2.setColor(Color.RED);
            g2.drawString(text1, x1, y1);
            g2.drawString(text2, x2, y2);

            // MAIN CHARACTER IMAGE
            int x = gp.screenWidth / 2 - gp.tileSize;
            y2 += gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x, y2, gp.tileSize * 2, gp.tileSize * 2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

            String text = "NEW GAME";
            x = getXforCenteredText(text);
            y2 += gp.tileSize * 3;
            g2.drawString(text, x, y2);
            if (commandNum == 0) {

                g2.drawString(">", x - gp.tileSize, y2);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y2 += gp.tileSize * 1;
            g2.drawString(text, x, y2);
            if (commandNum == 1) {

                g2.drawString(">", x - gp.tileSize, y2);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y2 += gp.tileSize * 1;
            g2.drawString(text, x, y2);
            if (commandNum == 2) {

                g2.drawString(">", x - gp.tileSize, y2);
            }
        }
        else if(titleScreenState == 1) {

            // CLASS SELECTION SCREEN
            g2.setColor(Color.RED);
            g2.setFont(g2.getFont().deriveFont(40F));

            String text = "Select your class";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {

                g2.drawString(">", x - gp.tileSize, y);
            }


            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 1) {

                g2.drawString(">", x - gp.tileSize, y);
            }
        }

    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public void drawCharacterScreen(){
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(22F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //NAMES
        g2.drawString("Level",textX,textY);
        textY+=lineHeight;
        g2.drawString("Life",textX,textY);
        textY+=lineHeight;
        g2.drawString("Strength",textX,textY);
        textY+=lineHeight;
        g2.drawString("Dexterity",textX,textY);
        textY+=lineHeight;
        g2.drawString("Attack",textX,textY);
        textY+=lineHeight;
        g2.drawString("Defence",textX,textY);
        textY+=lineHeight;
        g2.drawString("Exp",textX,textY);
        textY+=lineHeight;
        g2.drawString("Next Level",textX,textY);
        textY+=lineHeight;
        g2.drawString("Coins",textX,textY);
        textY+=lineHeight+20;
        g2.drawString("Weapon",textX,textY);
        textY+=lineHeight+15;
        g2.drawString("Shield",textX,textY);
        textY+=lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth)-30;
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gp.player.defence);
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight-10;

        g2.drawImage(gp.player.currentWeapon.down1,tailX-gp.tileSize,textY,null);
        textY+=gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1,tailX-gp.tileSize,textY,null);
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }
    public int getXforCenteredText(String text) {

        int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - textLength/2;

        return x;
    }
    public int getXforAlignRightText(String text,int tailX) {

        int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - textLength;

        return x;
    }
}
