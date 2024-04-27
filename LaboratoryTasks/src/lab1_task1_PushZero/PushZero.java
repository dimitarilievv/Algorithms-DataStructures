package lab1_task1_PushZero;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class PushZero {
    static void pushZerosToBeginning(int arr[], int n) {
        for(int i=0;i<n;i++){
            if(arr[i]==0)
            {
                System.out.print(arr[i]+" ");
            }
        }
        for(int i=0;i<n;i++){
            if(arr[i]!=0)
            {
                System.out.print(arr[i]+" ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] niza = new int[n];
        for (int i = 0; i < n; i++) {
            niza[i] = sc.nextInt();
        }
        System.out.println("Transformiranata niza e:");
        pushZerosToBeginning(niza,n);
    }
}