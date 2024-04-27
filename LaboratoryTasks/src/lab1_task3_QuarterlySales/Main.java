package lab1_task3_QuarterlySales;

import java.util.Arrays;
import java.util.Scanner;

class QuarterlySales {
    private int numOfSales;
    private int[] revenues;
    private int quarterNo;

    public QuarterlySales(int numOfSales, int[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }

    @Override
    public String toString() {
        return "" + sumRevenues();
    }

    public int getNumOfSales() {
        return numOfSales;
    }

    public int[] getRevenues() {
        return revenues;
    }

    public int getQuarterNo() {
        return quarterNo;
    }

    public int sumRevenues() {
        int sum = 0;
        for (int i = 0; i < numOfSales; i++) {
            sum += revenues[i];
        }
        return sum;
    }
}

class SalesPerson {
    private String name;
    private QuarterlySales[] quarters;

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append("   ");
        for (int i = 0; i < quarters.length; i++) {
            sb.append(quarters[i].sumRevenues());
            sb.append("   ");
        }
        sb.append(sumSales());
        return sb.toString();
    }


    public String getName() {
        return name;
    }

    public QuarterlySales[] getQuarters() {
        return quarters;
    }

    public int sumSales() {
        int sum = 0;
        for (int i = 0; i < quarters.length; i++) {
            sum += quarters[i].sumRevenues();
        }
        return sum;
    }
}


public class Main {

    public static SalesPerson salesChampion(SalesPerson[] arr) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].sumSales() > max) {
                max = arr[i].sumSales();
                index = i;
            }
        }
        return arr[index];
    }

    public static void table(SalesPerson[] arr) {
        System.out.println("SP   1   2   3   4   Total");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "");
        }
        System.out.println("");
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = Integer.parseInt(input.nextLine());
        SalesPerson[] arr = new SalesPerson[n];
        String ime = new String();
        int brProdazbi = 0;
        for (int i = 0; i < n; i++) {
            QuarterlySales[] kvartali = new QuarterlySales[4];
            ime = input.nextLine();
            for (int j = 0; j < kvartali.length; j++) {
                brProdazbi = Integer.parseInt(input.nextLine());
                int[] niza = new int[brProdazbi];
                String[] SNiza = input.nextLine().split(" ");
                for (int k = 0; k < brProdazbi; k++) {
                    niza[k] = Integer.parseInt(SNiza[k]);
                }
                kvartali[j] = new QuarterlySales(brProdazbi, niza, j + 1);
            }
            arr[i] = new SalesPerson(ime, kvartali);
        }
        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());

    }
}