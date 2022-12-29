package models;

import java.util.ArrayList;

public class Player {
    private final String nickname;
    private final String faceit_id;
    private final String country;
    private final double kd;
    private final int matches;
    private final ArrayList<MapStat> mapStats = new ArrayList<>();

    public Player(String nickname, String faceit_id, String country, double kd, int matches) {
        this.nickname = nickname;
        this.faceit_id = faceit_id;
        this.country = country;
        this.kd = kd;
        this.matches = matches;
    }

    public ArrayList<MapStat> getMapStats() {
        return mapStats;
    }

    public void addMapStat(MapStat stat) {
        mapStats.add(stat);
    }

    public String getNickname() {
        return nickname;
    }

    public String getFaceit_id() {
        return faceit_id;
    }

    public String getCountry() {
        return country;
    }

    public double getKd() {
        return kd;
    }

    public int getMatches() {
        return matches;
    }
}
