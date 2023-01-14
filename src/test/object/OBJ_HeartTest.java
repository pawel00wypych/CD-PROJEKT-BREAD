package object;

import entity.Entity;
import main.GamePanel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OBJ_HeartTest {

    @Test
    public void testManaCrystalConstructor() {
        GamePanel gp = mock(GamePanel.class);
        Entity heart = new OBJ_Heart(gp);
        assertEquals(heart.type,3);
        assertEquals(heart.value,2);
        assertEquals(heart.name,"Heart");
        assertNotEquals(heart.down1,null);
        assertNotEquals(heart.image,null);
        assertNotEquals(heart.image2,null);
        assertNotEquals(heart.image3,null);
    }

    @Test
    public void use_ShouldIncrease_Heart() {
        Entity entity = mock(Entity.class);
        GamePanel gp = mock(GamePanel.class);
        Entity entities = new OBJ_Heart(gp);
        int initialhearts = entity.life;
        entities.use(entity);
        assertEquals(initialhearts + 2, entity.life);
    }

    @Test
    public void use_ShouldPlay_SoundEffect() {
        Entity entity = mock(Entity.class);
        GamePanel gp = mock(GamePanel.class);
        Entity entities = new OBJ_Heart(gp);
        entities.use(entity);
        verify(gp, Mockito.times(1)).playSE(2);
    }
}