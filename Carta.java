public class Carta {
    private String nome;
    private String descrizione;
    private int puntiFerita;
    private boolean equipaggiabile;

    public Carta(String nome, String descrizione, int puntiFerita, boolean equipaggiabile) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.puntiFerita = puntiFerita;
        this.equipaggiabile = equipaggiabile;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getPuntiFerita() {
        return puntiFerita;
    }

    public boolean isEquipaggiabile() {
        return equipaggiabile;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", puntiFerita=" + puntiFerita +
                ", equipaggiabile=" + equipaggiabile +
                '}';
    }
}
