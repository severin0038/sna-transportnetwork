package data;

import static java.lang.String.valueOf;

public class TrainStation {

    private int trainStationId;
    private static int trainStationIdCounter = 0;
    private String trainStationName;
    private String longitude;
    private String latitude;

    public TrainStation(String trainStationName) {
        this.trainStationId = trainStationIdCounter++;
        this.trainStationName = deleteCommas(trainStationName);
        // todo default values löschen wenn nicht mehr benötigt
        System.out.println("Set Default for: ########### " + trainStationName);
        this.longitude = "8"; // default value (oberhalb der Schweiz)
        this.latitude = "48"; // default value, damit in Gephi ersichtlich
    }


    public String toCSVString(String delimeter) {
        return String.join(delimeter,
                valueOf(getTrainStationId()),
                getTrainStationName(),
                valueOf(getLongitude()),
                valueOf(getLatitude())
        );
    }


    @Override
    public String toString() {
        return trainStationId + ": " + trainStationName;
    }

    public String getTrainStationName() {
        return trainStationName;
    }

    public void setTrainStationName(String trainStationName) {
        this.trainStationName = deleteCommas(trainStationName);
    }

    public int getTrainStationId() {
        return trainStationId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    private String deleteCommas(String input)
    {
        return input.replaceAll(",", "");
    }

}
