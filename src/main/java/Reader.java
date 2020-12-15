import data.Trainstation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reader {
    private File file;
    private final String DATE_FORMAT = "dd.MM.yyyy HH:mm";
    private final String DATE_FORMAT_WITH_SECONDS = "dd.MM.yyyy HH:mm:ss";

    private Application application;

    public Reader(String fileName, Application application) throws URISyntaxException {
            file = getRessourceFile(fileName);
            this.application = application;
    }

    public File getRessourceFile(String fileName) throws URISyntaxException {
        URL filePath = Main.class.getClassLoader().getResource(fileName);
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

    public ArrayList<Item> readFile() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<Item> items = new ArrayList<>();
        String st;
        while ((st = br.readLine()) != null) {
            String[] it = st.split(";");
            if(/*it[3].equals("SBB") &&*/ it[5].equals("Zug")) {
                LocalDateTime dateAn = LocalDateTime.of(1970, 01, 01, 00, 00);
                LocalDateTime dateAb = LocalDateTime.of(2070, 01, 01, 00, 00);

                Trainstation haltestelle = application.getTrainstations().stream()
                        .filter(trainstation -> it[13].equals(trainstation.getStationName()))
                        .findAny()
                        .orElse(null);

                int haltestellen_id;

                if(haltestelle != null) {
                    haltestellen_id = haltestelle.getStationId();
                } else {
                    Trainstation ts = new Trainstation(it[13]);
                    application.getTrainstations().add(ts);
                    haltestellen_id = ts.getStationId();
                }

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
                        haltestellen_id,
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

    private LocalDateTime parseStringToDate(String date, String format, LocalDateTime dateIfEmpty ) {
        if(!date.isEmpty()) {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
        } else {
            return dateIfEmpty;
        }
    }

}
