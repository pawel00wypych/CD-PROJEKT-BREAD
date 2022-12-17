package main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while(col < gp.maxWorldCol && row<gp.maxWorldRow){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent(){

        //check if player is more than 1 tile away from last event
        int distance = Math.max( Math.abs(gp.player.worldX - previousEventX), Math.abs(gp.player.worldY - previousEventY));
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }

        if(canTouchEvent){
            //Damage pit in tile 2/2
            if(hit(2,2,"any")){
                damagePit(2,2);
            }
            //healing pool in tile 3/3
            if(hit(3,3,"any")){
                healingPool(3,3);
            }
        }

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[eventCol][eventRow].x = eventCol * gp.tileSize + eventRect[eventCol][eventRow].x;
        eventRect[eventCol][eventRow].y = eventCol * gp.tileSize + eventRect[eventCol][eventRow].y;

        if (gp.player.solidArea.intersects(eventRect[eventCol][eventRow]) && !eventRect[eventCol][eventRow].eventDone) {
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[eventCol][eventRow].x = eventRect[eventCol][eventRow].eventRectDefaultX;
        eventRect[eventCol][eventRow].y = eventRect[eventCol][eventRow].eventRectDefaultY;
        return hit;
    }

    //multiple hit event for example spikes
    public void damagePit(int eventCol, int eventRow){
        //gp.ui.showMessage("You fall into the damage pit!");
        gp.player.life -=1;
        //eventRect[eventCol][eventRow].eventDone = true;
        canTouchEvent = false;
    }

    //one time heal event
    public void healingPool(int eventCol, int eventRow){
        //gp.ui.showMessage("Your health has been recovered!");
        gp.player.life = gp.player.maxLife;
        eventRect[eventCol][eventRow].eventDone = true;
    }
}
