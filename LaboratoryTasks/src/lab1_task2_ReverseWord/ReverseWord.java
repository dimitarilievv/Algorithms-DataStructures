package lab1_task2_ReverseWord;

import java.util.Scanner;

public class ReverseWord {

    public static void printReversed(String word) {
        StringBuilder sb=new StringBuilder();
        for(int i=word.length()-1;i>=0;i--)
        {
            sb.append(word.charAt(i));
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=Integer.parseInt(sc.nextLine());
        // sc.nextLine();
        for(int i=0;i<n;i++)
        {
            String zbor=sc.nextLine();
            printReversed(zbor);
        }
    }
}