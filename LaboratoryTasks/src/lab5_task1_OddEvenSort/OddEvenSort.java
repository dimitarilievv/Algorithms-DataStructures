package lab5_task1_OddEvenSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class OddEvenSort {
    static void oddEvenSort(int a[], int n) {
        int[] neparni = Arrays.stream(a).filter(x -> x % 2 != 0).toArray();
        int[] parni = Arrays.stream(a).filter(x -> x % 2 == 0).toArray();
        Arrays.sort(neparni);
        Arrays.sort(parni);
        int index = 0;
        for (int i = 0; i < neparni.length; i++) {
            a[index++] = neparni[i];
        }
        for (int i = parni.length - 1; i >= 0; i--) {
            a[index++] = parni[i];
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String[] pom = s.split(" ");
        int[] a = new int[n];
        for (i = 0; i < n; i++)
            a[i] = Integer.parseInt(pom[i]);
        oddEvenSort(a, n);
        for (i = 0; i < n - 1; i++)
            System.out.print(a[i] + " ");
        System.out.print(a[i]);
    }
}