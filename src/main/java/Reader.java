import data.TrainStation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reader {
    private File file;

    private Application application;

    Reader(String fileName, Application application) throws URISyntaxException {
            file = getRessourceFile(fileName);
            this.application = application;
    }

    private File getRessourceFile(String fileName) throws URISyntaxException {
        URL filePath = Main.class.getClassLoader().getResource(fileName);
        assert filePath != null;
        return Paths.get(filePath.toURI()).toFile();
    }

//    public ArrayList<data.Trainstation> readTrainstationFile() throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(file));
//
//        ArrayList<data.Trainstation> trainStations = new ArrayList<>();
//        String st;
//        while ((st = br.readLine()) != null) {
//            String[] ts = st.split(";");
//            data.Trainstation trainStation = new data.Trainstation(Integer.valueOf(ts[0]), ts[1]);
//            trainStations.add(trainStation);
//        }
//        return trainStations;
//    }

    ArrayList<Item> readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<Item> items = new ArrayList<>();
        String st;
        LocalDateTime dateAn = LocalDateTime.of(1970, 1, 1, 0, 0);
        LocalDateTime dateAb = LocalDateTime.of(2070, 1, 1, 0, 0);
        String DATE_FORMAT = "dd.MM.yyyy HH:mm";
        String DATE_FORMAT_WITH_SECONDS = "dd.MM.yyyy HH:mm:ss";

        while ((st = br.readLine()) != null) {
            String[] it = st.split(";");
            if(/*it[3].equals("SBB") &&*/ it[5].equals("Zug")) {

                int trainStationId = returnTrainstationIdAndCreateTrainstationIfDoesntExists(it[13]);

                Item item = new Item(
                        it[0],
                        it[1],
                        it[2],
                        it[3],
                        it[4],
                        it[5],
                        it[6],
                        it[7],
                        it[8],
                        it[9],
                        Boolean.parseBoolean(it[10]),
                        Boolean.parseBoolean(it[11]),
                        it[12],
                        trainStationId,
                        parseStringToDate(it[14], DATE_FORMAT, dateAn),
                        parseStringToDate(it[15], DATE_FORMAT_WITH_SECONDS, dateAn),
                        it[16],
                        parseStringToDate(it[17], DATE_FORMAT, dateAb),
                        parseStringToDate(it[18], DATE_FORMAT_WITH_SECONDS, dateAb),
                        it[19],
                        it[20]);
                items.add(item);
            }
        }
        return items;
    }

    private int returnTrainstationIdAndCreateTrainstationIfDoesntExists(String trainstationName) {
        int trainstationId;

        TrainStation trainstation = application.getTrainStations().stream()
                .filter(ts -> trainstationName.equals(ts.getTrainStationName()))
                .findAny()
                .orElse(null);

        if(trainstation != null) {
            trainstationId = trainstation.getTrainStationId();
        } else {
            TrainStation ts = new TrainStation(trainstationName);
            application.getTrainStations().add(ts);
            trainstationId = ts.getTrainStationId();
        }
        return trainstationId;
    }

    private LocalDateTime parseStringToDate(String date, String format, LocalDateTime dateIfEmpty ) {
        if(!date.isEmpty()) {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
        } else {
            return dateIfEmpty;
        }
    }

}
