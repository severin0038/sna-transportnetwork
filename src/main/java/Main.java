import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static String outputFileName = "2020-04-04_network.csv";
    public static String inputFolder = "src/test/resources";

    public static void main(String[] args) throws IOException, URISyntaxException, ParseException {

        //- Init Application
        Application application = new Application();

        //- Read all Files from Folder
        File folder = new File(inputFolder + "/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                //- Collect Data
                if(file.getName().contains(".csv"))
                {
                    System.out.println(file.getName());
                    System.out.println(file.getAbsolutePath());
                    application.sbbDataSetToSnaGraph(file.getAbsolutePath());
                }
            }
        }


        //- Grouping
        application.groupAllData(outputFileName);


        //application.sbbDataSetToSnaGraph("2020-04-04_istdaten.csv", "2020-04-04_network.csv");
        // application.sbbDataSetToSnaGraph("2019-04-06istdaten.csv", "2019-04-06_network.csv");
        //application.sbbDataSetToSnaGraph("2020-04-04_istdaten.csv", "2020-04-04_network.csv");

        // application.sbbDataSetToSnaGraph("2020-04-04_istdaten.csv", "2020-04-04_network.csv");

         application.trainStationsToNodesList("trainStations.csv");
    }
}
