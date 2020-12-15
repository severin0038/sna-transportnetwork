import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Reader {
    private File file;

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

    public ArrayList<Item> readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));

        ArrayList<Item> items = new ArrayList<>();
        String st;
        while ((st = br.readLine()) != null) {
            String[] it = st.split(";");
            if(/*it[3].equals("SBB") &&*/ it[5].equals("Zug")) {
                Item item = new Item(it[0], it[1], it[2], it[3], it[4], it[5], it[6], it[7], it[8], it[9], Boolean.parseBoolean(it[10]), Boolean.parseBoolean(it[11]), it[12], it[13], it[14], it[15], it[16], it[17], it[18], it[19], it[20]);
                items.add(item);
            }
        }
        return items;
    }

}
