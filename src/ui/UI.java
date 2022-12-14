package ui;

import entity.Entity;
import main.ConfigMemento;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;

    Graphics2D g2;
    Font retroGaming;

    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank, keyImage;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.0");
    public int commandNum = 0;
    public int titleScreenState = 0; //0: first screen, 1: second screen
    public int subState = 0;
    int counter = 0;

    PlayerResourcesDecorator playerResources;


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

        Entity key = new OBJ_Key(gp);
        keyImage = key.down1;

        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;

        playerResources = new PlayerKeysDecorator(new PlayerManaDecorator(new PlayerLife(this), this), this);

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

            playerResources.drawPlayerResources();
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {

            playerResources.drawPlayerResources();
            drawPauseScreen();
        }
        // CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
        }
        if(gp.gameState == gp.levelUpState){
            drawLevelUpState();
        }
        // OPTIONS STATE
        if(gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }
        // GAME OVER STATE
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
        // TRANSITION STATE
        if(gp.gameState == gp.transitionState){
            drawTransition();
        }
        if(gp.gameState == gp.gameEndState){
            drawGameEnd();
        }

    }
    public void drawGameEnd(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text = "CONGRATULATIONS !!!";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));

        //shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text,x,y);
        //white text
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);
        y+=gp.tileSize*2;
        text = "YOU FOUND A TREASURE";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));

        //shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        g2.drawString(text,x,y);
        //white text
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //back to the title screen
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        text = "Back to menu";
        x = getXforCenteredText(text);
        y+=gp.tileSize*2;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">",x-40,y);
        }
    }
    public void drawTransition(){
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        if(counter == 50){
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
        }
    }
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text = "GAME OVER";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110F));

        //shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text,x,y);
        //white text
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //retry
        g2.setFont(g2.getFont().deriveFont(30f));
        text = "Retry";
        x= getXforCenteredText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">",x-40,y);
        }

        //back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y+=gp.tileSize*2;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">",x-40,y);
        }
    }
    public void drawOptionsScreen(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(22F));

        //SUB WINDOW
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch (subState){
            case 0:options_top(frameX,frameY); break;
            case 1:options_fullScreenNotification(frameX,frameY); break;
            case 2:options_control(frameX,frameY); break;
            case 3:options_endGameConfirmation(frameX,frameY); break;
        }
        gp.keyH.enterPressed = false;

    }
    public void options_top(int frameX, int frameY){
        int textX;
        int textY;
        //TITLE
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY+ gp.tileSize;
        g2.drawString(text,textX,textY);

        //FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full Screen",textX,textY);
        if(commandNum == 0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed){
                gp.fullScreenOn = !gp.fullScreenOn;
                subState = 1;
            }
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Music",textX,textY);
        if(commandNum == 1){
            g2.drawString(">",textX-25,textY);
        }
        // SE
        textY += gp.tileSize;
        g2.drawString("Effects",textX,textY);
        if(commandNum == 2){
            g2.drawString(">",textX-25,textY);
        }
        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Control",textX,textY);
        if(commandNum == 3){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed){
                subState = 2;
                commandNum = 0;
            }
        }
        //EXIT GAME
        textY += gp.tileSize;
        g2.drawString("Exit Game",textX,textY);
        if(commandNum == 4){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed){
                subState = 3;
                commandNum = 0;
            }
        }
        //BACK
        textY += gp.tileSize*2;
        g2.drawString("Back",textX,textY);
        if(commandNum == 5){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed){
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //FULL SCREEN CHECKBOX
        textX = frameX + gp.tileSize*5;
        textY = (int) ( frameY + gp.tileSize*2.6);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY, gp.tileSize/2,gp.tileSize/2 );
        if(gp.fullScreenOn){
            g2.fillRect(textX,textY, gp.tileSize/2,gp.tileSize/2 );
        }

        //MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX,textY,120,gp.tileSize/2);
        int volumeWidth = 24*gp.music.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);

        //SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX,textY,120,gp.tileSize/2);
        int soundEffectWidth = 24*gp.soundEffect.volumeScale;
        g2.fillRect(textX,textY,soundEffectWidth,24);



        gp.config.saveConfig(new ConfigMemento(gp.fullScreenOn,gp.music.volumeScale,gp.soundEffect.volumeScale));

    }
    public void options_fullScreenNotification(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;
        String message = "The change will take \neffect after \nrestarting the game";
        for(String line: message.split("\n")){
            g2.drawString(line,textX,textY);
            textY+= 40;
        }
        //BACK
        textY += gp.tileSize;
        g2.drawString("Back",textX,textY);
        if(commandNum == 0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed){
                subState = 0;
            }
        }
    }
    public void options_control(int frameX, int frameY){
        int textX;
        int textY;

        //TITLE
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text,textX,textY);

        textX = frameX + gp.tileSize;
        textY +=gp.tileSize;
        g2.drawString("Move",textX,textY);
        textY+= gp.tileSize;
        g2.drawString("Attack",textX,textY);
        textY+= gp.tileSize;
        g2.drawString("Shoot",textX,textY);
        textY+= gp.tileSize;
        g2.drawString("Stats",textX,textY);
        textY+= gp.tileSize;
        g2.drawString("Pause",textX,textY);
        textY+= gp.tileSize;
        g2.drawString("Options",textX,textY);

        textX = frameX + gp.tileSize*4;
        textY = frameY + gp.tileSize*2;
        g2.drawString("W A S D",textX,textY);
        textY+= gp.tileSize;
        g2.drawString("ENTER",textX,textY);
        textY+= gp.tileSize;
        g2.drawString("F",textX,textY);
        textY+= gp.tileSize;
        g2.drawString("P",textX,textY);
        textY+= gp.tileSize;
        g2.drawString("SPACE",textX,textY);
        textY+= gp.tileSize;
        g2.drawString("ESC",textX,textY);
        //BACK
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Back",textX,textY);
        if(commandNum == 0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed){
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void options_endGameConfirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        String message = "Quit game and return \nto title screen?";
        for(String line: message.split("\n")){
            g2.drawString(line,textX,textY);
            textY+= 40;
        }
        //YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text,textX,textY);
        if(commandNum == 0){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed){
                subState = 0;
                gp.gameState = gp.titleState;
                gp.stopMusic();
                gp.playMusic(5);
                gp.restart();
            }
        }
        //NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text,textX,textY);
        if(commandNum == 1){
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed){
                subState = 0;
                commandNum = 4;
            }
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
            y2 += gp.tileSize;
            g2.drawString(text, x, y2);
            if (commandNum == 1) {

                g2.drawString(">", x - gp.tileSize, y2);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y2 += gp.tileSize;
            g2.drawString(text, x, y2);
            if (commandNum == 2) {

                g2.drawString(">", x - gp.tileSize, y2);
            }
        }
        else if(titleScreenState == 1) {

            g2.setColor(new Color(0, 0, 60));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
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
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
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
        g2.drawString("Mana",textX,textY);
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


        //VALUES
        int tailX = (frameX + frameWidth)-30;
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = gp.player.life + "/" + gp.player.maxLife;
        textX = getXforAlignRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = gp.player.mana + "/" + gp.player.maxMana;
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
        textY+=lineHeight-15;

        g2.drawImage(gp.player.currentWeapon.down1,tailX-gp.tileSize,textY - 5,null);
        textY+=gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1,tailX-gp.tileSize,textY - 5,null);
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

        return gp.screenWidth/2 - textLength/2;
    }
    public int getXforAlignRightText(String text,int tailX) {

        int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        return tailX - textLength;
    }
    public void drawLevelUpState() {

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        String text1 = "Your level is "+ gp.player.level + " now!";
        String text2 = "You feel stronger!";
        int x1 = getXforCenteredText(text1);
        int x2 = getXforCenteredText(text2);
        int y1 = gp.tileSize * 2;
        int y2 = gp.tileSize * 4;

        // SHADOW
        g2.setColor(Color.BLACK);
        g2.drawString(text1, x1 + 5, y1 + 5);
        g2.drawString(text2, x2 + 5, y2 + 5);

        // MAIN COLOR
        g2.setColor(Color.YELLOW);
        g2.drawString(text1, x1, y1);
        g2.drawString(text2, x2, y2);
    }
}
