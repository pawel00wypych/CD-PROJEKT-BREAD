package test.monster;

import main.GamePanel;
import monster.RedSlime;
import object.OBJ_Fireball;
import object.OBJ_Rock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RedSlimeTest {

    @Test
    void good_Param_Equals_Constructor() {
        GamePanel gp = mock(GamePanel.class);
        RedSlime redSlime = new RedSlime(gp);
        assertEquals(2, redSlime.type);
        assertEquals("Red Slime", redSlime.name);
        assertEquals(2, redSlime.defaultSpeed);
        assertEquals(2, redSlime.speed);
        assertEquals(7, redSlime.maxLife);
        assertEquals(7, redSlime.life);
        assertEquals(6, redSlime.attack);
        assertEquals(2, redSlime.defence);
        assertEquals(3, redSlime.exp);
        assertTrue(redSlime.projectile instanceof OBJ_Rock);
    }

    @Test
    void bad_Param_NotEqual_Constructor() {
        GamePanel gp = mock(GamePanel.class);
        RedSlime redSlime = new RedSlime(gp);
        assertNotEquals(1, redSlime.type);
        assertNotEquals("Slime", redSlime.name);
        assertNotEquals(15, redSlime.defaultSpeed);
        assertFalse(-1 >= redSlime.speed);
        assertFalse(0 >= redSlime.life);
        assertFalse(0 >= redSlime.maxLife);
        assertFalse(-1 >= redSlime.attack);
        assertFalse(-1 >= redSlime.defence);
        assertFalse(0 > redSlime.exp);
        assertFalse(redSlime.projectile instanceof OBJ_Fireball);
    }

    @Test
    void getImage_NotEqual_Null() {
        GamePanel gp = mock(GamePanel.class);
        RedSlime redSlime = new RedSlime(gp);
        redSlime.getImage();
        assertNotEquals(null, redSlime.up1);
        assertNotEquals(null, redSlime.up2);
        assertNotEquals(null, redSlime.left1);
        assertNotEquals(null, redSlime.left2);
        assertNotEquals(null, redSlime.right1);
        assertNotEquals(null, redSlime.right2);
        assertNotEquals(null, redSlime.down1);
        assertNotEquals(null, redSlime.down2);
    }


    @Test
    void good_Param_Equal_damageReaction() {
        GamePanel gp = mock(GamePanel.class);
        RedSlime redSlime = new RedSlime(gp);

        redSlime.damageReaction();

        assertEquals(0, redSlime.actionLockCounter);
    }


    @Test
    void good_Param_Equal_Clone() {
        GamePanel gp = mock(GamePanel.class);
        RedSlime redSlime1 = new RedSlime(gp);
        redSlime1.type = 3;
        redSlime1.name = "White Slime";
        redSlime1.defaultSpeed = 2;
        redSlime1.speed = 2;
        redSlime1.maxLife = 10;
        redSlime1.life = 10;
        redSlime1.attack = 8;
        redSlime1.defence = 2;
        redSlime1.exp = 4;
        RedSlime redSlime2 = redSlime1.clone();
        assertEquals(3, redSlime2.type);
        assertEquals("White Slime", redSlime2.name);
        assertEquals(2, redSlime2.defaultSpeed);
        assertEquals(2, redSlime2.speed);
        assertEquals(10, redSlime2.maxLife);
        assertEquals(10, redSlime2.life);
        assertEquals(8, redSlime2.attack);
        assertEquals(2, redSlime2.defence);
        assertEquals(4, redSlime2.exp);
    }

}