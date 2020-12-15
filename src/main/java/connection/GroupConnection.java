package connection;

public class GroupConnection extends  Connection {

    private int verbindungenProTag;

    public GroupConnection(String abfahrtsBahnhof, String ankunftsBahnhof, int verbindungenProTag) {
        super(abfahrtsBahnhof, ankunftsBahnhof);
        this.verbindungenProTag = verbindungenProTag;
    }

    @Override
    public String toString() {
        return abfahrtsBahnhof + " nach " + ankunftsBahnhof + ": " + verbindungenProTag;
    }

    /* Getter and Setter */

    public int getVerbindungenProTag() {
        return verbindungenProTag;
    }

    public void setVerbindungenProTag(int verbindungenProTag) {
        this.verbindungenProTag = verbindungenProTag;
    }
}
