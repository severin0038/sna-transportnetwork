public class Trainstation {

    private Integer stationId;
    private String stationName;

    Trainstation(Integer stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }

    public String infoAsLine(String delimeter) {
        return String.join(delimeter,
                Integer.toString(getStationId()),
                getStationName()
        );
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
