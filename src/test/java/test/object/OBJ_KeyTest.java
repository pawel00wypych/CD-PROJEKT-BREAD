package test.object;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Key;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OBJ_KeyTest {

    @Test
    public void testOBJ_BootsConstructor() {
        GamePanel gp = mock(GamePanel.class);
        Entity key = new OBJ_Key(gp);
        assertEquals(key.type,3);
        assertEquals(key.name,"Key");
        assertNotEquals(key.down1,null);
    }

}