package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;

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

}
