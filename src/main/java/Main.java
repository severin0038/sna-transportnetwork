import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class Main {

    public static String inputFolder = "src/main/resources/data";

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {

        Application application = new Application();

        application.readAllFilesfromFolderAndGenerateApplicationPerFile(inputFolder);

        application.exportConnectionsToCSV("2020_04_01-30.csv");
        application.addGeolocationToTrainstations("stations_geolocation_v07_02.csv");
        application.trainStationsToNodesList("trainStations.csv");

    }
}
