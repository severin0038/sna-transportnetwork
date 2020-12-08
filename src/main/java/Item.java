public class Item {

    private String betriebstag;
    private String fahrt_bezeichner;
    private String betreiber_id;
    private String betreiber_abk;
    private String betreiber_name;
    private String produkt_id;
    private String linien_id;
    private String linien_text;
    private String umlauf_id;
    private String verkehrsmittel_text;
    private boolean zusatzfahrt_tf;
    private boolean faellt_aus_tf;
    private String bpuic;
    private String haltestellen_name;
    private String ankunftszeit;
    private String an_prognose;
    private String an_prognose_status;
    private String abfahrtszeit;
    private String ab_prognose;
    private String ab_prognose_status;
    private String durchfahrt_tf;

    public Item(String betriebstag, String fahrt_bezeichner, String betreiber_id, String betreiber_abk, String betreiber_name, String produkt_id, String linien_id, String linien_text, String umlauf_id, String verkehrsmittel_text, boolean zusatzfahrt_tf, boolean faellt_aus_tf, String bpuic, String haltestellen_name, String ankunftszeit, String an_prognose, String an_prognose_status, String abfahrtszeit, String ab_prognose, String ab_prognose_status, String durchfahrt_tf) {
        this.betriebstag = betriebstag;
        this.fahrt_bezeichner = fahrt_bezeichner;
        this.betreiber_id = betreiber_id;
        this.betreiber_abk = betreiber_abk;
        this.betreiber_name = betreiber_name;
        this.produkt_id = produkt_id;
        this.linien_id = linien_id;
        this.linien_text = linien_text;
        this.umlauf_id = umlauf_id;
        this.verkehrsmittel_text = verkehrsmittel_text;
        this.zusatzfahrt_tf = zusatzfahrt_tf;
        this.faellt_aus_tf = faellt_aus_tf;
        this.bpuic = bpuic;
        this.haltestellen_name = haltestellen_name;
        this.ankunftszeit = ankunftszeit;
        this.an_prognose = an_prognose;
        this.an_prognose_status = an_prognose_status;
        this.abfahrtszeit = abfahrtszeit;
        this.ab_prognose = ab_prognose;
        this.ab_prognose_status = ab_prognose_status;
        this.durchfahrt_tf = durchfahrt_tf;
    }

//    @Override
//    public String toString() {
//        return "betriebstag: " + betriebstag
//            + ", fahrt_bezeichner: " + fahrt_bezeichner
//            + ", betreiber_id: " + betreiber_id
//            + ", betreiber_abk: " + betreiber_abk
//            + ", betreiber_name: " + betreiber_name
//            + ", produkt_id: " + produkt_id
//            + ", linien_id: " + linien_id
//            + ", linien_text: " + linien_text
//            + ", umlauf_id: " + umlauf_id
//            + ", verkehrsmittel_text: " + verkehrsmittel_text
//            + ", zusatzfahrt_tf: " + zusatzfahrt_tf
//            + ", faellt_aus_tf: " + faellt_aus_tf
//            + ", bpuic: " + bpuic
//            + ", haltestellen_name: " + haltestellen_name
//            + ", ankunftszeit: " + ankunftszeit
//            + ", an_prognose: " + an_prognose
//            + ", an_prognose_status: " + an_prognose_status
//            + ", abfahrtszeit: " + abfahrtszeit
//            + ", ab_prognose: " + ab_prognose
//            + ", ab_prognose_status: " + ab_prognose_status
//            + ", durchfahrt_tf: " + durchfahrt_tf;
//    }

    @Override
    public String toString() {
        return haltestellen_name + " " + ankunftszeit;
    }

    public String getBetriebstag() {
        return betriebstag;
    }

    public String getFahrt_bezeichner() {
        return fahrt_bezeichner;
    }

    public String getBetreiber_id() {
        return betreiber_id;
    }

    public String getBetreiber_abk() {
        return betreiber_abk;
    }

    public String getBetreiber_name() {
        return betreiber_name;
    }

    public String getProdukt_id() {
        return produkt_id;
    }

    public String getLinien_id() {
        return linien_id;
    }

    public String getLinien_text() {
        return linien_text;
    }

    public String getUmlauf_id() {
        return umlauf_id;
    }

    public String getVerkehrsmittel_text() {
        return verkehrsmittel_text;
    }

    public boolean isZusatzfahrt_tf() {
        return zusatzfahrt_tf;
    }

    public boolean isFaellt_aus_tf() {
        return faellt_aus_tf;
    }

    public String getBpuic() {
        return bpuic;
    }

    public String getHaltestellen_name() {
        return haltestellen_name;
    }

    public String getAnkunftszeit() {
        return ankunftszeit;
    }

    public String getAn_prognose() {
        return an_prognose;
    }

    public String getAn_prognose_status() {
        return an_prognose_status;
    }

    public String getAbfahrtszeit() {
        return abfahrtszeit;
    }

    public String getAb_prognose() {
        return ab_prognose;
    }

    public String getAb_prognose_status() {
        return ab_prognose_status;
    }

    public String getDurchfahrt_tf() {
        return durchfahrt_tf;
    }
}