package lab3_task2_CountWordPairs;

import java.util.Scanner;

public class CountWordPairs {

    //TODO: implement function
    public static int countWordPairs(String [] words) {
        int br=0;
        for(int i=0;i<words.length;i++)
        {
            for(int j=i+1;j<words.length;j++){
                if(words[i].charAt(0)==words[j].charAt(0))
                {
                    br++;
                }
            }
        }
        return br;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        String words[] = new String[N];

        for(int i=0;i<N;i++) {
            words[i] = input.next();
        }

        System.out.println(countWordPairs(words));

    }
}