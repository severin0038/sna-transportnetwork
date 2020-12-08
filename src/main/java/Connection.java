public class Connection {

    private String abfahrtsBahnhof;
    private String ankunftsBahnhof;
    private String linienId;
    private String ankunftzszeit;
    private String ankunftProgrnose;

    public Connection(String abfahrtsBahnhof, String ankunftsBahnhof, String linienId, String ankunftzszeit, String ankunftProgrnose) {
        this.abfahrtsBahnhof = abfahrtsBahnhof;
        this.ankunftsBahnhof = ankunftsBahnhof;
        this.linienId = linienId;
        this.ankunftzszeit = ankunftzszeit;
        this.ankunftProgrnose = ankunftProgrnose;
    }

    @Override
    public String toString() {
        return abfahrtsBahnhof + " nach " + ankunftsBahnhof;
    }

    /* Getter and Setter */

    public String getAbfahrtsBahnhof() {
        return abfahrtsBahnhof;
    }

    public void setAbfahrtsBahnhof(String abfahrtsBahnhof) {
        this.abfahrtsBahnhof = abfahrtsBahnhof;
    }

    public String getAnkunftsBahnhof() {
        return ankunftsBahnhof;
    }

    public void setAnkunftsBahnhof(String ankunftsBahnhof) {
        this.ankunftsBahnhof = ankunftsBahnhof;
    }

    public String getLinienId() {
        return linienId;
    }

    public void setLinienId(String linienId) {
        this.linienId = linienId;
    }

    public String getAnkunftzszeit() {
        return ankunftzszeit;
    }

    public void setAnkunftzszeit(String ankunftzszeit) {
        this.ankunftzszeit = ankunftzszeit;
    }

    public String getAnkunftProgrnose() {
        return ankunftProgrnose;
    }

    public void setAnkunftProgrnose(String ankunftProgrnose) {
        this.ankunftProgrnose = ankunftProgrnose;
    }
}
