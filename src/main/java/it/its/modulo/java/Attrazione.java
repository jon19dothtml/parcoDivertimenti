package it.its.modulo.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Attrazione {
    private final String nomeAttrazione;
    private final String codiceAttrazione;
    public enum Tipo{
        ROLLERCOASTER,
        SHOW,
        FOOD;
        public static Map<String, Tipo> TIPO_MAP= new HashMap<>();
        static {
            TIPO_MAP.put("rollercoaster", ROLLERCOASTER);
            TIPO_MAP.put("show", SHOW);
            TIPO_MAP.put("food", FOOD);
        }
        public static Tipo lookUp(String value) {
            if (value == null)
                throw new IllegalArgumentException("Valore nullo");
            value = value.toLowerCase();
            Tipo tipo = TIPO_MAP.get(value);
            if (tipo != null) {
                return tipo;
            } else {
                throw new IllegalArgumentException("Genere non valido");
            }
        }
    }
    private final Tipo tipoAttrazione;
    private final int capacitaOraria;
    private final int altezzaMinimaCm;
    private final boolean aperta;

    public Attrazione(String nomeAttrazione, String codiceAttrazione, Tipo tipoAttrazione, int capacitaOraria, int altezzaMinimaCm, boolean aperta) {
        this.nomeAttrazione = nomeAttrazione;
        this.codiceAttrazione = codiceAttrazione;
        this.tipoAttrazione = tipoAttrazione;
        this.capacitaOraria = capacitaOraria;
        this.altezzaMinimaCm = altezzaMinimaCm;
        this.aperta = aperta;
    }

    public String getNomeAttrazione() {
        return nomeAttrazione;
    }

    public String getCodiceAttrazione() {
        return codiceAttrazione;
    }

    public Tipo getTipoAttrazione() {
        return tipoAttrazione;
    }

    public int getCapacitaOraria() {
        return capacitaOraria;
    }

    public int getAltezzaMinimaCm() {
        return altezzaMinimaCm;
    }

    public boolean isAperta() {
        return aperta;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Attrazione that)) return false;

        return capacitaOraria == that.capacitaOraria && altezzaMinimaCm == that.altezzaMinimaCm && aperta == that.aperta && Objects.equals(nomeAttrazione, that.nomeAttrazione) && Objects.equals(codiceAttrazione, that.codiceAttrazione) && tipoAttrazione == that.tipoAttrazione;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(nomeAttrazione);
        result = 31 * result + Objects.hashCode(codiceAttrazione);
        result = 31 * result + Objects.hashCode(tipoAttrazione);
        result = 31 * result + capacitaOraria;
        result = 31 * result + altezzaMinimaCm;
        result = 31 * result + Boolean.hashCode(aperta);
        return result;
    }

    @Override
    public String toString() {
        return "Attrazione{" +
                "nomeAttrazione='" + nomeAttrazione + '\'' +
                ", codiceAttrazione='" + codiceAttrazione + '\'' +
                ", tipoAttrazione=" + tipoAttrazione +
                ", capacitaOraria=" + capacitaOraria +
                ", altezzaMinimaCm=" + altezzaMinimaCm +
                ", aperta=" + aperta +
                '}';
    }
}
