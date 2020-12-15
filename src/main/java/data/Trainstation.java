package data;

import static java.lang.String.valueOf;

public class Trainstation {

    private int stationId;
    private static int stationIdCounter = 1;
    private String stationName;

    public Trainstation(String stationName) {
        this.stationId = stationIdCounter++;
        this.stationName = stationName;
    }

    public String toCSVString(String delimeter) {
        return String.join(delimeter,
                valueOf(getStationId()),
                getStationName());
    }

    @Override
    public String toString() {
        return stationId + ": " +stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getStationId() {
        return stationId;
    }
}
