import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "(" + key + "," + value + ")";
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                curr.element = newEntry;
                return;
            }
        }
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

public class DedoMrazAdresi {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String, String> dobriDeca = new CBHT<String, String>(N);// Vie ja odreduvate goleminata na tabelata

        // vo imeDobriDeca se zachuvuvaat iminjata na dobrite deca
        String[] imeDobriDeca = new String[N];
        // tuka se zachuvuvaat soodvetnite adresi na decata
        String[] adresaDobriDeca = new String[N];
        String pom,pom1, pom2;
        for (int i = 0; i < N; i++) {
            pom1 = br.readLine();
            pom2 = br.readLine();
            imeDobriDeca[i] = pom1;
            adresaDobriDeca[i] = pom2;
        }

        int M = Integer.parseInt(br.readLine());
        CBHT<String, String> promenetiUlici = new CBHT<String, String>(M);// Vie ja odreduvate goleminata na tabelata

        // vo staroIme se zachuvuvaat starite iminja na ulici
        String[] staroIme = new String[M];
        // tuka se zachuvuvaat noite iminja na ulici
        String[] novoIme = new String[M];
        for (int i = 0; i < M; i++) {
            pom = br.readLine();
            String[] del = pom.split(" ");
            staroIme[i] = del[0];
            novoIme[i] = del[1];
        }

        //tuka se zapishuva imeto na deteto shto treba da se proveri
        String deteZaProverka = br.readLine();

        /*
         *
         * Vashiot kod ovde
         *
         */

        for(int i = 0; i<N; i++)
        {
            dobriDeca.insert(imeDobriDeca[i], adresaDobriDeca[i]);
        }
        for(int i = 0; i<M; i++)
        {
            promenetiUlici.insert(staroIme[i], novoIme[i]);
        }

        SLLNode<MapEntry<String, String>> node = dobriDeca.search(deteZaProverka);
        String wholeUlica = node.element.value;
        String ulica = node.element.value .split("\\s+")[0];
        SLLNode<MapEntry<String, String>> changedUlica = promenetiUlici.search(ulica);

        if(changedUlica == null)
            System.out.println(node.element.value);
        else
        {
            String[] strings = wholeUlica.split("\\s+");
            strings[0] = changedUlica.element.value;
            wholeUlica = Arrays.stream(strings).map(singleElement -> singleElement).collect(Collectors.joining(" "));
            System.out.println(wholeUlica);
        }

    }
}