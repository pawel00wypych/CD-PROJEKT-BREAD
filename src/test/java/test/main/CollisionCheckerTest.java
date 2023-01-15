package test.main;

import entity.Player;
import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;
import monster.GreenSlime;
import object.OBJ_Fireball;
import object.OBJ_Rock;
import org.junit.jupiter.api.Test;
import tile.TileManager;

import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CollisionCheckerTest {
    @Test
    void should_return_true_on_player_out_of_map_checkTile() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        CollisionChecker colChecker = new CollisionChecker(gp);
        Player player = new Player(gp,keyH);
        player.worldX = gp.tileSize * 55;
        player.worldY = gp.tileSize * 55;
        colChecker.checkTile(player);
        assertTrue(player.collisionOn);
    }
    @Test
    void should_return_true_on_player_on_tree_checkTile() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        CollisionChecker colChecker = new CollisionChecker(gp);
        Player player = new Player(gp,keyH);
        player.worldX = gp.tileSize * 41;
        player.worldY = gp.tileSize * 8;
        colChecker.checkTile(player);
        assertTrue(player.collisionOn);
    }
    @Test
    void should_return_false_on_projectile_on_water_checkTile() {
        GamePanel gp = GamePanel.getInstance();
        CollisionChecker colChecker = new CollisionChecker(gp);
        OBJ_Fireball fireball = new OBJ_Fireball(gp);
        fireball.worldX = gp.tileSize * 1;
        fireball.worldY = gp.tileSize * 1;
        colChecker.checkTile(fireball);
        assertFalse(fireball.collisionOn);
    }
    @Test
    void should_return_false_on_player_on_grass_checkTile() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        CollisionChecker colChecker = new CollisionChecker(gp);
        Player player = new Player(gp,keyH);
        player.worldX = gp.tileSize * 24;
        player.worldY = gp.tileSize * 24;
        colChecker.checkTile(player);
        assertFalse(player.collisionOn);
    }

    @Test
    void should_return_true_on_two_monsters_checkEntity() {
        GamePanel gp = GamePanel.getInstance();
        GreenSlime greenSlime = new GreenSlime(gp);
        GreenSlime greenSlime2 = new GreenSlime(gp);
        CollisionChecker colChecker = new CollisionChecker(gp);
        greenSlime.worldX = gp.tileSize * 24;
        greenSlime.worldY = gp.tileSize * 24;
        greenSlime2.worldX = gp.tileSize * 24;
        greenSlime2.worldY = gp.tileSize * 24;
        Arrays.fill( gp.monster[0], null);
        gp.monster[0][0] = greenSlime2;
        colChecker.checkEntity(greenSlime,gp.monster);
        assertEquals(colChecker.checkEntity(greenSlime,gp.monster),0);
    }

    @Test
    void should_return_false_on_two_monsters_checkEntity() {
        GamePanel gp = GamePanel.getInstance();
        GreenSlime greenSlime = new GreenSlime(gp);
        GreenSlime greenSlime2 = new GreenSlime(gp);
        CollisionChecker colChecker = new CollisionChecker(gp);
        greenSlime.worldX = gp.tileSize * 24;
        greenSlime.worldY = gp.tileSize * 24;
        greenSlime2.worldX = gp.tileSize * 25;
        greenSlime2.worldY = gp.tileSize * 25;
        Arrays.fill( gp.monster[0], null);
        gp.monster[0][0] = greenSlime2;
        colChecker.checkEntity(greenSlime,gp.monster);
        assertEquals(colChecker.checkEntity(greenSlime,gp.monster),999);
    }

    @Test
    void should_return_true_on_projectile_hit_monster_checkEntity() {
        GamePanel gp = GamePanel.getInstance();
        GreenSlime greenSlime = new GreenSlime(gp);
        OBJ_Fireball fireball = new OBJ_Fireball(gp);
        CollisionChecker colChecker = new CollisionChecker(gp);
        greenSlime.worldX = gp.tileSize * 24;
        greenSlime.worldY = gp.tileSize * 24;
        fireball.worldX = gp.tileSize * 24;
        fireball.worldY = gp.tileSize * 24;
        Arrays.fill( gp.monster[0], null);
        gp.monster[0][0] = greenSlime;
        colChecker.checkEntity(fireball,gp.monster);
        assertEquals(colChecker.checkEntity(fireball,gp.monster),0);
    }
    @Test
    void should_return_false_on_projectile_hit_monster_checkEntity() {
        GamePanel gp = GamePanel.getInstance();
        GreenSlime greenSlime = new GreenSlime(gp);
        OBJ_Fireball fireball = new OBJ_Fireball(gp);
        CollisionChecker colChecker = new CollisionChecker(gp);
        greenSlime.worldX = gp.tileSize * 24;
        greenSlime.worldY = gp.tileSize * 24;
        fireball.worldX = gp.tileSize * 25;
        fireball.worldY = gp.tileSize * 25;
        Arrays.fill( gp.monster[0], null);
        gp.monster[0][0] = greenSlime;
        colChecker.checkEntity(fireball,gp.monster);
        assertEquals(colChecker.checkEntity(fireball,gp.monster),999);
    }

    @Test
    void should_return_true_on_monster_hit_player_checkPlayer() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        CollisionChecker colChecker = new CollisionChecker(gp);
        GreenSlime greenSlime = new GreenSlime(gp);
        Player player = new Player(gp,keyH);
        player.worldX = gp.tileSize * 24;
        player.worldY = gp.tileSize * 24;
        greenSlime.worldX = gp.tileSize * 24;
        greenSlime.worldY = gp.tileSize * 24;
        Arrays.fill( gp.monster[0], null);
        gp.monster[0][0] = greenSlime;
        assertTrue(colChecker.checkPlayer(greenSlime));
    }
    @Test
    void should_return_false_on_monster_hit_player_checkPlayer() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        CollisionChecker colChecker = new CollisionChecker(gp);
        GreenSlime greenSlime = new GreenSlime(gp);
        Player player = new Player(gp,keyH);
        player.worldX = gp.tileSize * 24;
        player.worldY = gp.tileSize * 24;
        greenSlime.worldX = gp.tileSize * 25;
        greenSlime.worldY = gp.tileSize * 25;
        Arrays.fill( gp.monster[0], null);
        gp.monster[0][0] = greenSlime;
        assertFalse(colChecker.checkPlayer(greenSlime));
    }
}