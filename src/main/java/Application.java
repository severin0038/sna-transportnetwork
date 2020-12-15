import connection.Connection;
import connection.GroupConnection;
import connection.SingleConnection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

public class Application {

    private final int DELAY_ABWEICHUNG_KEI_AHNIG_WIE_DA_HEISST_IN_SECONDS = 60;
    private final String CSV_HEADER = "abfahrtsBahnhof;ankunftsBahnhof;verbindungenProTag;relativeAnzahlVerspaeteteAbfahrt;relativeAnzahlVerspaeteteAnkunft;durchschnittlicheAbfahrtsverspaetung;durchschnittlicheAnkunftsverspaetung;durchschnittlicheAbfahrtsverspaetungNurVerspaetete;durchschnittlicheAnkunftsverspaetungNurVerspaetete";

    public Application() {
    }

    public void sbbDataSetToSnaGraph() throws URISyntaxException, IOException, ParseException {

        Reader trainstationReader = new Reader("trainstations-ch.csv");
        ArrayList<Trainstation> trainstations = trainstationReader.readTrainstationFile();

        Reader reader = new Reader("2020-04-04_istdaten.csv");
        ArrayList<Item> items = reader.readFile();

        Map<String, List<Item>> itemsGroupedByLinienId = groupItemsByLinienId(items);
        ArrayList<SingleConnection> connections = groupedItemsToConnections(itemsGroupedByLinienId);
        ArrayList<SingleConnection> sortedConnections = sortConnectionsByAbfahrtsBahnhofAndAnkunftsBahnhof(connections);
        ArrayList<GroupConnection> connectionWithoutDuplicates = deleteDuplicatesInConnectionListAndCalculateSomeSumOrAverageValues(sortedConnections);

        System.out.println(connectionWithoutDuplicates);

        Writer writer = new Writer();
        writer.writeCSV(connectionWithoutDuplicates, "2020-04-04_network.csv", CSV_HEADER);
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
                        item.getBetreiber_abk(),
                        linienId,
                        item.getAbfahrtszeit(),
                        item.getAb_prognose(),
                        item.getAnkunftszeit(),
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

    private ArrayList<GroupConnection> deleteDuplicatesInConnectionListAndCalculateSomeSumOrAverageValues(ArrayList<SingleConnection> connections) {
        ArrayList<GroupConnection> connectionsWithoutDuplicates = new ArrayList<>();
        String abfahrtsBahnhof = "";
        String ankunftsBahnhof = "";
        int counter = 0;
        int countDelayedConnectionsAnkunft = 0;
        int countDelayedConnectionsAbfahrt = 0;
        int countDelayInSecondsAnkunft = 0;
        int countDelayInSecondsAbfahrt = 0;

        double relativeAnzahlVerspaeteteAbfahrt;
        double relativeAnzahlVerspaeteteAnkunft;
        int durchschnittlicheAbfahrtsverspaetung;
        int durchschnittlicheAnkunftsverspaetung;
        int durchschnittlicheAbfahrtsverspaetungNurVerspaetete;
        int durchschnittlicheAnkunftsverspaetungNurVerspaetete;

        for(int i = 0; i < connections.size()-1; i++) {
            if(abfahrtsBahnhof.equals(connections.get(i).getAbfahrtsBahnhof()) && ankunftsBahnhof.equals((connections.get(i).getAnkunftsBahnhof()))) {
                counter++;
                long delayAnkunft = Duration.between(connections.get(i).getAnkunftszeit(), connections.get(i).getAnkunftPrognose()).toSeconds();
                long delayAbfahrt = Duration.between(connections.get(i).getAbfahrtszeit(), connections.get(i).getAbfahrtPrognose()).toSeconds();

                if(delayAnkunft >= DELAY_ABWEICHUNG_KEI_AHNIG_WIE_DA_HEISST_IN_SECONDS && delayAnkunft < 60*60*24) {
                    countDelayedConnectionsAnkunft++;
                    countDelayInSecondsAnkunft += (delayAnkunft - DELAY_ABWEICHUNG_KEI_AHNIG_WIE_DA_HEISST_IN_SECONDS);
                }
                if(delayAbfahrt >= DELAY_ABWEICHUNG_KEI_AHNIG_WIE_DA_HEISST_IN_SECONDS && delayAbfahrt < 60*60*24) {
                    countDelayedConnectionsAbfahrt++;
                    countDelayInSecondsAbfahrt += (delayAbfahrt - DELAY_ABWEICHUNG_KEI_AHNIG_WIE_DA_HEISST_IN_SECONDS);
                }
            } else {
                if(i != 0) {
                    relativeAnzahlVerspaeteteAbfahrt = (double) countDelayedConnectionsAbfahrt/(double) counter;
                    relativeAnzahlVerspaeteteAnkunft = (double) countDelayedConnectionsAnkunft/(double) counter;
                    durchschnittlicheAbfahrtsverspaetung = countDelayInSecondsAbfahrt/counter;
                    durchschnittlicheAnkunftsverspaetung = countDelayInSecondsAnkunft/counter;
                    if(countDelayedConnectionsAbfahrt != 0) { durchschnittlicheAbfahrtsverspaetungNurVerspaetete = countDelayInSecondsAbfahrt/countDelayedConnectionsAbfahrt; } else { durchschnittlicheAbfahrtsverspaetungNurVerspaetete = 0; }
                    if(countDelayInSecondsAnkunft != 0) { durchschnittlicheAnkunftsverspaetungNurVerspaetete = countDelayInSecondsAnkunft/countDelayedConnectionsAnkunft; } else { durchschnittlicheAnkunftsverspaetungNurVerspaetete = 0; }

                    connectionsWithoutDuplicates.add(new GroupConnection(connections.get(i-1).getAbfahrtsBahnhof(), connections.get(i-1).getAnkunftsBahnhof(), counter, relativeAnzahlVerspaeteteAbfahrt, relativeAnzahlVerspaeteteAnkunft, durchschnittlicheAbfahrtsverspaetung, durchschnittlicheAnkunftsverspaetung, durchschnittlicheAbfahrtsverspaetungNurVerspaetete, durchschnittlicheAnkunftsverspaetungNurVerspaetete));
                }
                counter = 1;
                countDelayedConnectionsAnkunft = 0;
                countDelayedConnectionsAbfahrt = 0;
                countDelayInSecondsAnkunft = 0;
                countDelayInSecondsAbfahrt = 0;
                abfahrtsBahnhof = connections.get(i).getAbfahrtsBahnhof();
                ankunftsBahnhof = connections.get(i).getAnkunftsBahnhof();
            }
        }

        return connectionsWithoutDuplicates;
    }

}
