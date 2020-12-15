package connection;

public class SingleConnection extends Connection {

    private String linienId;
    private String ankunftzszeit;
    private String ankunftProgrnose;

    public SingleConnection(String abfahrtsBahnhof, String ankunftsBahnhof, String linienId, String ankunftzszeit, String ankunftProgrnose) {
        super(abfahrtsBahnhof, ankunftsBahnhof);
        this.linienId = linienId;
        this.ankunftzszeit = ankunftzszeit;
        this.ankunftProgrnose = ankunftProgrnose;
    }

    @Override
    public String toString() {
        return abfahrtsBahnhof + " nach " + ankunftsBahnhof;
    }

    /* Getter and Setter */

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
