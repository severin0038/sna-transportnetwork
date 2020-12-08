import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Application {

    Reader trainstationReader = new Reader("trainstations-ch.csv");
    ArrayList<Trainstation> trainstations = trainstationReader.readTrainstationFile();

    Reader reader = new Reader("2020-04-04_istdaten.csv");
    ArrayList<Item> items = reader.readFile();

    ArrayList<Connection> connections = new ArrayList<>();

    public Application() throws URISyntaxException, IOException {
    }


    public Map<String, List<Item>> groupItemsByLinienId(ArrayList<Item> items) {
        Map<String, List<Item>> itemsGroupedByConnection =
                items.stream().collect(groupingBy(it -> it.getLinien_id()));

        return itemsGroupedByConnection;
    }

    public void groupedItemsToConnections(Map<String, List<Item>> itemsGroupedByConnection) {

        itemsGroupedByConnection.forEach((linienId, listOfItems) -> {
            listOfItems.sort(Comparator.comparing(Item::getAnkunftszeit));

            for(int i = 0; i < listOfItems.size()-1; i++) {
                Item item = listOfItems.get(i);
                Connection conn = new Connection(
                        item.getHaltestellen_name(),
                        listOfItems.get(i+1).getHaltestellen_name(),
                        linienId, item.getAnkunftszeit(),
                        item.getAn_prognose());
                connections.add(conn);
            }
        });

    }

}
