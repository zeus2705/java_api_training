package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import org.everit.json.schema.internal.RegexFormatValidator;

import java.io.IOException;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Game {
    final List<List<int[]>> yourboard;
    final boolean[] ingame;
    final AI ai;
    final MyServer server;
    final Annihilation shoot;

    final Pattern p = Pattern.compile("^[A-J](10|[1-9])$");

    enum FireResult {miss,hit,sunk,out}

    Game(MyServer server)
    {
        ingame = new boolean[]{false};
        ai = new AI();
        yourboard = ai.GenerateBoard();
        this.server = server;
        shoot = new Annihilation(this);
    }

    public FireResult ShotAt(String cell) throws IOException {
        if (!p.matcher(cell).find()) {return FireResult.out; }
        int y = (cell.charAt(0)- 'A');
        int x = Integer.parseInt(cell.split("[A-J]")[1]);
        for (List<int[]> coordship : yourboard) {for (int[] coord : coordship) {
            if (coord[0] == x && coord[1] == y){
                coordship.remove(coord);
                if (coordship.size() == 0){
                    yourboard.remove(coordship);
                    return FireResult.sunk;
                }
                return FireResult.hit;
        }}}
        return FireResult.miss;
    }

    public void FireBack() {
        int[] coord = shoot.GetCellToShoot();
        FireResult result = shoot.Shoot(coord);
        if (!ingame[0]) {
            System.out.println(String.format("The game has ended %s won", yourboard.size() > 0 ? "you" : "opponent"));
        }

    }
}
