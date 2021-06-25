package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void ShotOutofBound() throws IOException {
        Game g = new Game(null);
        Assertions.assertEquals(Game.FireResult.out,g.ShotAt("Kappa"),"Shot is out of baord");
        Assertions.assertEquals(Game.FireResult.out,g.ShotAt("Z9"),"Shot is out of baord");
        Assertions.assertEquals(Game.FireResult.out,g.ShotAt("A99"),"Shot is out of baord");
        Assertions.assertEquals(Game.FireResult.out,g.ShotAt("A0"),"Shot is out of baord");
        Assertions.assertEquals(Game.FireResult.out,g.ShotAt("b11"),"Shot is out of baord");
    }

    @Test
    void Fireback() throws IOException {
        MyServer s = new MyServer("1235");
        Game g = new Game(s);
        g.FireBack();
    }
    @Test
    void GoodShot() throws IOException {
        boolean stop = false;
        Game g = new Game(null);
        int b= 0;
        while (b < 100) {
            int[] coord = g.yourboard.get(0).get(0);
            if (g.yourboard.get(0).size() == 1)
                Assertions.assertEquals(Game.FireResult.sunk, g.ShotAt(String.format("%s%s", (char) (coord[1] + 'A'), coord[0] + 1)), "Shot is a sunk");
            else
                Assertions.assertEquals(Game.FireResult.hit, g.ShotAt(String.format("%s%s", (char) (coord[1] + 'A'), coord[0] + 1)), "Shot is a hit");
            if (g.yourboard.size() == 0)
                break;
            b++;
        }
        for (int x = 1; x < 11; x++)
        {
            for (int y = 0; y < 10; y++)
            {
            Assertions.assertEquals(Game.FireResult.miss, g.ShotAt(String.format("%s%s", (char) (y + 'A'), x )), "Shot is a miss");
            }
        }
    }
}
