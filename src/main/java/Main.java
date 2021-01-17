import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class Main {

    public static String inputFolder = "src/main/resources/data";

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {

        Application application = new Application();

        application.readAllFilesfromFolderAndGenerateApplicationPerFile(inputFolder);

        application.exportConnectionsToCSV("2019_04_Monday_5D.csv");
        application.addGeolocationToTrainstations("stations_geolocation_v07_05.csv");
        application.trainStationsToNodesList("trainStations.csv");

    }
}