import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InterazioneUtente interazioneUtente = new InterazioneUtente();
        int numeroGiocatori = interazioneUtente.chiediNumeroGiocatori();
        List<Giocatore> giocatori = new ArrayList<>(); // Inizializzazione dell'ArrayList
        for (int i = 0; i < numeroGiocatori; i++) {
            System.out.print("Inserisci il nome del giocatore " + (i + 1) + ": ");
            String nomeGiocatore = interazioneUtente.scanner.nextLine();
            giocatori.add(new Giocatore(nomeGiocatore, i == 0)); // Passa true se è lo sceriffo, altrimenti false

        }
        Partita partita = new Partita(giocatori);
        partita.stampaRuoliGiocatori(); // Stampiamo i ruoli assegnati ai giocatori
        partita.avviaPartita();
    }
}
