package models;

import java.util.ArrayList;

public class PlayersCollection {
    private static final ArrayList<Player> players = new ArrayList<>();

    public static void addPlayer(Player player){
        players.add(player);
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }
}
