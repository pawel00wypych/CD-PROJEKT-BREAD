package test.entity;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;
import monster.GreenSlime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;

public class PlayerTest {

    @Test
    public void player_constructor_correct_default_values() {
        GamePanel gp = mock(GamePanel.class);
        KeyHandler keyH = mock(KeyHandler.class);
        Player player = new Player(gp,keyH);
        Assertions.assertEquals(player.type, 0);
        Assertions.assertEquals(player.worldX, gp.tileSize * 24);
        Assertions.assertEquals(player.worldY, gp.tileSize * 24);
        Assertions.assertEquals(player.direction, "down");
        Assertions.assertEquals(player.speed, 4);
        Assertions.assertEquals(player.coin, 0);
        Assertions.assertEquals(player.hasKey, 0);
        Assertions.assertEquals(player.exp, 0);
    }

    @Test
    public void player_checkLevelUp() {
        GamePanel gp = mock(GamePanel.class);
        KeyHandler keyH = mock(KeyHandler.class);
        Player player = new Player(gp,keyH);
        int tempLevel = player.level;
        player.exp += player.nextLevelExp;
        player.checkLevelUp();
        Assertions.assertEquals(player.level, tempLevel+1);
    }
    @Test
    public void player_should_damage_Monster() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        Player player = new Player(gp,keyH);
        GreenSlime greenSlime = new GreenSlime(gp);
        Arrays.fill( gp.monster[0], null);
        gp.monster[0][0] = greenSlime;
        int monsterHP = greenSlime.life;
        player.damageMonster(0,player.getAttack());
        Assertions.assertTrue(greenSlime.life<monsterHP);
    }
    @Test
    public void player_should_not_damage_invincible_Monster() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        Player player = new Player(gp,keyH);
        GreenSlime greenSlime = new GreenSlime(gp);
        Arrays.fill( gp.monster[0], null);
        gp.monster[0][0] = greenSlime;
        int monsterHP = greenSlime.life;
        greenSlime.invincible = true;
        player.damageMonster(0,player.getAttack());
        Assertions.assertFalse(greenSlime.life<monsterHP);
    }
    @Test
    public void player_should_take_damage_contactMonster() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        Player player = new Player(gp,keyH);
        GreenSlime greenSlime = new GreenSlime(gp);
        Arrays.fill( gp.monster[0], null);
        gp.monster[0][0] = greenSlime;
        int tempPlayerHP = player.life;
        player.contactMonster(0);
        Assertions.assertTrue(player.life<tempPlayerHP);
    }
    @Test
    public void player_should_not_take_damage_contactMonster_when_invincible() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        Player player = new Player(gp,keyH);
        GreenSlime greenSlime = new GreenSlime(gp);
        Arrays.fill( gp.monster[0], null);
        gp.monster[0][0] = greenSlime;
        int tempPlayerHP = player.life;
        player.invincible = true;
        player.contactMonster(0);
        Assertions.assertFalse(player.life<tempPlayerHP);
    }
    @Test
    public void player_should_return_correct_attack_value() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        Player player = new Player(gp,keyH);
        int correctAttack = player.strength * player.currentWeapon.attackValue;
        Assertions.assertEquals(player.getAttack(), correctAttack);
    }
    @Test
    public void player_should_return_correct_defence_value() {
        GamePanel gp = GamePanel.getInstance();
        KeyHandler keyH = mock(KeyHandler.class);
        Player player = new Player(gp,keyH);
        int correctDefence= player.dexterity * player.currentShield.defenceValue;
        Assertions.assertEquals(player.getDefence(), correctDefence);
    }
    @Test
    public void player_should_restoreLifeAndMana_correct_values() {
        GamePanel gp = mock(GamePanel.class);
        KeyHandler keyH = mock(KeyHandler.class);
        Player player = new Player(gp,keyH);
        player.restoreLifeAndMana();
        Assertions.assertEquals(player.mana, player.maxMana);
        Assertions.assertEquals(player.life, player.maxLife);
        Assertions.assertEquals(player.speed, 4);
        Assertions.assertEquals(player.coin, 0);
        Assertions.assertEquals(player.hasKey, 0);
        Assertions.assertEquals(player.exp, 0);
    }
    @Test
    public void player_should_go_to_default_position() {
        GamePanel gp = mock(GamePanel.class);
        KeyHandler keyH = mock(KeyHandler.class);
        Player player = new Player(gp,keyH);
        player.worldY = 34;
        player.worldX = 321;
        player.setDefaultPositions();
        Assertions.assertEquals(player.worldY, gp.tileSize*24);
        Assertions.assertEquals(player.worldX, gp.tileSize*24);
        Assertions.assertEquals(player.direction, "down");
    }
}
