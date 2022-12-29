package utils.csv;

import com.opencsv.CSVWriter;
import models.MapStat;
import models.Player;
import models.PlayersCollection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.faceit.FaceitApi;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriterService {
    private final String FILE_NAME;

    public CSVWriterService(String fileName){
        FILE_NAME = fileName;
    }

    public void writeCSV(String nickname) {
        FaceitApi faceitApi = new FaceitApi();
        Player playerToWrite;
        try {
            JSONObject player = faceitApi.getPlayerByNickname(nickname);
            JSONObject stats = faceitApi.getPlayersStats(nickname);
            JSONArray statsByMap = (JSONArray) stats.get("segments");

            playerToWrite = new Player(
                    (String)player.get("nickname"),
                    (String)player.get("player_id"),
                    (String)player.get("country"),
                    Double.parseDouble((String)(((JSONObject)stats.get("lifetime")).get("Average K/D Ratio"))),
                    Integer.parseInt((String)(((JSONObject)stats.get("lifetime")).get("Matches")))
            );

            for (Object statByMap : statsByMap) {
                String mapName = (String)(((JSONObject) statByMap).get("label"));
                JSONObject jsonStatByMap = (JSONObject) (((JSONObject) statByMap).get("stats"));
                MapStat mapStat = new MapStat(
                        mapName,
                        Double.parseDouble((String)jsonStatByMap.get("Average Kills")),
                        Double.parseDouble((String)jsonStatByMap.get("Average K/D Ratio")),
                        Integer.parseInt((String)jsonStatByMap.get("Wins")),
                        Integer.parseInt((String)jsonStatByMap.get("Matches"))
                );
                playerToWrite.addMapStat(mapStat);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_NAME))){
            writer.writeNext(new String[] {
                    playerToWrite.getNickname(),
                    playerToWrite.getFaceit_id(),
                    playerToWrite.getCountry(),
                    String.valueOf(playerToWrite.getMatches()),
                    String.valueOf(playerToWrite.getKd())
            });
            writer.writeNext(new String[] {});
            writer.writeNext(new String[] {
                    "mapName",
                    "mapsPlayed",
                    "averageKills",
                    "kd",
                    "wins"
            });
            playerToWrite.getMapStats().forEach(stat -> {
                writer.writeNext(new String[] {
                        stat.getMapName(),
                        String.valueOf(stat.getMapsPlayed()),
                        String.valueOf(stat.getAverageKills()),
                        String.valueOf(stat.getKd()),
                        String.valueOf(stat.getWins())

                });
            });
            writer.writeNext(new String[] {

            });
            PlayersCollection.addPlayer(playerToWrite);
            System.out.println("Выгрузка прошла успешно");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
