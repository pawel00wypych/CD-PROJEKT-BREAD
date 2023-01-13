package monster;

import object.OBJ_Fireball;
import object.OBJ_Rock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import main.GamePanel;

class GreenSlimeTest {

    @Test
    void good_Param_Equals_Constructor() {
        GamePanel gp = mock(GamePanel.class);
        GreenSlime greenSlime = new GreenSlime(gp);
        assertEquals(2, greenSlime.type);
        assertEquals("Green Slime", greenSlime.name);
        assertEquals(1, greenSlime.defaultSpeed);
        assertEquals(1, greenSlime.speed);
        assertEquals(5, greenSlime.maxLife);
        assertEquals(5, greenSlime.life);
        assertEquals(4, greenSlime.attack);
        assertEquals(1, greenSlime.defence);
        assertEquals(2, greenSlime.exp);
        assertTrue(greenSlime.projectile instanceof OBJ_Rock);
    }

    @Test
    void bad_Param_NotEqual_Constructor() {
        GamePanel gp = mock(GamePanel.class);
        GreenSlime greenSlime = new GreenSlime(gp);
        assertNotEquals(1, greenSlime.type);
        assertNotEquals("Slime", greenSlime.name);
        assertNotEquals(15, greenSlime.defaultSpeed);
        assertFalse(-1 >= greenSlime.speed);
        assertFalse(0 >= greenSlime.life);
        assertFalse(0 >= greenSlime.maxLife);
        assertFalse(-1 >= greenSlime.attack);
        assertFalse(-1 >= greenSlime.defence);
        assertFalse(0 > greenSlime.exp);
        assertFalse(greenSlime.projectile instanceof OBJ_Fireball);
    }

    @Test
    void getImage_NotEqual_Null() {
        GamePanel gp = mock(GamePanel.class);
        GreenSlime greenSlime = new GreenSlime(gp);
        greenSlime.getImage();
        assertNotEquals(null, greenSlime.up1);
        assertNotEquals(null, greenSlime.up2);
        assertNotEquals(null, greenSlime.left1);
        assertNotEquals(null, greenSlime.left2);
        assertNotEquals(null, greenSlime.right1);
        assertNotEquals(null, greenSlime.right2);
        assertNotEquals(null, greenSlime.down1);
        assertNotEquals(null, greenSlime.down2);
    }


    @Test
    void good_Param_Equal_damageReaction() {
        GamePanel gp = mock(GamePanel.class);
        GreenSlime greenSlime = new GreenSlime(gp);

        greenSlime.damageReaction();

        assertEquals(0, greenSlime.actionLockCounter);
    }


    @Test
    void good_Param_Equal_Clone() {
        GamePanel gp = mock(GamePanel.class);
        GreenSlime greenSlime1 = new GreenSlime(gp);
        greenSlime1.type = 3;
        greenSlime1.name = "Blue Slime";
        greenSlime1.defaultSpeed = 2;
        greenSlime1.speed = 2;
        greenSlime1.maxLife = 10;
        greenSlime1.life = 10;
        greenSlime1.attack = 8;
        greenSlime1.defence = 2;
        greenSlime1.exp = 4;
        GreenSlime greenSlime2 = greenSlime1.clone();
        assertEquals(3, greenSlime2.type);
        assertEquals("Blue Slime", greenSlime2.name);
        assertEquals(2, greenSlime2.defaultSpeed);
        assertEquals(2, greenSlime2.speed);
        assertEquals(10, greenSlime2.maxLife);
        assertEquals(10, greenSlime2.life);
        assertEquals(8, greenSlime2.attack);
        assertEquals(2, greenSlime2.defence);
        assertEquals(4, greenSlime2.exp);
    }
}