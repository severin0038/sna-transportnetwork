package data;

public class Connection {

    protected int abfahrtsBahnhofId;
    protected int ankunftsBahnhofId;

    public Connection(int abfahrtsBahnhofId, int ankunftsBahnhofId) {
        this.abfahrtsBahnhofId = abfahrtsBahnhofId;
        this.ankunftsBahnhofId = ankunftsBahnhofId;
    }

    @Override
    public String toString() {
        return abfahrtsBahnhofId + " nach " + ankunftsBahnhofId;
    }

    /* Getter and Setter */

    public int getAbfahrtsBahnhofId() {
        return abfahrtsBahnhofId;
    }

    public void setAbfahrtsBahnhofId(int abfahrtsBahnhofId) {
        this.abfahrtsBahnhofId = abfahrtsBahnhofId;
    }

    public int getAnkunftsBahnhofId() {
        return ankunftsBahnhofId;
    }

    public void setAnkunftsBahnhofId(int ankunftsBahnhofId) {
        this.ankunftsBahnhofId = ankunftsBahnhofId;
    }
}
