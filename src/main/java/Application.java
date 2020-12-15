import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Application {

    public Application() throws URISyntaxException, IOException {
    }

    public void sbbDataSetToSnaGraph() throws URISyntaxException, IOException {

        Reader trainstationReader = new Reader("trainstations-ch.csv");
        ArrayList<Trainstation> trainstations = trainstationReader.readTrainstationFile();

        Reader reader = new Reader("2020-04-04_istdaten.csv");
        ArrayList<Item> items = reader.readFile();

        Map<String, List<Item>> itemsGroupedByLinienId = groupItemsByLinienId(items);
        ArrayList<Connection> connections = groupedItemsToConnections(itemsGroupedByLinienId);
        ArrayList<Connection> sortedConnections = sortConnectionsByAbfahrtsBahnhofAndAnkunftsBahnhof(connections);
        ArrayList<Connection> connectionWithoutDuplicates = deleteDuplicatesInConnectionList(sortedConnections);

        System.out.println(connectionWithoutDuplicates);
    }

    private Map<String, List<Item>> groupItemsByLinienId(ArrayList<Item> items) {
        Map<String, List<Item>> itemsGroupedByConnection =
                items.stream().collect(groupingBy(it -> it.getLinien_id()));

        return itemsGroupedByConnection;
    }

    private ArrayList<Connection> groupedItemsToConnections(Map<String, List<Item>> itemsGroupedByConnection) {

        ArrayList<Connection> connections = new ArrayList<>();

        itemsGroupedByConnection.forEach((linienId, listOfItems) -> {
            listOfItems.sort(Comparator.comparing(Item::getAnkunftszeit));

            for(int i = 0; i < listOfItems.size()-1; i++) {
                Item item = listOfItems.get(i);
                Connection conn = new Connection(
                        item.getHaltestellen_name(),
                        listOfItems.get(i+1).getHaltestellen_name(),
                        linienId, item.getAnkunftszeit(),
                        item.getAn_prognose(),
                        0);
                connections.add(conn);
            }
        });

        return connections;
    }

    private ArrayList<Connection> sortConnectionsByAbfahrtsBahnhofAndAnkunftsBahnhof(ArrayList<Connection> connections) {
        Collections.sort(connections, (Comparator) (o1, o2) -> {

            String x1 = ((Connection) o1).getAbfahrtsBahnhof();
            String x2 = ((Connection) o2).getAbfahrtsBahnhof();
            int sComp = x1.compareTo(x2);

            if (sComp != 0) {
                return sComp;
            }

            String y1 = ((Connection) o1).getAnkunftsBahnhof();
            String y2 = ((Connection) o2).getAnkunftsBahnhof();
            return y1.compareTo(y2);
        });

        return connections;
    }

    private ArrayList<Connection> deleteDuplicatesInConnectionList(ArrayList<Connection> connections) {
        ArrayList<Connection> connectionsWithoutDuplicates = new ArrayList<>();
        String abfahrtsBahnhof = "";
        String ankunftsBahnhof = "";
        int counter = 0;

        for(int i = 0; i < connections.size()-1; i++) {
            if(abfahrtsBahnhof.equals(connections.get(i).getAbfahrtsBahnhof()) && ankunftsBahnhof.equals((connections.get(i).getAnkunftsBahnhof()))) {
                counter++;
            } else {
                if(i != 0) {
                    connectionsWithoutDuplicates.add(new Connection(connections.get(i-1).getAbfahrtsBahnhof(), connections.get(i-1).getAnkunftsBahnhof(), counter));
                }
                counter = 1;
                abfahrtsBahnhof = connections.get(i).getAbfahrtsBahnhof();
                ankunftsBahnhof = connections.get(i).getAnkunftsBahnhof();
            }
        }

        return connectionsWithoutDuplicates;
    }
}
