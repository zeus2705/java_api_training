package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Annihilation {
    final Game game;

    final List<int[]> allshots =new ArrayList<>();

    public Annihilation(Game g)
    {
        game = g;
        for (int i = 0; i < 10; i++) { for (int j = 0; j < 10; j++) {
            allshots.add(new int[]{i,j});
        }}
    }


    public int[] GetCellToShoot(){
        return allshots.remove(game.ai.rng.nextInt(allshots.size()));
    }

    public Game.FireResult Shoot(int[] coord) {
        String cell = String.format("%s%s", (char) (coord[1] + 'A'), coord[0] + 1);
        try {
            String url = String.format("%s/api/game/fire*?cell=%s", game.server.target[0], cell);
            HttpClient cli = HttpClient.newHttpClient();
            HttpRequest requetefire = HttpRequest.newBuilder()
                .uri(new URI(url)).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").GET()
                .build();
            HttpResponse<String> response = cli.send(requetefire, HttpResponse.BodyHandlers.ofString());
            if (response.body().contains("src=\"https://http.cat/404\""))
                return  EndGame();
            return game.server.handler.jsck.ValidateFireRequest(response.body(), game);
        } catch (Exception e) {return Game.FireResult.out; }
    }

    public Game.FireResult  EndGame(){
        game.ingame[0] = false;
        System.out.println(String.format("The game has ended %s won", game.yourboard.size() > 0 ? "you" : "opponent"));
        return Game.FireResult.miss;
    }
}
