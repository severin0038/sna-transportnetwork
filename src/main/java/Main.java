import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class Main {

    public static String inputFolder = "src/main/resources/data";

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {

        Application application = new Application();

        application.readAllFilesfromFolderAndGenerateApplicationPerFile(inputFolder);

        System.out.println("alle Files abgearbeitet");
        application.exportConnectionsToCSV("2019_01_01-31.csv");
        System.out.println("blabla");
        application.addGeolocationToTrainstations("stations_geolocation_v07_02.csv");
        System.out.println("blabla2");
        application.trainStationsToNodesList("trainStations.csv");
        System.out.println("geschafft");

    }
}
