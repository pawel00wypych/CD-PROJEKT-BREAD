package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;

    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[31];

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();

        loadMap("/maps/map01.txt");
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
        setup(26, "water09", true);
        setup(27, "water10", true);
        setup(28, "water11", true);
        setup(29, "water12", true);
        setup(30, "water13", true);


    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath) {

        try {

            InputStream is = getClass().getResourceAsStream(filePath);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;

                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col =0;
                    row++;
                }
            }

            br.close();

        }catch(Exception e) {

        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

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
