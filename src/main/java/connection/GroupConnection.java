package connection;

public class GroupConnection extends  Connection {

    private int verbindungenProTag;
    private double relativeAnzahlVerspaeteteAbfahrt;
    private double relativeAnzahlVerspaeteteAnkunft;
    private int durchschnittlicheAbfahrtsverspaetung;
    private int durchschnittlicheAnkunftsverspaetung;
    private int durchschnittlicheAbfahrtsverspaetungNurVerspaetete;
    private int durchschnittlicheAnkunftsverspaetungNurVerspaetete;

    public GroupConnection(String abfahrtsBahnhof, String ankunftsBahnhof, int verbindungenProTag, double relativeAnzahlVerspaeteteAbfahrt, double relativeAnzahlVerspaeteteAnkunft, int durchschnittlicheAbfahrtsverspaetung, int durchschnittlicheAnkunftsverspaetung, int durchschnittlicheAbfahrtsverspaetungNurVerspaetete, int durchschnittlicheAnkunftsverspaetungNurVerspaetete) {
        super(abfahrtsBahnhof, ankunftsBahnhof);
        this.verbindungenProTag = verbindungenProTag;
        this.relativeAnzahlVerspaeteteAbfahrt = relativeAnzahlVerspaeteteAbfahrt;
        this.relativeAnzahlVerspaeteteAnkunft = relativeAnzahlVerspaeteteAnkunft;
        this.durchschnittlicheAbfahrtsverspaetung = durchschnittlicheAbfahrtsverspaetung;
        this.durchschnittlicheAnkunftsverspaetung = durchschnittlicheAnkunftsverspaetung;
        this.durchschnittlicheAbfahrtsverspaetungNurVerspaetete = durchschnittlicheAbfahrtsverspaetungNurVerspaetete;
        this.durchschnittlicheAnkunftsverspaetungNurVerspaetete = durchschnittlicheAnkunftsverspaetungNurVerspaetete;
    }

    @Override
    public String toString() {
        return "\n" + abfahrtsBahnhof + " nach " + ankunftsBahnhof + " (" + verbindungenProTag + " Verbindungen)\n Verspätete Abfahrten: " + relativeAnzahlVerspaeteteAbfahrt*100 + "%, mit Ø " + durchschnittlicheAbfahrtsverspaetung +  "s Verspätung (resp. " + durchschnittlicheAbfahrtsverspaetungNurVerspaetete + "s)\n Verspätete Ankünfte: " + relativeAnzahlVerspaeteteAnkunft*100 + "%, mit Ø " + durchschnittlicheAnkunftsverspaetung + "s Verspätung (resp. " + durchschnittlicheAnkunftsverspaetungNurVerspaetete + "s)\n";
    }

    /* Getter and Setter */

    public int getVerbindungenProTag() {
        return verbindungenProTag;
    }

    public void setVerbindungenProTag(int verbindungenProTag) {
        this.verbindungenProTag = verbindungenProTag;
    }

    public double getRelativeAnzahlVerspaeteteAbfahrt() {
        return relativeAnzahlVerspaeteteAbfahrt;
    }

    public void setRelativeAnzahlVerspaeteteAbfahrt(double relativeAnzahlVerspaeteteAbfahrt) {
        this.relativeAnzahlVerspaeteteAbfahrt = relativeAnzahlVerspaeteteAbfahrt;
    }

    public double getRelativeAnzahlVerspaeteteAnkunft() {
        return relativeAnzahlVerspaeteteAnkunft;
    }

    public void setRelativeAnzahlVerspaeteteAnkunft(double relativeAnzahlVerspaeteteAnkunft) {
        this.relativeAnzahlVerspaeteteAnkunft = relativeAnzahlVerspaeteteAnkunft;
    }

    public int getDurchschnittlicheAbfahrtsverspaetung() {
        return durchschnittlicheAbfahrtsverspaetung;
    }

    public void setDurchschnittlicheAbfahrtsverspaetung(int durchschnittlicheAbfahrtsverspaetung) {
        this.durchschnittlicheAbfahrtsverspaetung = durchschnittlicheAbfahrtsverspaetung;
    }

    public int getDurchschnittlicheAnkunftsverspaetung() {
        return durchschnittlicheAnkunftsverspaetung;
    }

    public void setDurchschnittlicheAnkunftsverspaetung(int durchschnittlicheAnkunftsverspaetung) {
        this.durchschnittlicheAnkunftsverspaetung = durchschnittlicheAnkunftsverspaetung;
    }

    public int getDurchschnittlicheAbfahrtsverspaetungNurVerspaetete() {
        return durchschnittlicheAbfahrtsverspaetungNurVerspaetete;
    }

    public void setDurchschnittlicheAbfahrtsverspaetungNurVerspaetete(int durchschnittlicheAbfahrtsverspaetungNurVerspaetete) {
        this.durchschnittlicheAbfahrtsverspaetungNurVerspaetete = durchschnittlicheAbfahrtsverspaetungNurVerspaetete;
    }

    public int getDurchschnittlicheAnkunftsverspaetungNurVerspaetete() {
        return durchschnittlicheAnkunftsverspaetungNurVerspaetete;
    }

    public void setDurchschnittlicheAnkunftsverspaetungNurVerspaetete(int durchschnittlicheAnkunftsverspaetungNurVerspaetete) {
        this.durchschnittlicheAnkunftsverspaetungNurVerspaetete = durchschnittlicheAnkunftsverspaetungNurVerspaetete;
    }
}
