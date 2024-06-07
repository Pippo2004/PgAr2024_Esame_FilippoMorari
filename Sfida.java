import java.util.Scanner;

public class Sfida {
    public void eseguiSfida(int n, String[] paroleCriptate, String[] chiavi) {
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
