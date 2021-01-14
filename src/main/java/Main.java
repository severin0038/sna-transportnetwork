import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class Main {

    public static String inputFolder = "src/main/resources/data";

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {

        Application application = new Application();

        //- Read all Files from Folder
        File folder = new File(inputFolder+"/");
        File[] listOfFiles = folder.listFiles();
        String filePath;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                filePath = file.getPath().replace("\\", "/").replace("src/main/resources/", "");
                application.sbbDataSetToConnectionsList(filePath);
            }
        }

        application.exportConnectionsToCSV("2020-04-04___2019-04-06.csv");
        application.addGeolocationToTrainstations("stations_geolocation_v07_02.csv");
        application.trainStationsToNodesList("trainStations.csv");

    }
}
