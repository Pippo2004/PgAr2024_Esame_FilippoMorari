import java.util.List;
import java.util.Scanner;

public class Turno {
    private Giocatore giocatore;

    public Turno(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public void eseguiTurno(Mazzo mazzo, List<Giocatore> giocatori) {
        System.out.println("Turno di " + giocatore.getNome());

        giocatore.pesca(mazzo);
        giocatore.pesca(mazzo);

        giocatore.stampaInformazioni();

        boolean fineTurno = false;

        while (!fineTurno) {
            System.out.println("1. Gioca carta");
            System.out.println("2. Sfida un giocatore");
            System.out.println("3. Fine turno");

            Scanner scanner = new Scanner(System.in);
            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            switch (scelta) {
                case 1:
                    giocatore.stampaMano(); // Mostra le carte attualmente in mano al giocatore
                    System.out.println("Seleziona l'indice della carta da giocare:");
                    int indiceCartaDaGiocare = scanner.nextInt();
                    scanner.nextLine(); // Consuma il newline
                    giocatore.giocaCarta(indiceCartaDaGiocare - 1); // Gioca la carta selezionata
                    break;
                case 2:
                    sfidaGiocatore(giocatori);
                    break;
                case 3:
                    fineTurno = true;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    private void sfidaGiocatore(List<Giocatore> giocatori) {
        System.out.println("Scegli il giocatore da sfidare:");
        for (int i = 0; i < giocatori.size(); i++) {
            System.out.println((i + 1) + ". " + giocatori.get(i).getNome());
        }
        Scanner scanner = new Scanner(System.in);
        int sceltaGiocatore = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline

        if (sceltaGiocatore >= 1 && sceltaGiocatore <= giocatori.size()) {
            Giocatore avversario = giocatori.get(sceltaGiocatore - 1);
            System.out.println(avversario.getNome() + ", sei stato sfidato! Accetti la sfida? (sì/no)");
            String risposta = scanner.nextLine().toLowerCase();

            if (risposta.equals("sì")) {
                // Codice per eseguire la sfida
                eseguiSfida();
            } else if (risposta.equals("no")) {
                System.out.println("Sfida rifiutata.");
            } else {
                System.out.println("Risposta non valida. La sfida è stata rifiutata.");
            }
        } else {
            System.out.println("Scelta non valida.");
        }
    }

    private void eseguiSfida() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();  // Consuma la linea rimanente

        String[] paroleCriptate = new String[n];
        String[] chiavi = new String[n];

        String[] paroleInput = scanner.nextLine().trim().split("\\s+");
        String[] chiaviInput = scanner.nextLine().trim().split("\\s+");

        if (paroleInput.length != n || chiaviInput.length != n) {
            System.out.println("Errore: il numero di parole da decodificare e le chiavi di cifratura non corrisponde al numero di casi di test.");
            return;
        }

        for (int i = 0; i < n; i++) {
            paroleCriptate[i] = paroleInput[i];
            chiavi[i] = chiaviInput[i];
        }

        for (int i = 0; i < n; i++) {
            String decodifica = decifra(paroleCriptate[i], chiavi[i]);
            System.out.println(decodifica);
        }
    }

    private String decifra(String criptata, String chiave) {
        StringBuilder decifrata = new StringBuilder();
        String chiaveEstesa = estendiChiavePerCorrispondenzaLunghezza(chiave, criptata.length());
        for (int i = 0; i < criptata.length(); i++) {
            char carattereCriptato = criptata.charAt(i);
            char carattereChiave = chiaveEstesa.charAt(i);
            char carattereDecifrato = (char) ((carattereCriptato - carattereChiave + 26) % 26 + 'a');
            decifrata.append(carattereDecifrato);
        }
        return decifrata.toString();
    }

    private String estendiChiavePerCorrispondenzaLunghezza(String chiave, int lunghezza) {
        StringBuilder chiaveEstesa = new StringBuilder(lunghezza);
        int lunghezzaChiave = chiave.length();
        for (int i = 0; i < lunghezza; i++) {
            chiaveEstesa.append(chiave.charAt(i % lunghezzaChiave));
        }
        return chiaveEstesa.toString();
    }
}
