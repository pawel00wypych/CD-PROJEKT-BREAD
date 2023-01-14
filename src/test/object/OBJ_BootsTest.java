package object;

import entity.Entity;
import main.GamePanel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OBJ_BootsTest {

    @Test
    public void testOBJ_BootsConstructor() {
        GamePanel gp = mock(GamePanel.class);
        Entity boots = new OBJ_Boots(gp);
        assertEquals(boots.type,3);
        assertEquals(boots.name,"Boots");
        assertNotEquals(boots.down1,null);
    }

    @Test
    public void use_ShouldIncrease_Speed() {
        Entity entity = mock(Entity.class);
        GamePanel gp = mock(GamePanel.class);
        Entity entities = new OBJ_Boots(gp);
        int initialSpeed = entity.speed;
        entities.use(entity);
        assertEquals(initialSpeed + 2, entity.speed);
    }

    @Test
    public void use_ShouldPlay_SoundEffect() {
        Entity entity = mock(Entity.class);
        GamePanel gp = mock(GamePanel.class);
        Entity entities = new OBJ_Boots(gp);
        entities.use(entity);
        verify(gp, Mockito.times(1)).playSE(2);
    }
}