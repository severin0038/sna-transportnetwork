import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reader {
    private File file;
    private final String DATE_FORMAT = "dd.MM.yyyy HH:mm";
    private final String DATE_FORMAT_WITH_SECONDS = "dd.MM.yyyy HH:mm:ss";

    public Reader(String fileName) throws URISyntaxException {
            file = getRessourceFile(fileName);
    }

    public File getRessourceFile(String fileName) throws URISyntaxException {
        URL filePath = Main.class.getClassLoader().getResource(fileName);
        return Paths.get(filePath.toURI()).toFile();
    }

    public ArrayList<Trainstation> readTrainstationFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<Trainstation> trainStations = new ArrayList<>();
        String st;
        while ((st = br.readLine()) != null) {
            String[] ts = st.split(";");
            Trainstation trainStation = new Trainstation(Integer.valueOf(ts[0]), ts[1]);
            trainStations.add(trainStation);
        }
        return trainStations;
    }

    public ArrayList<Item> readFile() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<Item> items = new ArrayList<>();
        String st;
        while ((st = br.readLine()) != null) {
            String[] it = st.split(";");
            if(/*it[3].equals("SBB") &&*/ it[5].equals("Zug")) {
                Date dateAn = new Date(0);
                Date dateAb = new Date(3000);
                Item item = new Item(it[0], it[1], it[2], it[3], it[4], it[5], it[6], it[7], it[8], it[9], Boolean.parseBoolean(it[10]), Boolean.parseBoolean(it[11]), it[12], it[13], parseStringToDate(it[14], DATE_FORMAT, dateAn), parseStringToDate(it[15], DATE_FORMAT_WITH_SECONDS, dateAn), it[16], parseStringToDate(it[17], DATE_FORMAT, dateAb), parseStringToDate(it[18], DATE_FORMAT_WITH_SECONDS, dateAb), it[19], it[20]);
                items.add(item);
            }
        }
        return items;
    }

    private Date parseStringToDate(String date, String format, Date dateIfEmpty ) throws ParseException {
        if(!date.isEmpty()) {
            return new SimpleDateFormat(format).parse(date);
        } else {
            return dateIfEmpty;
        }
    }

}
