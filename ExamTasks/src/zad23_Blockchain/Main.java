package zad23_Blockchain;

import java.util.*;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if(after==last){
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if(before == first){
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    public E delete(DLLNode<E> node) {
        if(node==first){
            deleteFirst();
            return node.element;
        }
        if(node==last){
            deleteLast();
            return node.element;
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp + "<->";
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }


}

public class Main
{
    public static void main(String []argv)
    {
        DLL<Block> lista= new DLL<Block>();
        Scanner in = new Scanner(System.in);
        int izbor;
        do
        {
            System.out.println("0 - Exit");
            System.out.println("1 - CreateRoot");
            System.out.println("2 - AddBlock");
            System.out.println("3 - DeleteBlock");
            System.out.println("4 - isChainValid");
            izbor = in.nextInt();
            switch(izbor)
            {
                case 1:
                {
                    if(lista.getFirst()==null)
                    {
                        System.out.println("Vnesi podatok za root jazolot!");
                        String data = in.next();
                        Block nov = new Block(0,data);
                        nov.previousHash =0;
                        nov.hash= (nov.index + nov.data + nov.previousHash).hashCode();
                        lista.insertFirst(nov);
                        break;
                    }
                    else
                    {
                        System.out.println("Veke e kreiraj root jazol!");
                        break;
                    }
                }
                case 2:
                {
                    if(lista.getFirst()!=null)
                    {
                        System.out.println("Vnesi podatok za root jazolot!");
                        String data = in.next();
                        Block nov = new Block(lista.getLast().element.index + 1,data);
                        nov.previousHash =lista.getLast().element.hash;
                        nov.hash= (nov.index + nov.data + nov.previousHash).hashCode();
                        lista.insertLast(nov);
                        break;
                    }
                    else
                    {
                        System.out.println("Nemate root blok (jazol)!");
                        break;
                    }
                }
                case 3:
                {
                    if(lista.getFirst()==null)
                    {
                        System.out.println("Nema blockovi!");
                        break;
                    }
                    else
                    {
                        System.out.println("Vnesete hash na block koj sto sakate da go izbrisete");
                        int hash = in.nextInt();
                        DLLNode<Block> dvizi = lista.getFirst();
                        while(dvizi!=null)
                        {
                            if(dvizi.element.hash==hash)
                            {

                                lista.delete(dvizi);
                                dvizi = dvizi.succ;
                                while(dvizi!=null)
                                {
                                    if(dvizi.pred!=null)
                                    {
                                        dvizi.element.index= dvizi.pred.element.index + 1;
                                        dvizi.element.previousHash = dvizi.pred.element.hash;
                                    }
                                    else//se izbrisal prviot
                                    {
                                        dvizi.element.index = 0;
                                        dvizi.element.previousHash =0;
                                    }
                                    dvizi.element.hash = (dvizi.element.index + dvizi.element.data + dvizi.element.previousHash).hashCode();
                                    dvizi = dvizi.succ;
                                }
                                break;
                            }
                            dvizi = dvizi.succ;
                        }
                    }


                }

                case 4:
                {
                    boolean flag = true;
                    DLLNode<Block> dvizi = lista.getFirst();
                    if(dvizi.element.previousHash==0)
                    {
                        dvizi = dvizi.succ;
                        while(dvizi!=null)
                        {
                            if(dvizi.element.previousHash!=dvizi.pred.element.hash)
                            {
                                flag = false;
                                break;
                            }
                            dvizi = dvizi.succ;
                        }
                    }
                    else
                    {
                        flag = false;
                    }
                    if (flag==false)
                    {
                        System.out.println("Ne e validen sindzirot!");
                        break;
                    }
                    else
                    {
                        System.out.println("Sindzirot e validen!");
                        break;
                    }


                }
            }

        }while(izbor!=0);
    }
}

class Block
{
    int index;
    String data;
    int hash;
    int previousHash;

    public Block(int index,String data)
    {
        this.index = index;
        this.data = data;
    }
}
