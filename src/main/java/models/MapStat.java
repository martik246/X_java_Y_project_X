package models;

public class MapStat {
    private final String mapName;
    private final double averageKills;
    private final double kd;
    private final int wins;
    private final int mapsPlayed;

    public MapStat(String mapName, double averageKills, double kd, int wins, int mapsPlayed) {
        this.mapName = mapName;
        this.averageKills = averageKills;
        this.kd = kd;
        this.wins = wins;
        this.mapsPlayed = mapsPlayed;
    }

    public String getMapName() {
        return mapName;
    }

    public double getAverageKills() {
        return averageKills;
    }

    public double getKd() {
        return kd;
    }

    public int getWins() {
        return wins;
    }


    public int getMapsPlayed() {
        return mapsPlayed;
    }
}
