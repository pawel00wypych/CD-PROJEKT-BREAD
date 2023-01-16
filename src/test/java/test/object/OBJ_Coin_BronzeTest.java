package test.object;

import entity.Entity;
import entity.Player;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OBJ_Coin_BronzeTest {

    @Test
    public void testOBJ_Coin_BronzeConstructor() {
        GamePanel gp = mock(GamePanel.class);
        Entity coin = new OBJ_Coin_Bronze(gp);
        assertEquals(coin.type,3);
        assertEquals(coin.value,1);
        assertEquals(coin.name,"Bronze Coin");
        assertNotEquals(coin.down1,null);
    }

    @Test
    public void use_ShouldIncrease_coin() {

        GamePanel gp = mock(GamePanel.class);
        Player player = mock(Player.class);
        gp.player = player;
        Entity entities = new OBJ_Coin_Bronze(gp);
        int initialCoin = player.coin;
        entities.use(player);
        assertEquals(initialCoin + 1, player.coin);
    }

    @Test
    public void use_ShouldPlay_SoundEffect() {

        GamePanel gp = mock(GamePanel.class);
        Player player = mock(Player.class);
        gp.player = player;
        Entity entities = new OBJ_Coin_Bronze(gp);
        entities.use(player);
        verify(gp, Mockito.times(1)).playSE(1);
    }
}