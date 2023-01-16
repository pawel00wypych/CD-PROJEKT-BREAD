package test.object;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Fireball;
import org.junit.jupiter.api.Test;


import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OBJ_FireballTest {

    static GamePanel gp;
    static OBJ_Fireball fireBall;

    public static void setup() {
         gp = mock(GamePanel.class);
         fireBall = new OBJ_Fireball(gp);
    }

    @Test
    public void testOBJ_FireballConstructor() {
        setup();
        assertEquals(fireBall.speed,8);
        assertEquals(fireBall.maxLife,80);
        assertEquals(fireBall.attack,2);
        assertEquals(fireBall.useCost,1);
        assertEquals(fireBall.alive,false);
        assertEquals(fireBall.name,"Fireball");
    }


    @Test
    void getImage() {
        setup();
        fireBall.getImage();
        assertNotEquals(null, fireBall.up1);
        assertNotEquals(null, fireBall.up2);
        assertNotEquals(null, fireBall.left1);
        assertNotEquals(null, fireBall.left2);
        assertNotEquals(null, fireBall.right1);
        assertNotEquals(null, fireBall.right2);
        assertNotEquals(null, fireBall.down1);
        assertNotEquals(null, fireBall.down2);
    }

    @Test
    void haveResource() {
        setup();
        Entity user = mock(Entity.class);
        when(user.getMana()).thenReturn(5);

        assertTrue(fireBall.haveResource(user));

        when(user.getMana()).thenReturn(0);

        assertFalse(fireBall.haveResource(user));

    }

    @Test
    void subtractResource() {
        setup();
        Entity user = mock(Entity.class);
        when(user.getMana()).thenReturn(5);

        assertEquals(5, user.getMana());

        fireBall.subtractResource(user);

        assertEquals(-1, user.mana);
    }

    @Test
    void getParticleColor() {
        setup();
        assertEquals(new Color(240, 50, 0), fireBall.getParticleColor());
    }

    @Test
    void getParticleSize() {
        setup();
        assertEquals(8, fireBall.getParticleSize());
    }

    @Test
    void getParticleSpeed() {
        setup();
        assertEquals(1, fireBall.getParticleSpeed());
    }

    @Test
    void getParticleMaxLife() {
        setup();
        assertEquals(20, fireBall.getParticleMaxLife());
    }
}