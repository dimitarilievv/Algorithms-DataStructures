package lab7_task2_Preveduvac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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
        return "<" + key + "," + value + ">";
    }
}


class OBHT<K extends Comparable<K>,E> {

    private MapEntry<K,E>[] buckets;

    static final int NONE = -1;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static final MapEntry former = new MapEntry(null, null);

    private int occupancy = 0;

    @SuppressWarnings("unchecked")
    public OBHT (int m) {
        buckets = (MapEntry<K,E>[]) new MapEntry[m];
    }

    private int hash (K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public MapEntry<K,E> getBucket(int i){
        return buckets[i];
    }


    public int search (K targetKey) {
        int b = hash(targetKey);
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else
                b = (b + 1) % buckets.length;
        }
    }


    public void insert (K key, E val) {
        MapEntry<K,E> newEntry = new MapEntry<K,E>(key, val);
        int b = hash(key);
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            } else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            } else
                b = (b + 1) % buckets.length;
        }
    }


    @SuppressWarnings("unchecked")
    public void delete (K key) {
        int b = hash(key);
        for (;;) {
            MapEntry<K,E> oldEntry = buckets[b];
            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;
                return;
            } else{
                b = (b + 1) % buckets.length;
            }
        }
    }


    public String toString () {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            if (buckets[i] == null)
                temp += "\n";
            else if (buckets[i] == former)
                temp += "former\n";
            else
                temp += buckets[i] + "\n";
        }
        return temp;
    }


    public OBHT<K,E> clone () {
        OBHT<K,E> copy = new OBHT<K,E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K,E> e = buckets[i];
            if (e != null&&e != former)
                copy.buckets[i] = new MapEntry<K,E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
    }
}



public class Preveduvac {
    public static void main (String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        int N=Integer.parseInt(sc.nextLine());
        OBHT<String,String> table=new OBHT<>(N*2);
        for(int i=0;i<N;i++){
            String []zborovi=sc.nextLine().split(" ");
            String mkd= zborovi[0];
            String eng= zborovi[1];
            table.insert(eng,mkd);
        }
        while(true){
            String word=sc.nextLine();
            if(word.equalsIgnoreCase("KRAJ")){
                break;
            }
            int index=table.search(word);
            if(index!=OBHT.NONE){
                MapEntry<String,String> entry=table.getBucket(index);
                System.out.println(entry.value);
            }else{
                System.out.println("/");
            }
        }
    }
}