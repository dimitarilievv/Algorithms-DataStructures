package lab6_task2_CheckXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;


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
interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}


class LinkedStack<E> implements Stack<E> {

    //Stekot e pretstaven na sledniot nacin: top e link do prviot jazol
    // na ednostrano-povrzanata lista koja sodrzi gi elementite na stekot .
    private SLLNode<E> top;

    public LinkedStack () {
        // Konstrukcija na nov, prazen stek.
        top = null;
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (top == null);
    }

    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (top == null)
            throw new NoSuchElementException();
        return top.element;
    }

    public void clear () {
        // Go prazni stekot.
        top = null;
    }

    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        top = new SLLNode<E>(x, top);
    }

    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (top == null)
            throw new NoSuchElementException();
        E topElem = top.element;
        top = top.succ;
        return topElem;
    }

}

public class CheckXML {

    public static byte ifBalanced(String []rows, int lenght) {
        LinkedStack<String> stack = new LinkedStack<>();

        for (int i = 0; i < lenght; i++) {
            char firstChar = rows[i].charAt(0);

            if (firstChar == '[') {
                if (rows[i].charAt(1) != '/') {
                    stack.push(rows[i].substring(1));
                } else {
                    if (stack.isEmpty()) {
                        return 0;
                    }

                    String expectedTag = rows[i].substring(2);
                    if (!stack.pop().equals(expectedTag)) {
                        return 0;
                    }
                }
            }
        }
        if(stack.isEmpty())
            return 1;
        return 0;
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] rows = new String[n];

        for(int i=0; i<n; i++)
            rows[i] = br.readLine();

        int valid;

        valid = ifBalanced(rows,n);

        System.out.println(valid);

        br.close();
    }
}