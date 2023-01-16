package test.object;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Door;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OBJ_DoorTest {

    @Test
    public void testOBJ_DoorConstructor() {
        GamePanel gp = mock(GamePanel.class);
        Entity door = new OBJ_Door(gp);
        assertTrue(door.collision);
        assertEquals(door.name,"Door");
        assertNotEquals(door.down1,null);
    }

}