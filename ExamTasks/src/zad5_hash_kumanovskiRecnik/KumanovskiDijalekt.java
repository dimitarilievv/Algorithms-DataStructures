package zad5_hash_kumanovskiRecnik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        // Napishete ja vie HASH FUNKCIJATA
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

    public void insert(K key, E val) {        // Insert the entry <key, val> into this CBHT.
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

public class KumanovskiDijalekt {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(
                System.in));
        int N = Integer.parseInt(br.readLine());
        CBHT<String, String> table = new CBHT<>(2 * N);
        String rechnik[] = new String[N];
        for (int i = 0; i < N; i++) {
            rechnik[i] = br.readLine();
            String[] parts = rechnik[i].split("\\s+");
            table.insert(parts[0].toLowerCase(), parts[1].toLowerCase());
        }

        String tekst = br.readLine();
        String []zborovi=tekst.split("\\s+");
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < zborovi.length; i++) {
            if (zborovi[i].contains(",")) {
                zborovi[i] = zborovi[i].replace(",", "");
            }
            SLLNode<MapEntry<String,String>> dvizi=table.search(zborovi[i]);
            if(dvizi != null && dvizi.element.key.equalsIgnoreCase(zborovi[i])){
                sb.append(dvizi.element.value).append(" ");
            }else{
                sb.append(zborovi[i]).append(" ");
            }
        }
        System.out.println(sb);
        /*
        if(rechnik.length==0){
            System.out.println(tekst);
            return;
        }

        //Vasiot kod tuka
        CBHT<String,String> recnik = new CBHT<>(N*2-1);
        for (int i = 0; i < rechnik.length; i++) {
            String[] parts = rechnik[i].split(" ");
            recnik.insert(parts[0].toLowerCase(),parts[1].toLowerCase());
        }
        String[] zborovi = tekst.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zborovi.length; i++) {
            String tmp = zborovi[i];
            if(!Character.isLetter(tmp.charAt(tmp.length()-1))){
                tmp=tmp.substring(0,tmp.length()-1);
            }
            if(recnik.search(tmp.toLowerCase())!=null){
                String najden = recnik.search(tmp.toLowerCase()).element.value;
                if(Character.isUpperCase(zborovi[i].charAt(0))){
                    sb.append(Character.toUpperCase(najden.charAt(0)));
                    najden=najden.substring(1,najden.length());
                }
                sb.append(najden);
            }
            else sb.append(tmp);
            if(!Character.isLetter(zborovi[i].charAt(zborovi[i].length()-1)))
                sb.append((zborovi[i].charAt(zborovi[i].length()-1)));
            sb.append(" ");
        }
        System.out.println(sb.toString().trim());
         */
    }
}
