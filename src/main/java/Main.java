import models.MapStat;
import models.Player;
import models.PlayersCollection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.csv.CSVWriterService;
import utils.faceit.FaceitApi;
import utils.graphs.GraphsService;

import java.io.IOException;

public class Main {

    public static void main(final String[] args) throws IOException, InterruptedException {
        CSVWriterService csvWriterService = new CSVWriterService("C:\\Users\\Никита\\Downloads\\test.csv");
        csvWriterService.writeCSV("NYX");

        GraphsService.main(new String[]{});
    }
}