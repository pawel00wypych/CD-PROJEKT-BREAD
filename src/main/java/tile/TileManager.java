package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;

    public int[][][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[100];

        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();

        loadMap("/maps/map01.txt",0);
        loadMap("/maps/map_house.txt",1);
    }

    public void getTileImage() {

        setup(0, "grass0", false);
        setup(1, "grass0", false);
        setup(2, "grass0", false);
        setup(3, "grass0", false);
        setup(4, "grass0", false);
        setup(5, "grass0", false);
        setup(6, "grass0", false);
        setup(7, "grass0", false);
        setup(8, "grass0", false);
        setup(9, "grass0", false);
        setup(10, "grass0", false);
        setup(11, "grass1", false);
        setup(12, "grass2", false);
        setup(13, "earth0", false);
        setup(14, "wall0", true);
        setup(15, "tree0", true);
        setup(16, "tree1", true);
        setup(17, "water00", true);
        setup(18, "water01", true);
        setup(19, "water02", true);
        setup(20, "water03", true);
        setup(21, "water04", true);
        setup(22, "water05", true);
        setup(23, "water06", true);
        setup(24, "water07", true);
        setup(25, "water08", true);
        setup(26, "water13", true);
        setup(27, "water10", true);
        setup(28, "water11", true);
        setup(29, "water12", true);
        setup(30, "water09", true);
        setup(31, "sand00", false);
        setup(32, "sand01", false);
        setup(33, "sand02", false);
        setup(34, "sand03", false);
        setup(35, "sand04", false);
        setup(36, "sand05", false);
        setup(37, "sand06", false);
        setup(38, "sand07", false);
        setup(39, "sand08", false);
        setup(40, "sand09", false);
        setup(41, "sand10", false);
        setup(42, "sand11", false);
        setup(43, "sand12", false);
        setup(44, "spawn00", false);
        setup(45, "spawn01", false);
        setup(46, "spawn02", false);
        setup(47, "spawn03", false);
        setup(48, "spawn04", false);
        setup(49, "spawn05", false);
        setup(50, "spawn06", false);
        setup(51, "spawn07", false);
        setup(52, "spawn08", false);
        setup(53, "spawn09", false);
        setup(54, "spawn10", false);
        setup(55, "spawn11", false);
        setup(56, "spawn12", false);
        setup(57, "spawn13", false);
        setup(58, "spawn14", false);
        setup(59, "spawn15", false);
        setup( 60, "portal00", false);
        setup( 61, "portal01", false);
        setup( 62, "portal02", false);
        setup( 63, "portal03", false);
        setup( 64, "portal04", false);
        setup( 65, "portal05", false);
        setup( 66, "portal06", false);
        setup( 67, "portal07", false);
        setup( 68, "portal08", false);
        setup( 69, "portal09", false);
        setup( 70, "portal10", false);
        setup( 71, "portal11", false);
        setup( 72, "portal12", false);
        setup( 73, "portal13", false);
        setup( 74, "portal14", false);
        setup( 75, "portal15", false);
        setup( 76, "rock00", true);
        setup( 77, "rock01", true);
        setup( 78, "thorns00", false);
        setup( 79, "pool00", false);
        setup( 80, "pool01", false);
        setup( 81, "pool02", false);
        setup( 82, "pool03", false);
        setup( 83, "pool04", false);
        setup( 84, "pool05", false);
        setup( 85, "pool06", false);
        setup( 86, "pool07", false);
        setup( 87, "pool08", false);
        setup( 88, "pool09", false);
        setup( 89, "pool10", false);
        setup( 90, "pool11", false);
        setup( 91, "pool12", false);
        setup( 92, "pool13", false);
        setup( 93, "pool14", false);
        setup( 94, "pool15", false);
    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            if(Objects.equals(imageName, "thorns00")) {
                tile[index].damage = true;
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath, int map) {

        try {

            InputStream is = getClass().getResourceAsStream(filePath);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;

                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col =0;
                    row++;
                }
            }

            br.close();

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol) {

                worldCol = 0;
                worldRow++;
            }
        }
    }
}
