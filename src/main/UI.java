package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;

    Graphics2D g2;
    Font arial_40, arial_80B;
//    BufferedImage keyImage;
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
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

//        OBJ_Key key = new OBJ_Key(gp);
//        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        g2.setColor(Color.WHITE);
        // TITLE STATE
        if(gp.gameState == gp.titleState)
        {
            drawTitleScreen();
        }
        // PLAY STATE
        if(gp.gameState == gp.playState) {
            
            // TODO
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
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

    public void drawTitleScreen() {

        if(titleScreenState == 0) {


            g2.setColor(new Color(0, 0, 60));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 86F));
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

    public int getXforCenteredText(String text) {

        int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - textLength/2;

        return x;
    }
}