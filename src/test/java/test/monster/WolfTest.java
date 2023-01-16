package test.monster;

import main.GamePanel;
import monster.Sunflower;
import monster.Wolf;
import object.OBJ_Fireball;
import object.OBJ_Rock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class WolfTest {
    @Test
    void good_Param_Equals_Constructor() {
        GamePanel gp = mock(GamePanel.class);
        Wolf wolf = new Wolf(gp);
        assertEquals(2, wolf.type);
        assertEquals("Wolf", wolf.name);
        assertEquals(2, wolf.defaultSpeed);
        assertEquals(2, wolf.speed);
        assertEquals(14, wolf.maxLife);
        assertEquals(14, wolf.life);
        assertEquals(8, wolf.attack);
        assertEquals(3, wolf.defence);
        assertEquals(5, wolf.exp);
        assertFalse(wolf.projectile instanceof OBJ_Rock);
    }

    @Test
    void bad_Param_NotEqual_Constructor() {
        GamePanel gp = mock(GamePanel.class);
        Wolf wolf = new Wolf(gp);
        assertNotEquals(1, wolf.type);
        assertNotEquals("wol", wolf.name);
        assertNotEquals(15, wolf.defaultSpeed);
        assertFalse(-1 >= wolf.speed);
        assertFalse(0 >= wolf.life);
        assertFalse(0 >= wolf.maxLife);
        assertFalse(-1 >= wolf.attack);
        assertFalse(-1 >= wolf.defence);
        assertFalse(0 > wolf.exp);
        assertFalse(wolf.projectile instanceof OBJ_Fireball);
    }

    @Test
    void getImage_NotEqual_Null() {
        GamePanel gp = mock(GamePanel.class);
        Wolf wolf = new Wolf(gp);
        wolf.getImage();
        assertNotEquals(null, wolf.up1);
        assertNotEquals(null, wolf.up2);
        assertNotEquals(null, wolf.left1);
        assertNotEquals(null, wolf.left2);
        assertNotEquals(null, wolf.right1);
        assertNotEquals(null, wolf.right2);
        assertNotEquals(null, wolf.down1);
        assertNotEquals(null, wolf.down2);
    }

    @Test
    void good_Param_Equal_damageReaction() {
        GamePanel gp = mock(GamePanel.class);
        Wolf wolf = new Wolf(gp);

        wolf.damageReaction();

        assertEquals(0, wolf.actionLockCounter);
    }


    @Test
    void good_Param_Equal_Clone() {
        GamePanel gp = mock(GamePanel.class);
        Wolf wolf1 = new Wolf(gp);
        wolf1.type = 3;
        wolf1.name = "Blue Wolf";
        wolf1.defaultSpeed = 2;
        wolf1.speed = 2;
        wolf1.maxLife = 10;
        wolf1.life = 10;
        wolf1.attack = 8;
        wolf1.defence = 2;
        wolf1.exp = 4;
        Wolf wolf2 = wolf1.clone();
        assertEquals(3, wolf2.type);
        assertEquals("Blue Wolf", wolf2.name);
        assertEquals(2, wolf2.defaultSpeed);
        assertEquals(2, wolf2.speed);
        assertEquals(10, wolf2.maxLife);
        assertEquals(10, wolf2.life);
        assertEquals(8, wolf2.attack);
        assertEquals(2, wolf2.defence);
        assertEquals(4, wolf2.exp);
    }
}
