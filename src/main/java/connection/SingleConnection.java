package connection;

import java.util.Date;

public class SingleConnection extends Connection {

    private String linienId;
    private Date ankunftzszeit;
    private Date ankunftProgrnose;

    public SingleConnection(String abfahrtsBahnhof, String ankunftsBahnhof, String linienId, Date ankunftzszeit, Date ankunftProgrnose) {
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

    public Date getAnkunftzszeit() {
        return ankunftzszeit;
    }

    public void setAnkunftzszeit(Date ankunftzszeit) {
        this.ankunftzszeit = ankunftzszeit;
    }

    public Date getAnkunftProgrnose() {
        return ankunftProgrnose;
    }

    public void setAnkunftProgrnose(Date ankunftProgrnose) {
        this.ankunftProgrnose = ankunftProgrnose;
    }
}
