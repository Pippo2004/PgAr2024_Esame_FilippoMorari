import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazzo {
    private List<Carta> carte;

    public Mazzo() {
        carte = new ArrayList<>();
        // Aggiungi le carte al mazzo
        for (int i = 0; i < 50; i++) {
            carte.add(new Carta("BANG!", "Descrizione BANG!", 1, true));
        }
        for (int i = 0; i < 24; i++) {
            carte.add(new Carta("Mancato!", "Descrizione Mancato!", 0, false));
        }
        mescola();
    }

    public void mescola() {
        Collections.shuffle(carte);
    }

    public Carta pescaCarta() {
        if (carte.isEmpty()) {
            System.out.println("Il mazzo è vuoto. Non ci sono più carte da pescare.");
            return null;
        }
        return carte.remove(0);
    }
}
