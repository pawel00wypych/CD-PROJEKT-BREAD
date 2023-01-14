package object;

import entity.Entity;
import main.GamePanel;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OBJ_RockTest {

    static GamePanel gp;
    static OBJ_Rock rock;

    public static void setup() {
        gp = mock(GamePanel.class);
        rock = new OBJ_Rock(gp);
    }

    @Test
    public void testOBJ_FireballConstructor() {
        setup();
        assertNotEquals(rock.solidArea,null);
        assertEquals(rock.type,1);
        assertEquals(rock.speed,8);
        assertEquals(rock.maxLife,80);
        assertEquals(rock.attack,2);
        assertEquals(rock.useCost,1);
        assertFalse(rock.alive);
        assertEquals(rock.name,"Rock");
    }


    @Test
    void getImage() {
        setup();
        rock.getImage();
        assertNotEquals(null, rock.up1);
        assertNotEquals(null, rock.up2);
        assertNotEquals(null, rock.left1);
        assertNotEquals(null, rock.left2);
        assertNotEquals(null, rock.right1);
        assertNotEquals(null, rock.right2);
        assertNotEquals(null, rock.down1);
        assertNotEquals(null, rock.down2);
    }

    @Test
    void haveResource() {
        setup();
        Entity user = mock(Entity.class);
        when(user.getAmmo()).thenReturn(5);

        assertTrue(rock.haveResource(user));

        when(user.getAmmo()).thenReturn(0);

        assertFalse(rock.haveResource(user));

    }

    @Test
    void subtractResource() {
        setup();
        Entity user = mock(Entity.class);
        when(user.getAmmo()).thenReturn(5);

        assertEquals(5, user.getAmmo());

        rock.subtractResource(user);

        assertEquals(-1, user.ammo);
    }

    @Test
    void getParticleColor() {
        setup();
        assertEquals(new Color(40, 50, 0), rock.getParticleColor());
    }

    @Test
    void getParticleSize() {
        setup();
        assertEquals(6, rock.getParticleSize());
    }

    @Test
    void getParticleSpeed() {
        setup();
        assertEquals(1, rock.getParticleSpeed());
    }

    @Test
    void getParticleMaxLife() {
        setup();
        assertEquals(15, rock.getParticleMaxLife());
    }
}