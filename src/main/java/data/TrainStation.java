package data;

import static java.lang.String.valueOf;

public class TrainStation {

    private int trainStationId;
    private static int trainStationIdCounter = 1;
    private String trainStationName;

    public TrainStation(String trainStationName) {
        this.trainStationId = trainStationIdCounter++;
        this.trainStationName = trainStationName;
    }

    public String toCSVString(String delimeter) {
        return String.join(delimeter,
                valueOf(getTrainStationId()),
                getTrainStationName());
    }

    @Override
    public String toString() {
        return trainStationId + ": " + trainStationName;
    }

    public String getTrainStationName() {
        return trainStationName;
    }

    public void setTrainStationName(String trainStationName) {
        this.trainStationName = trainStationName;
    }

    public int getTrainStationId() {
        return trainStationId;
    }
}
