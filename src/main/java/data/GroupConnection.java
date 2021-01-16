package data;

import static java.lang.String.valueOf;

public class GroupConnection extends  Connection {

    private int verbindungenProTag;
    private double relativeAnzahlVerspaeteteAbfahrt;
    private double relativeAnzahlVerspaeteteAnkunft;
    private int durchschnittlicheAbfahrtsverspaetung;
    private int durchschnittlicheAnkunftsverspaetung;
    private int durchschnittlicheAbfahrtsverspaetungNurVerspaetete;
    private int durchschnittlicheAnkunftsverspaetungNurVerspaetete;
    //- Total
    private double totAbfahrtAnzahlVerspaetungen;
    private double totAnkunftAnzahlVerspaetungen;
    private int totAbfahrtSekundenVerspaetungen;
    private int totAnkunftSekundenVerspaetungen;


    public GroupConnection(int abfahrtsBahnhof, int ankunftsBahnhof, int verbindungenProTag,
                           double relativeAnzahlVerspaeteteAbfahrt, double relativeAnzahlVerspaeteteAnkunft,
                           int durchschnittlicheAbfahrtsverspaetung, int durchschnittlicheAnkunftsverspaetung,
                           int durchschnittlicheAbfahrtsverspaetungNurVerspaetete, int durchschnittlicheAnkunftsverspaetungNurVerspaetete,
                           double totAbfahrtAnzahlVerspaetungen, double totAnkunftAnzahlVerspaetungen,
                           int totAbfahrtSekundenVerspaetungen, int totAnkunftSekundenVerspaetungen
                           ) {
        super(abfahrtsBahnhof, ankunftsBahnhof);
        this.verbindungenProTag = verbindungenProTag;
        this.relativeAnzahlVerspaeteteAbfahrt = relativeAnzahlVerspaeteteAbfahrt;
        this.relativeAnzahlVerspaeteteAnkunft = relativeAnzahlVerspaeteteAnkunft;
        this.durchschnittlicheAbfahrtsverspaetung = durchschnittlicheAbfahrtsverspaetung;
        this.durchschnittlicheAnkunftsverspaetung = durchschnittlicheAnkunftsverspaetung;
        this.durchschnittlicheAbfahrtsverspaetungNurVerspaetete = durchschnittlicheAbfahrtsverspaetungNurVerspaetete;
        this.durchschnittlicheAnkunftsverspaetungNurVerspaetete = durchschnittlicheAnkunftsverspaetungNurVerspaetete;
        this.totAbfahrtAnzahlVerspaetungen = totAbfahrtAnzahlVerspaetungen;
        this.totAnkunftAnzahlVerspaetungen = totAnkunftAnzahlVerspaetungen;
        this.totAbfahrtSekundenVerspaetungen = totAbfahrtSekundenVerspaetungen;
        this.totAnkunftSekundenVerspaetungen = totAnkunftSekundenVerspaetungen;
    }

    @Override
    public String toString() {
        return abfahrtsBahnhofId + " nach " + ankunftsBahnhofId + " (" + verbindungenProTag + " Verbindungen)\n"+
                "Verspätete Abfahrten: " + relativeAnzahlVerspaeteteAbfahrt*100 + "%, mit Ø " + durchschnittlicheAbfahrtsverspaetung +  "s Verspätung (resp. " + durchschnittlicheAbfahrtsverspaetungNurVerspaetete + "s)\n" +
                "Verspätete Ankünfte: " + relativeAnzahlVerspaeteteAnkunft*100 + "%, mit Ø " + durchschnittlicheAnkunftsverspaetung + "s Verspätung (resp. " + durchschnittlicheAnkunftsverspaetungNurVerspaetete + "s)\n" +
                "Total verspätetet Anzahl Abfahrten: " + totAbfahrtAnzahlVerspaetungen + "Total verspätetet Anzahl Ankünften: " + totAnkunftAnzahlVerspaetungen +  "Total verspätetet Abfahrten" + totAbfahrtSekundenVerspaetungen +  "Total verspätetet Ankünfte" + totAnkunftSekundenVerspaetungen + "\n\n"
                ;
    }

    public String toCSVString(String delimeter) {
        return String.join(delimeter,
                valueOf(abfahrtsBahnhofId),
                valueOf(ankunftsBahnhofId),
                valueOf(verbindungenProTag),
                valueOf(relativeAnzahlVerspaeteteAbfahrt),
                valueOf(relativeAnzahlVerspaeteteAnkunft),
                valueOf(durchschnittlicheAbfahrtsverspaetung),
                valueOf(durchschnittlicheAnkunftsverspaetung),
                valueOf(durchschnittlicheAbfahrtsverspaetungNurVerspaetete),
                valueOf(durchschnittlicheAnkunftsverspaetungNurVerspaetete),
                valueOf(totAbfahrtAnzahlVerspaetungen),
                valueOf(totAnkunftAnzahlVerspaetungen),
                valueOf(totAbfahrtSekundenVerspaetungen),
                valueOf(totAnkunftSekundenVerspaetungen)
        );
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

    public double getTotAbfahrtAnzahlVerspaetungen() {
        return totAbfahrtAnzahlVerspaetungen;
    }

    public void setTotAbfahrtAnzahlVerspaetungen(double totAbfahrtAnzahlVerspaetungen) {
        this.totAbfahrtAnzahlVerspaetungen = totAbfahrtAnzahlVerspaetungen;
    }

    public double getTotAnkunftAnzahlVerspaetungen() {
        return totAnkunftAnzahlVerspaetungen;
    }

    public void setTotAnkunftAnzahlVerspaetungen(double totAnkunftAnzahlVerspaetungen) {
        this.totAnkunftAnzahlVerspaetungen = totAnkunftAnzahlVerspaetungen;
    }

    public int getTotAbfahrtSekundenVerspaetungen() {
        return totAbfahrtSekundenVerspaetungen;
    }

    public void setTotAbfahrtSekundenVerspaetungen(int totAbfahrtSekundenVerspaetungen) {
        this.totAbfahrtSekundenVerspaetungen = totAbfahrtSekundenVerspaetungen;
    }

    public int getTotAnkunftSekundenVerspaetungen() {
        return totAnkunftSekundenVerspaetungen;
    }

    public void setTotAnkunftSekundenVerspaetungen(int totAnkunftSekundenVerspaetungen) {
        this.totAnkunftSekundenVerspaetungen = totAnkunftSekundenVerspaetungen;
    }
}
