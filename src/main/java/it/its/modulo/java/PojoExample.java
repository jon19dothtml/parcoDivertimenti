package it.its.modulo.java;

public class PojoExample {
    public String nome;
    public String cognome;
    public String CodiceFiscale;

    @Override
    public String toString() {
        return "PojoExample{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", CodiceFiscale='" + CodiceFiscale + '\'' +
                '}';
    }
}
