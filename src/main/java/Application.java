import data.Connection;
import data.GroupConnection;
import data.SingleConnection;
import data.TrainStation;

import javax.print.URIException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

class Application {

    private final int DELAY_ABWEICHUNG_KEI_AHNIG_WIE_DA_HEISST_IN_SECONDS = 60;
    private final String CSV_CONNECTION_HEADER = "abfahrtsBahnhof;ankunftsBahnhof;verbindungenProTag;relativeAnzahlVerspaeteteAbfahrt;relativeAnzahlVerspaeteteAnkunft;durchschnittlicheAbfahrtsverspaetung;durchschnittlicheAnkunftsverspaetung;durchschnittlicheAbfahrtsverspaetungNurVerspaetete;durchschnittlicheAnkunftsverspaetungNurVerspaetete";
    private final String CSV_CONNECTION_HEADER_READY_FOR_GEPHI = "Source;Target;verbindungenProTag;relativeAnzahlVerspaeteteAbfahrt;relativeAnzahlVerspaeteteAnkunft;durchschnittlicheAbfahrtsverspaetung;durchschnittlicheAnkunftsverspaetung;durchschnittlicheAbfahrtsverspaetungNurVerspaetete;durchschnittlicheAnkunftsverspaetungNurVerspaetete;totAbfahrtAnzahlVerspaetungen;totAnkunftAnzahlVerspaetungen;totAbfahrtSekundenVerspaetungen;totAnkunftSekundenVerspaetungen";
    private final String CSV_TRAINSTATION_HEADER = "bahnhofId;bahnhofName";
    private final String CSV_TRAINSTATION_HEADER_READY_FOR_GEPHI = "Id;Label;Longitude;Latitude";

    private ArrayList<TrainStation> trainStations = new ArrayList<>();
    ArrayList<SingleConnection> connections = new ArrayList<>();

    private Writer writer = new Writer();


    Application() {
    }

    void sbbDataSetToConnectionsList(String inputFile) throws URISyntaxException, IOException {

        Reader reader = new Reader(inputFile, this);
        ArrayList<Item> items = reader.readFile();

        Map<String, List<Item>> itemsGroupedByLinienId = groupItemsByLinienId(items);
        groupedItemsToConnections(itemsGroupedByLinienId, connections);

    }

    void exportConnectionsToCSV(String outputFileNameConnections) throws IOException {
        ArrayList<SingleConnection> sortedConnections = sortConnectionsByAbfahrtsBahnhofAndAnkunftsBahnhof(connections);
        ArrayList<GroupConnection> connectionWithoutDuplicates = deleteDuplicatesInConnectionListAndCalculateSomeSumAndAverageValues(sortedConnections);

        writer.writeConnectionCSV(connectionWithoutDuplicates, outputFileNameConnections, CSV_CONNECTION_HEADER_READY_FOR_GEPHI);
    }



    void addGeolocationToTrainstations(String GeoLocationFile) throws URISyntaxException, IOException {
            Reader reader = new Reader(GeoLocationFile, this);
            ArrayList<GeoItem> geolocation = reader.readGeoLocationFile();

            // TODO verbessern: Bei gleichem Namen werden Daten einfach überschrieben
            for (TrainStation ts: trainStations ) {
                for (int i = 1; i < geolocation.size(); i++) {
                    if(ts.getTrainStationName().equals(geolocation.get(i).getBahnhofsname())) {
                        ts.setLongitude(geolocation.get(i).getLongitude());
                        ts.setLatitude(geolocation.get(i).getLatitude());
                    }
                }
            }
    }

    void trainStationsToNodesList(String outputFileNameTrainStations) throws IOException {
        writer.writeTrainstationCSV(trainStations, outputFileNameTrainStations, CSV_TRAINSTATION_HEADER_READY_FOR_GEPHI);
    }

    private Map<String, List<Item>> groupItemsByLinienId(ArrayList<Item> items) {
        return items.stream().collect(groupingBy(it -> it.getLinien_id()+it.getBetreiber_abk()));
    }

