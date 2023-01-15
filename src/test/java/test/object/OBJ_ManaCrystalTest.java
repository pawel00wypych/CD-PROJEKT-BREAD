package test.object;

import entity.Entity;
import main.GamePanel;
import object.OBJ_ManaCrystal;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OBJ_ManaCrystalTest {

    @Test
    public void testManaCrystalConstructor() {
        GamePanel gp = mock(GamePanel.class);
        Entity crystal = new OBJ_ManaCrystal(gp);
        assertEquals(crystal.type,3);
        assertEquals(crystal.value,2);
        assertEquals(crystal.name,"Mana Crystal");
        assertNotEquals(crystal.down1,null);
        assertNotEquals(crystal.image,null);
        assertNotEquals(crystal.image2,null);
    }

    @Test
    public void use_ShouldIncrease_Mana() {
        Entity entity = mock(Entity.class);
        GamePanel gp = mock(GamePanel.class);
        Entity entities = new OBJ_ManaCrystal(gp);
        int initialmana = entity.mana;
        entities.use(entity);
        assertEquals(initialmana + 2, entity.mana);
    }

    @Test
    public void use_ShouldPlay_SoundEffect() {
        Entity entity = mock(Entity.class);
        GamePanel gp = mock(GamePanel.class);
        Entity entities = new OBJ_ManaCrystal(gp);
        entities.use(entity);
        verify(gp, Mockito.times(1)).playSE(2);
    }

}