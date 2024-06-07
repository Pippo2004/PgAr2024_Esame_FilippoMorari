import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Giocatore {
    private String nome;
    private String ruolo;
    private int puntiFerita;
    private List<Carta> mano;

    public Giocatore(String nome, boolean isSceriffo) {
        this.nome = nome;
        this.ruolo = "";
        this.puntiFerita = isSceriffo ? 5 : 4; // Imposta 5 punti ferita per lo sceriffo, altrimenti 4
        this.mano = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public int getPuntiFerita() {
        return puntiFerita;
    }

    public void setPuntiFerita(int puntiFerita) {
        this.puntiFerita = puntiFerita;
    }

    public List<Carta> getMano() {
        return mano;
    }

    public void stampaMano() {
        System.out.println("Mano di " + nome + ":");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + mano.get(i).getNome());
        }
        System.out.println();
    }

    public void stampaInformazioni() {
        System.out.println("Nome: " + nome);
        System.out.println("Ruolo: " + ruolo);
        System.out.println("Punti ferita: " + puntiFerita);
        System.out.print("Mano: ");
        for (int i = 0; i < mano.size(); i++) {
            System.out.print("(" + (i + 1) + ") " + mano.get(i).getNome() + " ");
        }
        System.out.println();
    }


    public void aggiungiCarta(Carta carta) {
        mano.add(carta);
    }

    public Carta giocaCarta(int indice) {
        if (indice >= 0 && indice < mano.size()) {
            return mano.remove(indice);
        } else {
            System.out.println("Indice non valido.");
            return null;
        }
    }

    public Carta giocaCarta(Carta carta) {
        if (mano.contains(carta)) {
            mano.remove(carta);
            System.out.println(nome + " ha giocato la carta: " + carta.getNome());
            return carta;
        } else {
            System.out.println(nome + " non ha la carta nella mano.");
            return null;
        }
    }


    public void giocaCartaBang(Giocatore target) {
        Carta cartaBang = null;
        for (Carta carta : mano) {
            if (carta.getNome().equals("BANG!")) {
                cartaBang = carta;
                break;
            }
        }
        if (cartaBang != null) {
            mano.remove(cartaBang);
            System.out.println(nome + " ha giocato la carta BANG!");
            target.soccombe();
        } else {
            System.out.println(nome + " non ha la carta BANG! nella mano.");
        }
    }

    public void giocaCartaMancato() {
        Carta cartaMancato = null;
        for (Carta carta : mano) {
            if (carta.getNome().equals("Mancato!")) {
                cartaMancato = carta;
                break;
            }
        }
        if (cartaMancato != null) {
            mano.remove(cartaMancato);
            System.out.println(nome + " ha giocato la carta Mancato!");
        } else {
            System.out.println(nome + " non ha la carta Mancato! nella mano.");
        }
    }

    public boolean rispondiSfida() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(nome + ", sei stato sfidato! Accetti la sfida? (sì/no)");
        String risposta = scanner.nextLine().toLowerCase();

        if (risposta.equals("sì")) {
            System.out.println("Sfida accettata. Esegui la sfida.");
            return true;
        } else if (risposta.equals("no")) {
            System.out.println("Sfida rifiutata.");
            return false;
        } else {
            System.out.println("Risposta non valida. La sfida è stata rifiutata.");
            return false;
        }
    }

    public void eseguiSfida() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il numero di parole criptate e le relative chiavi di cifratura:");

        int n = scanner.nextInt();
        scanner.nextLine();  // Consuma il newline

        String[] paroleCriptate = new String[n];
        String[] chiavi = new String[n];

        System.out.println("Inserisci le parole criptate:");
        String[] paroleInput = scanner.nextLine().trim().split("\\s+");

        System.out.println("Inserisci le chiavi di cifratura:");
        String[] chiaviInput = scanner.nextLine().trim().split("\\s+");

        if (paroleInput.length != n || chiaviInput.length != n) {
            System.out.println("Errore: il numero di parole da decodificare e le chiavi di cifratura non corrisponde al numero di casi di test.");
            return;
        }

        for (int i = 0; i < n; i++) {
            paroleCriptate[i] = paroleInput[i];
            chiavi[i] = chiaviInput[i];
        }

        Sfida sfida = new Sfida(); // Creare un'istanza della classe Sfida
        sfida.eseguiSfida(n, paroleCriptate, chiavi); // Richiamare il metodo per eseguire la sfida

        System.out.println("Vuoi giocare una carta durante la sfida? (sì/no)");
        String risposta = scanner.nextLine().toLowerCase();
        if (risposta.equals("sì")) {
            System.out.println("Inserisci l'indice della carta da giocare:");
            int indiceCarta = scanner.nextInt();
            this.giocaCarta(indiceCarta);
        }
        scanner.nextLine();
    }

    public void pesca(Mazzo mazzo) {
        Carta cartaPescata = mazzo.pescaCarta();
        if (cartaPescata != null) {
            mano.add(cartaPescata);
            System.out.println(nome + " ha pescato una carta: " + cartaPescata.getNome());
        } else {
            System.out.println("Il mazzo è vuoto. Non ci sono più carte da pescare.");
        }
    }

    public void soccombe() {
        puntiFerita--;
        System.out.println(nome + " ha perso un punto ferita. Punti Ferita rimanenti: " + puntiFerita);
    }

    public void scartaCarteInEccesso() {
        int numeroCarteMassimo = puntiFerita;
        int carteInEccesso = mano.size() - puntiFerita;
        Scanner scanner = new Scanner(System.in);
        if (carteInEccesso > 0) {
            System.out.println("Hai troppe carte in mano! Devi scartarne " + carteInEccesso);
            while (carteInEccesso > 0) {
                System.out.println("Mano attuale:");
                stampaMano();
                System.out.println("Seleziona la carta da scartare (1-" + mano.size() + "): ");
                int indiceCartaDaScartare = scanner.nextInt();

                if (indiceCartaDaScartare >= 1 && indiceCartaDaScartare <= mano.size()) {
                    Carta cartaDaScartare = mano.remove(indiceCartaDaScartare - 1);
                    System.out.println(nome + " ha scartato la carta: " + cartaDaScartare.getNome());
                    carteInEccesso--;
                } else {
                    System.out.println("Indice non valido. Inserisci un numero tra 1 e " + mano.size() + ".");
                }
            }
        }
    }


    @Override
    public String toString() {
        return "Giocatore{" +
                "nome='" + nome + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", puntiFerita=" + puntiFerita +
                ", mano=" + mano +
                '}';
    }

}
