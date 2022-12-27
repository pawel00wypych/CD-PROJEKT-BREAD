package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int originalTileSize = 16; //16x16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //960px
    public final int screenHeight = tileSize * maxScreenRow; //576ox

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    //FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;


    int FPS = 60;



    // SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;

    Sound music = new Sound();
    Sound soundEffect = new Sound();

    public CollisionChecker colChecker = new CollisionChecker(this);

    public AssetSetter aSetter = new AssetSetter(this);

    public UI ui = new UI(this);

    public EventHandler eHandler = new EventHandler(this);

    public Config config = new Config(this);

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity[] monster = new Entity[20];

    //you can display 50 objects at screen
    public Entity[] obj = new Entity[30];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();

    ArrayList<Entity> entityList = new ArrayList<>();


    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int characterState = 4;
    public final int optionsState = 6;
    public final int gameOverState = 7;
    public int levelUpState = 5;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setObject();
        aSetter.setMonster();
        playMusic(5);
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if(fullScreenOn)
            setFUllScreen();

    }

    public void retry(){
        player.setDefaultPositions();
        player.restoreLifeAndMana();
        aSetter.setObject();
        aSetter.setMonster();
    }
    public void restart(){
        player.setDefaultValues();
        player.setDefaultPositions();
        player.restoreLifeAndMana();
        aSetter.setObject();
        aSetter.setMonster();
    }

    public void setFUllScreen(){
        //GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //GET FULL SCREEN WIDTH AND HEIGHT
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {


        double drawInterval = 1000000000/FPS;

        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

            long currentTime = System.nanoTime();

            update();

            drawToTempScreen(); //draw everything to the buffered image
            drawToScreen(); //draw the buffered image to the screen

            try {
                double remainingTime = nextDrawTime - System.nanoTime();

                remainingTime /= 1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }
    }

    public void update() {

        if(gameState == playState) {
            player.update();

            for(int i = 0; i< monster.length;i++){
                if (monster[i] != null){
                    if(monster[i].alive && !monster[i].dying) {
                        monster[i].update();
                    }
                    if(!monster[i].alive) {
                        monster[i].checkDrop();
                        monster[i] = null;
                    }
                }

            }
            for(int i = 0; i< projectileList.size();i++){
                if (projectileList.get(i) != null){
                    if(projectileList.get(i).alive)
                        projectileList.get(i).update();
                    else
                        projectileList.remove(i);
                }
            }
            for(int i = 0; i< particleList.size();i++){
                if (particleList.get(i) != null){
                    if(particleList.get(i).alive)
                        particleList.get(i).update();
                    else
                        particleList.remove(i);
                }
            }

        } else if (gameState ==pauseState) {
            // nothing
        }
    }
    public void drawToTempScreen(){
        // DEBUG
        long drawStart = 0;
        if(keyH.showDebugText) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if(gameState == titleState) {

            ui.draw(g2);
        }
        // OTHERS
        else  {

            // TILE
            tileM.draw(g2);

            //ADD ENTITIES AND OBJECTS TO LIST
            entityList.add(player);
            for (Entity object : obj) {
                if (object != null) {
                    entityList.add(object);
                }
            }
            //ADD MONSTERS TO RENDER LIST
            for (Entity entity : monster) {
                if (entity != null){
                    entityList.add(entity);
                }
            }
            //ADD PROJECTILES TO RENDER LIST
            for (Entity projectile : projectileList) {
                if (projectile != null){
                    entityList.add(projectile);
                }
            }
            for (Entity particle : particleList) {
                if (particle != null){
                    entityList.add(particle);
                }
            }


            //SORT ENTITIES BY WORLDY
            entityList.sort(Comparator.comparingInt(e -> e.worldY));

            //DRAW ENTITIES AND CLEAR THE LIST
            for (Entity entity : entityList) {
                entity.draw(g2);
            }
            entityList.clear();

            // UI
            ui.draw(g2);

            // DEBUG

            if(keyH.showDebugText) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
                g2.setColor(Color.white);
                int x = 10;
                int y = 400;
                int lineHeight = 20;

                g2.drawString("WorldX: " + player.worldX, x, y);
                y += lineHeight;
                g2.drawString("WorldY: " + player.worldY, x, y);
                y += lineHeight;
                g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y);
                y += lineHeight;
                g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y);
                y += lineHeight;

                g2.drawString("Draw Time: " + passed, x, y);
                System.out.println("Draw Time: " + passed);
            }
        }
    }
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen,0,0,screenWidth2,screenHeight2,null);
        g.dispose();
    }
    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {

        music.stop();
    }

    public void playSE(int i) {

        soundEffect.setFile(i);
        soundEffect.play();
    }
}
