package data;

import java.time.LocalDateTime;

public class SingleConnection extends Connection {

    private String betreiber_abk;
    private String linienId;
    private LocalDateTime abfahrtszeit;
    private LocalDateTime abfahrtPrognose;
    private LocalDateTime ankunftszeit;
    private LocalDateTime ankunftProgrnose;

    public SingleConnection(int abfahrtsBahnhof, int ankunftsBahnhof, String betreiber_abk, String linienId, LocalDateTime abfahrtszeit, LocalDateTime abfahrtPrognose, LocalDateTime ankunftszeit, LocalDateTime ankunftProgrnose) {
        super(abfahrtsBahnhof, ankunftsBahnhof);
        this.betreiber_abk = betreiber_abk;
        this.linienId = linienId;
        this.abfahrtszeit = abfahrtszeit;
        this.abfahrtPrognose = abfahrtPrognose;
        this.ankunftszeit = ankunftszeit;
        this.ankunftProgrnose = ankunftProgrnose;
    }

    @Override
    public String toString() {
        return abfahrtsBahnhofId + " nach " + ankunftsBahnhofId;
    }

    /* Getter and Setter */

    public String getBetreiber_abk() {
        return betreiber_abk;
    }

    public void setBetreiber_abk(String betreiber_abk) {
        this.betreiber_abk = betreiber_abk;
    }

    public String getLinienId() {
        return linienId;
    }

    public void setLinienId(String linienId) {
        this.linienId = linienId;
    }

    public LocalDateTime getAbfahrtszeit() {
        return abfahrtszeit;
    }

    public void setAbfahrtszeit(LocalDateTime abfahrtszeit) {
        this.abfahrtszeit = abfahrtszeit;
    }

    public LocalDateTime getAbfahrtPrognose() {
        return abfahrtPrognose;
    }

    public void setAbfahrtPrognose(LocalDateTime abfahrtPrognose) {
        this.abfahrtPrognose = abfahrtPrognose;
    }

    public LocalDateTime getAnkunftszeit() {
        return ankunftszeit;
    }

    public void setAnkunftszeit(LocalDateTime ankunftszeit) {
        this.ankunftszeit = ankunftszeit;
    }

    public LocalDateTime getAnkunftPrognose() {
        return ankunftProgrnose;
    }

    public void setAnkunftProgrnose(LocalDateTime ankunftProgrnose) {
        this.ankunftProgrnose = ankunftProgrnose;
    }
}
