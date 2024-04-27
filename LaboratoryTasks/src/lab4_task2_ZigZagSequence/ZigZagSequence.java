package lab4_task2_ZigZagSequence;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        if (a.length <= 1) {
            return a.length; // Sequence of only one non-zero number is considered a zigzag sequence
        }

        int maxLength = 1;
        int currentLength = 1;

        for (int i = 1; i < a.length; i++) {
            if ((a[i] > 0 && a[i - 1] < 0) || (a[i] < 0 && a[i - 1] > 0)) {
                // Current pair is zigzag
                currentLength++;
                maxLength = Math.max(maxLength, currentLength);
            } else {
                // Current pair is not zigzag, reset the length
                currentLength = 1;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i = 0; i < N; i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();
    }
}
