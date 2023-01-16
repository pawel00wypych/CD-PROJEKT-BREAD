package test.monster;

import main.GamePanel;
import monster.SandSlime;
import object.OBJ_Fireball;
import object.OBJ_Rock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SandSlimeTest {

    @Test
    void good_Param_Equals_Constructor() {
        GamePanel gp = mock(GamePanel.class);
        SandSlime sandSlime = new SandSlime(gp);
        assertEquals(2, sandSlime.type);
        assertEquals("Sand Slime", sandSlime.name);
        assertEquals(1, sandSlime.defaultSpeed);
        assertEquals(1, sandSlime.speed);
        assertEquals(12, sandSlime.maxLife);
        assertEquals(12, sandSlime.life);
        assertEquals(8, sandSlime.attack);
        assertEquals(5, sandSlime.defence);
        assertEquals(5, sandSlime.exp);
        assertTrue(sandSlime.projectile instanceof OBJ_Rock);
    }

    @Test
    void bad_Param_NotEqual_Constructor() {
        GamePanel gp = mock(GamePanel.class);
        SandSlime sandSlime = new SandSlime(gp);
        assertNotEquals(1, sandSlime.type);
        assertNotEquals("Slime", sandSlime.name);
        assertNotEquals(15, sandSlime.defaultSpeed);
        assertFalse(-1 >= sandSlime.speed);
        assertFalse(0 >= sandSlime.life);
        assertFalse(0 >= sandSlime.maxLife);
        assertFalse(-1 >= sandSlime.attack);
        assertFalse(-1 >= sandSlime.defence);
        assertFalse(0 > sandSlime.exp);
        assertFalse(sandSlime.projectile instanceof OBJ_Fireball);
    }

    @Test
    void getImage_NotEqual_Null() {
        GamePanel gp = mock(GamePanel.class);
        SandSlime sandSlime = new SandSlime(gp);
        sandSlime.getImage();
        assertNotEquals(null, sandSlime.up1);
        assertNotEquals(null, sandSlime.up2);
        assertNotEquals(null, sandSlime.left1);
        assertNotEquals(null, sandSlime.left2);
        assertNotEquals(null, sandSlime.right1);
        assertNotEquals(null, sandSlime.right2);
        assertNotEquals(null, sandSlime.down1);
        assertNotEquals(null, sandSlime.down2);
    }


    @Test
    void good_Param_Equal_damageReaction() {
        GamePanel gp = mock(GamePanel.class);
        SandSlime sandSlime = new SandSlime(gp);

        sandSlime.damageReaction();

        assertEquals(0, sandSlime.actionLockCounter);
    }


    @Test
    void good_Param_Equal_Clone() {
        GamePanel gp = mock(GamePanel.class);
        SandSlime sandSlime1 = new SandSlime(gp);
        sandSlime1.type = 3;
        sandSlime1.name = "Black Slime";
        sandSlime1.defaultSpeed = 5;
        sandSlime1.speed = 5;
        sandSlime1.maxLife = 10;
        sandSlime1.life = 10;
        sandSlime1.attack = 11;
        sandSlime1.defence = 2;
        sandSlime1.exp = 4;
        SandSlime sandSlime2 = sandSlime1.clone();
        assertEquals(3, sandSlime2.type);
        assertEquals("Black Slime", sandSlime2.name);
        assertEquals(5, sandSlime2.defaultSpeed);
        assertEquals(5, sandSlime2.speed);
        assertEquals(10, sandSlime2.maxLife);
        assertEquals(10, sandSlime2.life);
        assertEquals(11, sandSlime2.attack);
        assertEquals(2, sandSlime2.defence);
        assertEquals(4, sandSlime2.exp);
    }

}