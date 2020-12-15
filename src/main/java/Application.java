import connection.Connection;
import connection.GroupConnection;
import connection.SingleConnection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

public class Application {

    public Application() throws URISyntaxException, IOException {
    }

    public void sbbDataSetToSnaGraph() throws URISyntaxException, IOException, ParseException {

        Reader trainstationReader = new Reader("trainstations-ch.csv");
        ArrayList<Trainstation> trainstations = trainstationReader.readTrainstationFile();

        Reader reader = new Reader("2020-04-04_istdaten.csv");
        ArrayList<Item> items = reader.readFile();

        Map<String, List<Item>> itemsGroupedByLinienId = groupItemsByLinienId(items);
        ArrayList<SingleConnection> connections = groupedItemsToConnections(itemsGroupedByLinienId);
        ArrayList<SingleConnection> sortedConnections = sortConnectionsByAbfahrtsBahnhofAndAnkunftsBahnhof(connections);
        ArrayList<GroupConnection> connectionWithoutDuplicates = deleteDuplicatesInConnectionList(sortedConnections);

        System.out.println(connectionWithoutDuplicates);
    }

    private Map<String, List<Item>> groupItemsByLinienId(ArrayList<Item> items) {
        Map<String, List<Item>> itemsGroupedByConnection =
                items.stream().collect(groupingBy(it -> it.getLinien_id()+it.getBetreiber_abk()));

        return itemsGroupedByConnection;
    }

    private ArrayList<SingleConnection> groupedItemsToConnections(Map<String, List<Item>> itemsGroupedByConnection) {

        ArrayList<SingleConnection> connections = new ArrayList<>();

        itemsGroupedByConnection.forEach((linienId, listOfItems) -> {
            listOfItems.sort(Comparator.comparing(Item::getAnkunftszeit));

            for(int i = 0; i < listOfItems.size()-1; i++) {
                Item item = listOfItems.get(i);
                SingleConnection conn = new SingleConnection(
                        item.getHaltestellen_name(),
                        listOfItems.get(i+1).getHaltestellen_name(),
                        linienId, item.getAnkunftszeit(),
                        item.getAn_prognose());
                connections.add(conn);
            }
        });

        return connections;
    }

    private ArrayList<SingleConnection> sortConnectionsByAbfahrtsBahnhofAndAnkunftsBahnhof(ArrayList<SingleConnection> connections) {
        Collections.sort(connections, Comparator.comparing((Function<SingleConnection, String>) Connection::getAbfahrtsBahnhof).thenComparing(Connection::getAnkunftsBahnhof));
        return connections;
    }

    private ArrayList<GroupConnection> deleteDuplicatesInConnectionList(ArrayList<SingleConnection> connections) {
        ArrayList<GroupConnection> connectionsWithoutDuplicates = new ArrayList<>();
        String abfahrtsBahnhof = "";
        String ankunftsBahnhof = "";
        int counter = 0;
        int countDelayedConnections = 0;

        for(int i = 0; i < connections.size()-1; i++) {
            if(abfahrtsBahnhof.equals(connections.get(i).getAbfahrtsBahnhof()) && ankunftsBahnhof.equals((connections.get(i).getAnkunftsBahnhof()))) {
                counter++;
            } else {
                if(i != 0) {
                    connectionsWithoutDuplicates.add(new GroupConnection(connections.get(i-1).getAbfahrtsBahnhof(), connections.get(i-1).getAnkunftsBahnhof(), counter));
                }
                counter = 1;
                abfahrtsBahnhof = connections.get(i).getAbfahrtsBahnhof();
                ankunftsBahnhof = connections.get(i).getAnkunftsBahnhof();
            }
        }

        return connectionsWithoutDuplicates;
    }

}
