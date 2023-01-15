package test.monster;

import main.GamePanel;
import monster.Sunflower;
import object.OBJ_Fireball;
import object.OBJ_Rock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SunflowerTest {

    @Test
    void good_Param_Equals_Constructor() {
        GamePanel gp = mock(GamePanel.class);
        Sunflower sunflower = new Sunflower(gp);
        assertEquals(2, sunflower.type);
        assertEquals("Sunflower", sunflower.name);
        assertEquals(1, sunflower.defaultSpeed);
        assertEquals(1, sunflower.speed);
        assertEquals(4, sunflower.maxLife);
        assertEquals(4, sunflower.life);
        assertEquals(3, sunflower.attack);
        assertEquals(0, sunflower.defence);
        assertEquals(2, sunflower.exp);
        assertTrue(sunflower.projectile instanceof OBJ_Rock);
    }

    @Test
    void bad_Param_NotEqual_Constructor() {
        GamePanel gp = mock(GamePanel.class);
        Sunflower sunflower = new Sunflower(gp);
        assertNotEquals(1, sunflower.type);
        assertNotEquals("Sun", sunflower.name);
        assertNotEquals(15, sunflower.defaultSpeed);
        assertFalse(-1 >= sunflower.speed);
        assertFalse(0 >= sunflower.life);
        assertFalse(0 >= sunflower.maxLife);
        assertFalse(-1 >= sunflower.attack);
        assertFalse(-1 >= sunflower.defence);
        assertFalse(0 > sunflower.exp);
        assertFalse(sunflower.projectile instanceof OBJ_Fireball);
    }

    @Test
    void getImage_NotEqual_Null() {
        GamePanel gp = mock(GamePanel.class);
        Sunflower sunflower = new Sunflower(gp);
        sunflower.getImage();
        assertNotEquals(null, sunflower.up1);
        assertNotEquals(null, sunflower.up2);
        assertNotEquals(null, sunflower.left1);
        assertNotEquals(null, sunflower.left2);
        assertNotEquals(null, sunflower.right1);
        assertNotEquals(null, sunflower.right2);
        assertNotEquals(null, sunflower.down1);
        assertNotEquals(null, sunflower.down2);
    }

    @Test
    void good_Param_Equal_damageReaction() {
        GamePanel gp = mock(GamePanel.class);
        Sunflower sunflower = new Sunflower(gp);

        sunflower.damageReaction();

        assertEquals(0, sunflower.actionLockCounter);
    }


    @Test
    void good_Param_Equal_Clone() {
        GamePanel gp = mock(GamePanel.class);
        Sunflower sunflower1 = new Sunflower(gp);
        sunflower1.type = 3;
        sunflower1.name = "Blue Sunflower";
        sunflower1.defaultSpeed = 2;
        sunflower1.speed = 2;
        sunflower1.maxLife = 10;
        sunflower1.life = 10;
        sunflower1.attack = 8;
        sunflower1.defence = 2;
        sunflower1.exp = 4;
        Sunflower sunflower2 = sunflower1.clone();
        assertEquals(3, sunflower2.type);
        assertEquals("Blue Sunflower", sunflower2.name);
        assertEquals(2, sunflower2.defaultSpeed);
        assertEquals(2, sunflower2.speed);
        assertEquals(10, sunflower2.maxLife);
        assertEquals(10, sunflower2.life);
        assertEquals(8, sunflower2.attack);
        assertEquals(2, sunflower2.defence);
        assertEquals(4, sunflower2.exp);
    }
}