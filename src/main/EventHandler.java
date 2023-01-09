package main;

import state.TransisionState;

public class EventHandler {

    GamePanel gp;
    EventRect[][][] eventRect;

    public int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    public int tempMap, tempCol,tempRow;
    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row<gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 5;
            eventRect[map][col][row].y = 5;
            eventRect[map][col][row].width = 40;
            eventRect[map][col][row].height = 40;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
                if(gp.maxWorldRow == row){
                    row = 0;
                    map++;
                }
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

            if(hit(0,20,40,"any")){
                healingPool(0,20,40);
            }
            else if(hit(0,4,4,"any")){
                damagePit();
            }
            //transition to map2
            else if(hit(0,40,40,"any")){
                teleport(1,22,22);
            }
            //go back to map1
               else if(hit(1,22,22,"any")){
               teleport(0,40,40);
            }
        }

    }

    public boolean hit(int map, int eventCol, int eventRow, String reqDirection){
        boolean hit = false;
        if(map == gp.currentMap){

            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][eventCol][eventRow].x = eventCol * gp.tileSize + eventRect[map][eventCol][eventRow].x;
            eventRect[map][eventCol][eventRow].y = eventRow * gp.tileSize + eventRect[map][eventCol][eventRow].y;

            if (gp.player.solidArea.intersects(eventRect[map][eventCol][eventRow]) && !eventRect[map][eventCol][eventRow].eventDone) {

                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][eventCol][eventRow].x = eventRect[map][eventCol][eventRow].eventRectDefaultX;
            eventRect[map][eventCol][eventRow].y = eventRect[map][eventCol][eventRow].eventRectDefaultY;

        }
        return hit;
    }

    //multiple hit event for example spikes
    public void damagePit(){
        //gp.ui.showMessage("You fall into the damage pit!");
        gp.player.life -=1;
        //eventRect[eventCol][eventRow].eventDone = true;
        canTouchEvent = false;
    }

    //one time heal event
    public void healingPool(int map,int eventCol, int eventRow){

        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        canTouchEvent = false;

        eventRect[map][eventCol][eventRow].eventDone = true;
        //gp.aSetter.setMonster();
    }
    public void teleport(int map, int col, int row){
        //gp.gamestate = TransisionState;
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(13);
    }
}
