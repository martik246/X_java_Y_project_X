package utils.faceit;

import org.json.simple.JSONObject;
import utils.json.JSONParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FaceitApi{

    private static final JSONParser JSON_PARSER = new JSONParser();
    private static final String FACEIT_API_KEY = "64565ab0-d87f-4b0a-abd4-1dfcc3583cf6";
    private static final String FACEIT_ID = "d2c9244d-dcbc-4ed6-9a20-7edf257d061a";

    private static final String BASE_URL = "https://open.faceit.com/data";

    private static final String VERSION_ONE = "v1";
    private static final String VERSION_TWO = "v2";
    private static final String VERSION_THREE = "v3";
    private static final String VERSION_FOUR = "v4";

    private static final String PLAYER = "players";
    private static final String STATS = "players/{player_id}/stats/csgo";


    private static final String GAME = "game_id=csgo";
    private static final String NICKNAME = "nickname=";
    private static final String GAME_PLAYER_ID = "game_player_id=";

    private static String get(String version, String method, String parameters) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Bearer " + FACEIT_API_KEY)
                .uri(getURI(version, method, parameters))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private static URI getURI(String version, String method, String parameters) {
        try {
            return new URI(String.format("%s/%s/%s%s", BASE_URL, version, method, parameters));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public JSONObject getPlayerByNickname(String nickname) throws IOException, InterruptedException {
        String parameters = String.format("?%s%s", NICKNAME, nickname);
        String request = get(VERSION_FOUR, PLAYER, parameters);

        return JSON_PARSER.deserializeObject(request);
    }

    public JSONObject getPlayerById(String id) throws IOException, InterruptedException {
        String parameters = String.format("/%s", id);
        String request = get(VERSION_FOUR, PLAYER, parameters);

        return JSON_PARSER.deserializeObject(request);
    }

    public JSONObject getPlayersStats(String nickname) throws IOException, InterruptedException {
        String playersId = getPlayersId(nickname);
        String request = get(VERSION_FOUR, STATS.replace("{player_id}", playersId), "");

        return JSON_PARSER.deserializeObject(request);
    }

    private String getPlayersId(String nickname) {
        try {
            JSONObject player = getPlayerByNickname(nickname);
            return (String) player.get("player_id");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
