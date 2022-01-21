import java.util.Scanner;

class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {      // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K, E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K, E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
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
            for (SLLNode<MapEntry<K, E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }

}

class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "<" + key + "," + value + ">";
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

public class PomoshniciteNaDedoMraz {
    public static String transcription(String zbor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zbor.length(); i++) {
            if (zbor.charAt(i) == 'c' || zbor.charAt(i) == 'C') {
                sb.append(zbor.charAt(i));
                if (zbor.charAt(++i) == 'h') continue;
            }
            if (zbor.charAt(i) == 'z' || zbor.charAt(i) == 'Z') {
                sb.append(zbor.charAt(i));
                if (zbor.charAt(++i) == 'h') continue;
            }
            if (zbor.charAt(i) == 's' || zbor.charAt(i) == 'S') {
                sb.append(zbor.charAt(i));
                if (zbor.charAt(++i) == 'h') continue;
            }
            sb.append(zbor.charAt(i));
        }

        return sb.toString();
    }

    public static boolean isPrime(int m) {
        boolean prime = true;
        for (int i = 0; i < Math.sqrt(m); i++) {
            if (m % i == 0) prime = false;
        }
        return prime;
    }

    public static int findM(int n) {
        int m = (int) (n / 0.7);
        while (!isPrime(m)) m++;
        return m;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String brDobriDeca = scan.nextLine();
        int N = Integer.parseInt(brDobriDeca);

        CBHT<String, String> table = new CBHT<>(57);
        for (int i = 0; i < N; i++) {
            String line = scan.nextLine();
            String[] niza = line.split(" ");
            String ime = niza[0];
            String podarok = niza[1];
            table.insert(ime, podarok);
        }

        String prebaraj = scan.nextLine();

        SLLNode<MapEntry<String, String>> node = table.search(transcription(prebaraj));

        if (node == null) {
            System.out.println("Nema poklon");
        } else {
            String poklon = node.element.value;
            System.out.println(poklon);
        }

    }
}
