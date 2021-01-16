import data.SingleConnection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

class ApplicationPerDataset {

    private final int DELAY_TOLERANCE_IN_SECONDS = 60;

    Application application;
    ArrayList<SingleConnection> connections;


    ApplicationPerDataset(Application application, ArrayList<SingleConnection> connections) {
        this.application = application;
        this.connections = connections;
    }

    ArrayList<SingleConnection> sbbDataSetToConnectionsList(String inputFile) throws URISyntaxException, IOException {
        Reader reader = new Reader(inputFile, application);
        ArrayList<Item> items = reader.readFile();

        Map<String, List<Item>> itemsGroupedByLinienId = groupItemsByLinienId(items);
        groupedItemsToConnections(itemsGroupedByLinienId, connections);
        countKnockOnDelaysPerTrainstation();

        return connections;
    }

    void countKnockOnDelaysPerTrainstation() {
        ArrayList<SingleConnection> connectionsWithMinimum5MinutesArrivalDelay = new ArrayList<>();
        ArrayList<SingleConnection> connectionsWithMinimum5MinutesDepartureDelay = new ArrayList<>();

        System.out.println("Start countKnockOnDelaysPerTrainstation");

        for(int i = 0; i < connections.size()-1; i++) {
            long delayAnkunft = Duration.between(connections.get(i).getAnkunftszeit(), connections.get(i).getAnkunftPrognose()).getSeconds();
            long delayAbfahrt = Duration.between(connections.get(i).getAbfahrtszeit(), connections.get(i).getAbfahrtPrognose()).getSeconds();

            if (delayAnkunft >= 5 * 60 + DELAY_TOLERANCE_IN_SECONDS && delayAnkunft < 60 * 60 * 24) {
                connections.get(i).setAnkunftsverspaetungInSekunden(delayAnkunft);
                connectionsWithMinimum5MinutesArrivalDelay.add(connections.get(i));
            }

            if (delayAbfahrt >= 5 * 60 + DELAY_TOLERANCE_IN_SECONDS && delayAbfahrt < 60 * 60 * 24) {
                connections.get(i).setAbfahrtsverspaetungInSekunden(delayAbfahrt);
                connectionsWithMinimum5MinutesDepartureDelay.add(connections.get(i));
            }
        }

        for(int a = 0; a < connectionsWithMinimum5MinutesArrivalDelay.size()-1; a++) {
            SingleConnection con = connectionsWithMinimum5MinutesArrivalDelay.get(a);
        }

        int trainStationId;
        int numberOfKnockOnDelays;

        System.out.println("Anzahl Verbindungen: " + connections.size());
        System.out.println("Anzahl mind. 5 Minuten Ankunftsverspätung: " + connectionsWithMinimum5MinutesArrivalDelay.size());
        System.out.println("Anzahl mind. 5 Minuten Abfahrtsverspätung: " + connectionsWithMinimum5MinutesDepartureDelay.size());

        for(int l = 0; l < connectionsWithMinimum5MinutesArrivalDelay.size()-1; l++) {
            trainStationId = connectionsWithMinimum5MinutesArrivalDelay.get(l).getAnkunftsBahnhofId();
            numberOfKnockOnDelays = 0;
            LocalDateTime arrivalTime = connectionsWithMinimum5MinutesArrivalDelay.get(l).getAnkunftPrognose();

            for(int j = 0; j < connectionsWithMinimum5MinutesDepartureDelay.size()-1; j++) {
                if(connectionsWithMinimum5MinutesDepartureDelay.get(j).getAbfahrtsBahnhofId() == trainStationId) {
                    LocalDateTime departureTime = connectionsWithMinimum5MinutesDepartureDelay.get(j).getAbfahrtPrognose();
                    long differneceBetweenArrivalTimeOfDelayedArrivalTrainAndDepartureTimeOfDelayedDepartureTrainInSeconds = Duration.between(arrivalTime, departureTime).getSeconds();

                    if(differneceBetweenArrivalTimeOfDelayedArrivalTrainAndDepartureTimeOfDelayedDepartureTrainInSeconds > 0*60
                            && differneceBetweenArrivalTimeOfDelayedArrivalTrainAndDepartureTimeOfDelayedDepartureTrainInSeconds < 10*60 ) {
                        numberOfKnockOnDelays++;
                    }
                }
            }

            if(numberOfKnockOnDelays != 0) {
                int k = application.getTrainStations().size() - 1;

                while (k > -1) {
                    if (application.getTrainStations().get(k).getTrainStationId() == trainStationId) {
                        application.getTrainStations().get(k).setNumberOfKnockOnDelays(application.getTrainStations().get(k).getNumberOfKnockOnDelays()+numberOfKnockOnDelays);
                        k = -1;
                    }
                    k--;
                }

            }
        }

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
}
