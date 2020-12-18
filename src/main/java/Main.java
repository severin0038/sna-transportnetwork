import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {
        Application application = new Application();
        application.sbbDataSetToSnaGraph("2020-04-04_istdaten.csv", "2020-04-04_network.csv");
        application.sbbDataSetToSnaGraph("2019-04-06istdaten.csv", "2019-04-06_network.csv");
        application.trainStationsToNodesList("trainStations.csv");
    }
}
