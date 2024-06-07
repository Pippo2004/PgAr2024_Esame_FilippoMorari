import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterazioneUtente {
    Scanner scanner;

    public InterazioneUtente() {
        this.scanner = new Scanner(System.in);
    }

    public int chiediNumeroGiocatori() {
        int numeroGiocatori = 0;
        while (numeroGiocatori < 4 || numeroGiocatori > 7) {
            System.out.print("Inserisci il numero di giocatori (da 4 a 7): ");
            if (scanner.hasNextInt()) {
                numeroGiocatori = scanner.nextInt();
                scanner.nextLine(); // Consuma il newline dopo il numero
            } else {
                scanner.nextLine(); // Consuma l'input non valido
            }
            if (numeroGiocatori < 4 || numeroGiocatori > 7) {
                System.out.println("Il numero di giocatori deve essere compreso tra 4 e 7.");
            }
        }
        return numeroGiocatori;
    }

    public List<String> chiediNomiGiocatori(int numeroGiocatori) {
        List<String> nomiGiocatori = new ArrayList<>();
        for (int i = 0; i < numeroGiocatori; i++) {
            System.out.print("Inserisci il nome del giocatore " + (i + 1) + ": ");
            nomiGiocatori.add(scanner.nextLine());
        }
        return nomiGiocatori;
    }
}