    private void groupedItemsToConnections(Map<String, List<Item>> itemsGroupedByConnection, ArrayList<SingleConnection> connections) {

        itemsGroupedByConnection.forEach((linienId, listOfItems) -> {
            listOfItems.sort(Comparator.comparing(Item::getAnkunftszeit));

            for(int i = 0; i < listOfItems.size()-1; i++) {
                Item item = listOfItems.get(i);
                SingleConnection conn = new SingleConnection(
                        item.getHaltestellen_id(),
                        listOfItems.get(i+1).getHaltestellen_id(),
                        item.getBetreiber_abk(),
                        linienId,
                        item.getAbfahrtszeit(),
                        item.getAb_prognose(),
                        item.getAnkunftszeit(),
                        item.getAn_prognose());
                connections.add(conn);
            }
        });

    }

    private ArrayList<SingleConnection> sortConnectionsByAbfahrtsBahnhofAndAnkunftsBahnhof(ArrayList<SingleConnection> connections) {
        Collections.sort(connections, Comparator.comparing((Function<SingleConnection, Integer>) Connection::getAbfahrtsBahnhofId).thenComparing(Connection::getAnkunftsBahnhofId));
        return connections;
    }

    private ArrayList<GroupConnection> deleteDuplicatesInConnectionListAndCalculateSomeSumAndAverageValues(ArrayList<SingleConnection> connections) {
        ArrayList<GroupConnection> connectionsWithoutDuplicates = new ArrayList<>();
        int abfahrtsBahnhof = -1;
        int ankunftsBahnhof = -1;
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

        //- Total
        double totAbfahrtAnzahlVerspaetungen;
        double totAnkunftAnzahlVerspaetungen;
        int totAbfahrtSekundenVerspaetungen;
        int totAnkunftSekundenVerspaetungen;


        for(int i = 0; i < connections.size()-1; i++) {
            if((abfahrtsBahnhof == connections.get(i).getAbfahrtsBahnhofId()) && (ankunftsBahnhof == connections.get(i).getAnkunftsBahnhofId())) {
                counter++;
                long delayAnkunft = Duration.between(connections.get(i).getAnkunftszeit(), connections.get(i).getAnkunftPrognose()).getSeconds();
                long delayAbfahrt = Duration.between(connections.get(i).getAbfahrtszeit(), connections.get(i).getAbfahrtPrognose()).getSeconds();

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

                    //- Total
                    totAbfahrtAnzahlVerspaetungen = countDelayedConnectionsAbfahrt;
                    totAnkunftAnzahlVerspaetungen = countDelayedConnectionsAnkunft;
                    totAbfahrtSekundenVerspaetungen = countDelayInSecondsAbfahrt;
                    totAnkunftSekundenVerspaetungen = countDelayInSecondsAnkunft;

                    if(countDelayedConnectionsAbfahrt != 0) { durchschnittlicheAbfahrtsverspaetungNurVerspaetete = countDelayInSecondsAbfahrt/countDelayedConnectionsAbfahrt; } else { durchschnittlicheAbfahrtsverspaetungNurVerspaetete = 0; }
                    if(countDelayInSecondsAnkunft != 0) { durchschnittlicheAnkunftsverspaetungNurVerspaetete = countDelayInSecondsAnkunft/countDelayedConnectionsAnkunft; } else { durchschnittlicheAnkunftsverspaetungNurVerspaetete = 0; }

                    connectionsWithoutDuplicates.add(new GroupConnection(connections.get(i-1).getAbfahrtsBahnhofId(), connections.get(i-1).getAnkunftsBahnhofId(), counter, relativeAnzahlVerspaeteteAbfahrt, relativeAnzahlVerspaeteteAnkunft, durchschnittlicheAbfahrtsverspaetung, durchschnittlicheAnkunftsverspaetung, durchschnittlicheAbfahrtsverspaetungNurVerspaetete, durchschnittlicheAnkunftsverspaetungNurVerspaetete, totAbfahrtAnzahlVerspaetungen, totAnkunftAnzahlVerspaetungen, totAbfahrtSekundenVerspaetungen, totAnkunftSekundenVerspaetungen));
                }
                counter = 1;
                countDelayedConnectionsAnkunft = 0;
                countDelayedConnectionsAbfahrt = 0;
                countDelayInSecondsAnkunft = 0;
                countDelayInSecondsAbfahrt = 0;
                abfahrtsBahnhof = connections.get(i).getAbfahrtsBahnhofId();
                ankunftsBahnhof = connections.get(i).getAnkunftsBahnhofId();
            }
        }

        return connectionsWithoutDuplicates;
    }

    ArrayList<TrainStation> getTrainStations() {
        return trainStations;
    }
}
