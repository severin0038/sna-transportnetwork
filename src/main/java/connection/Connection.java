package connection;

public class Connection {

    protected String abfahrtsBahnhof;
    protected String ankunftsBahnhof;

    public Connection(String abfahrtsBahnhof, String ankunftsBahnhof) {
        this.abfahrtsBahnhof = abfahrtsBahnhof;
        this.ankunftsBahnhof = ankunftsBahnhof;
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
}
