package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AnnihilationTest {
    @Test
    void BadArgument()
    {
        Annihilation a = new Annihilation(null);
        try {
            a.Shoot(new int[]{1,2});
            assertEquals(Game.FireResult.out,a.Shoot(new int[]{1,2}),"bad shot is");
        }catch (Exception e){}
    }

    @Test
    void EndGame() throws IOException {
        Game g = new Game( new MyServer("1234"));
        g.server.target[0] = "http://localhost:1234";
        Annihilation a = new Annihilation(g);
        a.Shoot(new int[]{1,1});
    }



}
