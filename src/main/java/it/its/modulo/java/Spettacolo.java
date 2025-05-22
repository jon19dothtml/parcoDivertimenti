package it.its.modulo.java;

import org.w3c.dom.Attr;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Spettacolo extends Attrazione {

    private List<LocalTime> orarioDiInizio;
    private int durataSpettacoloInMinuti;
    private int postiASedere;

    public Spettacolo(String nomeAttrazione, String codiceAttrazione, Tipo tipoAttrazione, int capacitaOraria, int altezzaMinimaCm, boolean aperta, AreaTematica areaTematica, List<LocalTime> orarioDiInizio, int durataSpettacoloInMinuti, int postiASedere) {
        super(nomeAttrazione, codiceAttrazione, tipoAttrazione, capacitaOraria, altezzaMinimaCm, aperta, areaTematica);
        if(orarioDiInizio!=  null) {
            orarioDiInizio.sort(LocalTime::compareTo);
            this.orarioDiInizio = orarioDiInizio;
        }else orarioDiInizio = new ArrayList<>();
        this.durataSpettacoloInMinuti = durataSpettacoloInMinuti;
        this.postiASedere = postiASedere;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Spettacolo that)) return false;
        if (!super.equals(o)) return false;

        return durataSpettacoloInMinuti == that.durataSpettacoloInMinuti && postiASedere == that.postiASedere && Objects.equals(orarioDiInizio, that.orarioDiInizio);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(orarioDiInizio);
        result = 31 * result + durataSpettacoloInMinuti;
        result = 31 * result + postiASedere;
        return result;
    }

    @Override
    public String toString() {
        return "Spettacolo{" +
                "nomeAttrazione='" + this.getNomeAttrazione() + '\'' +
                ", codiceAttrazione='" + this.getCodiceAttrazione() + '\'' +
                ", tipoAttrazione=" + this.getTipoAttrazione() +
                ", capacitaOraria=" + this.getCapacitaOraria() +
                ", altezzaMinimaCm=" + this.getAltezzaMinimaCm() +
                ", aperta=" + this.isAperta() +
                ", areaTematica" + this.getAreaTematica() +
                "orarioDiInizio=" + orarioDiInizio +
                ", durataSpettacoloInMinuti=" + durataSpettacoloInMinuti +
                ", postiASedere=" + postiASedere +
                '}';
    }

    @Override
    public String getDescrizioneCompleta() {
        LocalTime oraAttuale= LocalTime.now();
        //crea un flusso della lista degli orari di inizio
        //dopo di che filtra tutti quelli che hanno ora di inzio after quella sua
        //oppure è uguale a ora
        //della lista che ha ottenuto prende solo il primo
        Optional<LocalTime> prossimoSpettacolo= this.orarioDiInizio
                .stream()
                .filter(orarioDiInizio -> orarioDiInizio.isAfter(oraAttuale) || orarioDiInizio.equals(oraAttuale))
                .findFirst();
        String descrizioneCompleta;
        return prossimoSpettacolo
                .map( localTime -> this.getNomeAttrazione() + "Ora di inizio " +localTime )
                .orElseGet(() ->this.getNomeAttrazione() + " Ora Inizio " + (!this.orarioDiInizio.isEmpty()
                ? this.orarioDiInizio.getFirst()
                : "NON CI SONO ORARI DISPONIBILI"));
    }

    public List<LocalTime> getOrarioDiInizio() {
        return orarioDiInizio;
    }

    public int getDurataSpettacoloInMinuti() {
        return durataSpettacoloInMinuti;
    }

    public int getPostiASedere() {
        return postiASedere;
    }

    @Override
    public int calcolaTempoAttesaMedio(int personeInCoda) {
        int prossimoSpettacoloDisponibile = personeInCoda / this.postiASedere;
        LocalTime orarioAttuale = LocalTime.now();
        List<LocalTime> prossimiSpettacoli= this.orarioDiInizio
                .stream()
                .filter(orarioDiInizio -> orarioDiInizio.isAfter(orarioAttuale)
                || orarioDiInizio.equals(orarioAttuale))
                .toList();
        if(!prossimiSpettacoli.isEmpty()
                && prossimiSpettacoli.size() > prossimoSpettacoloDisponibile){
            LocalTime orarioProssimoSpettacolo= prossimiSpettacoli.get(prossimoSpettacoloDisponibile);
            Duration duration = Duration.between(orarioProssimoSpettacolo, orarioAttuale);
            return (int) duration.get(ChronoUnit.MINUTES);
        }else return -1;


    }
}
