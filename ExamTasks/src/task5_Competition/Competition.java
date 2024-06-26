package task5_Competition;

/*Податоците за натпреварувачите на натпреварите по математика се чуваат во две еднострано поврзани листи.

Во првата листа се чуваат податоците за натпреварувачите кои учествувале на натпреварот минатата година,
а во втората листа се чуваат податоците за натпреварувачите кои учествувале во тековната година.

И кај двете листи, во секој од јазлите се чуваат број за идентификација (id) и поените (децимален број)
 кои ги освоил соодветниот натпреварувач.

Потребно е да се изберат натпреварувачи, за тековната година, кои ќе продолжат на следното меѓународно натпреварување.

За таа цел, потребно е од листата на натпреварувачи за тековната година да се отстранат (избришат) сите
 натпреварувачи кои освоиле помалку поени од просечниот број на поени освоени на натпреварот од минатата година.



Влез:

Во првиот ред е даден бројот на натпреварувачи од минатата година.

Во вториот ред е даден бројот на натпреварувачи од тековната година.

Во следните редови се дадени паровите податоци за секој натпреварувач, одделени со празно место, во формат id поени.

Излез:

Во еден ред id на сите натпреварувачи кои ќе продолжат на меѓународното натпреварување.



Забелешка: Даден е целосниот код на структурата којашто треба да се користи.
Дадена е и тест класата Competition.java, со целосно имплементиран input и output.
 Потребно е да се менува само во рамки на void competition(SLL<Competitor> prevYearCompetitors,
  SLL<Competitor> currYearCompetitors) функцијата. Притоа, бришењето треба да биде имплементирано како бришење на цел јазел!

For example:

Input
2
2
825 10.65
484 68.21
773 70.12
789 23.90

Result
773
*/

import java.util.Scanner;

class Competitor {
    private int id;
    private double points;

    public Competitor(int id, double points) {
        this.id = id;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public double getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
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

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
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
}

public class Competition {

    //todo: implement function
    public static void competition(SLL<Competitor> prevYearCompetitors, SLL<Competitor> currYearCompetitors) {
        SLLNode<Competitor>dvizi1=prevYearCompetitors.getFirst();
        double sum=0;
        while(dvizi1!=null){
            sum+=dvizi1.element.getPoints();
            dvizi1= dvizi1.succ;
        }
        double avg=sum/prevYearCompetitors.length();
        SLLNode<Competitor> dvizi2=currYearCompetitors.getFirst();
        while(dvizi2!=null){
            if(dvizi2.element.getPoints()<avg){
                currYearCompetitors.delete(dvizi2);
            }
            dvizi2=dvizi2.succ;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int prevYearCount = Integer.parseInt(scanner.nextLine());
        int currYearCount = Integer.parseInt(scanner.nextLine());
        SLL<Competitor> prevYearCompetitors = new SLL<Competitor>();
        SLL<Competitor> currYearCompetitors = new SLL<Competitor>();

        for (int i = 0; i < prevYearCount; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            prevYearCompetitors.insertLast(new Competitor(Integer.parseInt(parts[0]), Double.parseDouble(parts[1])));
        }

        for (int i = 0; i < currYearCount; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            currYearCompetitors.insertLast(new Competitor(Integer.parseInt(parts[0]), Double.parseDouble(parts[1])));
        }

        competition(prevYearCompetitors, currYearCompetitors);
        System.out.println(currYearCompetitors.toString());
    }
}