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
import java.util.regex.Pattern;

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


    ArrayList<Item> readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<Item> items = new ArrayList<>();
        String st;
        LocalDateTime dateAn = LocalDateTime.of(1970, 1, 1, 0, 0);
        LocalDateTime dateAb = LocalDateTime.of(2070, 1, 1, 0, 0);
        String DATE_FORMAT = "dd.MM.yyyy HH:mm";
        String DATE_FORMAT_WITH_SECONDS = "dd.MM.yyyy HH:mm:ss";
        Pattern DATE_PATTERN = Pattern.compile("^\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}");
        Pattern DATE_PATTERN_WITH_SECONDS = Pattern.compile("^\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}:\\d{2}");

        while ((st = br.readLine()) != null) {
            String[] it = st.split(";");
            if(it.length > 20) {
                if (/*it[3].equals("SBB") &&*/ it[5].equals("Zug")) {

                    //- Delete Commas
                    it[13] = deleteCommas(it[13]);

                    int trainStationId = returnTrainstationIdAndCreateTrainstationIfDoesntExists(it[13]);

                    Item item = new Item(
                            it[0], //- BETRIEBSTAG
                            it[1], //- FAHRT_BEZEICHNER
                            it[2], //- BETREIBER_ID
                            it[3], //- BETREIBER_ABK
                            it[4], //- BETREIBER_NAME
                            it[5], //- PRODUKT_ID
                            it[6], //- LINIEN_ID
                            it[7], //- LINIEN_TEXT
                            it[8], //- UMLAUF_ID
                            it[9], //- VERKEHRSMITTEL_TEXT
                            Boolean.parseBoolean(it[10]), //- ZUSATZFAHRT_TF
                            Boolean.parseBoolean(it[11]), //- FAELLT_AUS_TF
                            it[12], //- BPUIC
                            trainStationId, //- HALTESTELLEN_NAME it[13]
                            parseStringToDate(it[14], DATE_FORMAT, DATE_PATTERN, dateAn), //- ANKUNFTSZEIT
                            parseStringToDate(it[15], DATE_FORMAT_WITH_SECONDS, DATE_PATTERN_WITH_SECONDS, dateAn), //- AN_PROGNOSE
                            it[16], //- AN_PROGNOSE_STATUS
                            parseStringToDate(it[17], DATE_FORMAT, DATE_PATTERN, dateAb), //- ABFAHRTSZEIT
                            parseStringToDate(it[18], DATE_FORMAT_WITH_SECONDS, DATE_PATTERN_WITH_SECONDS, dateAb), //- AB_PROGNOSE
                            it[19], //- AB_PROGNOSE_STATUS
                            it[20]); //- AB_PROGNOSE_STATUS
                    items.add(item);
                }
            }

        }
        return items;
    }

    ArrayList<GeoItem> readGeoLocationFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<GeoItem> geoItems = new ArrayList<>();
        String st;

        while ((st = br.readLine()) != null) {
            String[] it = st.split(",");

            // TODO: input file bereinigen
            // TODO verbessern: Ortbezeichnungen nach Komma werden nicht eingelesen
            if(it.length > 3)
            {
                it[2] = it[2] + it[3];
            }

            it[2] = deleteCommas(it[2]);

                GeoItem geoItem = new GeoItem(
                        it[0],
                        it[1],
                        it[2]);
                geoItems.add(geoItem);
        }
        return geoItems;
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

    private LocalDateTime parseStringToDate(String date, String format, Pattern datePattern, LocalDateTime dateIfEmpty ) {
        if(!date.isEmpty() && datePattern.matcher(date).matches()) {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
        } else {
            return dateIfEmpty;
        }
    }

    private String deleteCommas(String input)
    {
        return input.replaceAll(",", "");
    }

}
