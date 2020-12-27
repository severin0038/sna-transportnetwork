public class GeoItem {

    private String longitude;
    private String latitude;
    private String bahnhofsname;


    public GeoItem(String longitude, String latitude, String bahnhofsname) {
        this.bahnhofsname = bahnhofsname;
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public String getBahnhofsname() {
        return bahnhofsname;
    }

    public void setBahnhofsname(String bahnhofsname) {
        this.bahnhofsname = bahnhofsname;
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
}