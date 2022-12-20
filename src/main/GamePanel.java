package main;

import com.sun.source.tree.EmptyStatementTree;
import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int originalTileSize = 16; //16x16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

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

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity[] monster = new Entity[20];

    //you can display only 10 objects at screen
    public Entity[] obj = new Entity[10];

    ArrayList<Entity> entityList = new ArrayList<>();


    // GAME STATE
    public int gameState;

    public final int titleState = 0;

    public final int playState = 1;
    public final int pauseState = 2;


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

            repaint();

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

            for (Entity entity : monster) {
                if (entity != null)
                    entity.update();
            }

        } else if (gameState ==pauseState) {
            // nothing
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime) {
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
            for (Entity entity : monster) {
                if (entity != null)
                    entityList.add(entity);
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

            if(keyH.checkDrawTime) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw Time: " + passed, 10, 400);
                System.out.println("Draw Time: " + passed);
            }


            g2.dispose();

        }



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
