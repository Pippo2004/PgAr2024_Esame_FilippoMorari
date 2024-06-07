import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Partita {
    private List<Giocatore> giocatori;
    private Mazzo mazzo;
    private Giocatore sceriffo;
    private int indiceGiocatoreCorrente;

    public Partita(List<Giocatore> giocatori) {
        this.giocatori = giocatori;
        mazzo = new Mazzo();
        distribuisciCarteIniziali();
        assegnaRuoli();
        sceriffo = giocatori.get(0);
        indiceGiocatoreCorrente = 0;
    }

    private void distribuisciCarteIniziali() {
        for (Giocatore giocatore : giocatori) {
            for (int i = 0; i < giocatore.getPuntiFerita(); i++) {
                giocatore.pesca(mazzo);
            }
        }
    }

    private void assegnaRuoli() {
        int numeroGiocatori = giocatori.size();

        giocatori.get(0).setRuolo("Sceriffo");
        giocatori.get(numeroGiocatori - 1).setRuolo("Rinnegato");

        int numeroFuorilegge = 0;
        int numeroVice = 0;
        switch (numeroGiocatori) {
            case 4:
                numeroFuorilegge = 2;
                break;
            case 5:
                numeroFuorilegge = 2;
                numeroVice = 1;
                break;
            case 6:
                numeroFuorilegge = 3;
                numeroVice = 1;
                break;
            case 7:
                numeroFuorilegge = 3;
                numeroVice = 2;
                break;
            default:
                break;
        }

        List<String> ruoli = new ArrayList<>();
        for (int i = 0; i < numeroFuorilegge; i++) {
            ruoli.add("Fuorilegge");
        }
        for (int i = 0; i < numeroVice; i++) {
            ruoli.add("Vice");
        }
        Collections.shuffle(ruoli);

        int index = 0;
        for (Giocatore giocatore : giocatori) {
            if (!giocatore.getRuolo().equals("Sceriffo") && !giocatore.getRuolo().equals("Rinnegato")) {
                if (index < ruoli.size()) {
                    giocatore.setRuolo(ruoli.get(index));
                    index++;
                } else {
                    break;
                }
            }
        }
    }

    public void stampaRuoliGiocatori() {
        for (Giocatore giocatore : giocatori) {
            System.out.println(giocatore);
            giocatore.stampaMano();
        }
    }

    public void avviaPartita() {
        while (!isPartitaFinita()) {
            Giocatore giocatoreCorrente = giocatori.get(indiceGiocatoreCorrente);
            System.out.println("Turno di " + giocatoreCorrente.getNome());
            giocatoreCorrente.stampaInformazioni();
            Turno turno = new Turno(giocatoreCorrente);
            turno.eseguiTurno(mazzo, giocatori);
            giocatoreCorrente.scartaCarteInEccesso(); // Chiamata al metodo per scartare le carte in eccesso
            indiceGiocatoreCorrente = (indiceGiocatoreCorrente + 1) % giocatori.size();
        }
    }


    private boolean isPartitaFinita() {
        for (int i = 0; i < giocatori.size(); i++) {
            Giocatore giocatore = giocatori.get(i);
            if (giocatore.getPuntiFerita() <= 0) {
                System.out.println(giocatore.getNome() + " è stato eliminato!");
                giocatori.remove(giocatore);
                if (giocatore.getRuolo().equals("Sceriffo")) {
                    System.out.println("I Fuorilegge hanno vinto la partita!");
                } else if (giocatore.getRuolo().equals("Rinnegato")) {
                    System.out.println("I Fuorilegge hanno vinto la partita!");
                } else if (giocatori.size() == 1 && giocatori.get(0).getRuolo().equals("Sceriffo")) {
                    System.out.println("Lo Sceriffo ha vinto la partita!");
                }
                return true;
            }
        }
        return false;
    }
}
