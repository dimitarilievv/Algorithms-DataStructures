package lab6_task3_MVR;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//not correct for all the test cases
class Gragjanin {
    String imePrezime;
    int lKarta;
    int pasos;
    int vozacka;

    public Gragjanin(String imePrezime, int lKarta, int pasos, int vozacka) {
        this.imePrezime = imePrezime;
        this.lKarta = lKarta;
        this.pasos = pasos;
        this.vozacka = vozacka;
    }
}

public class MVR {

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        int N = Integer.parseInt(br.nextLine());
        List<Gragjanin> people = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
            people.add(covek);
        }

        br.close();

        List<Gragjanin> result = processDocuments(people);

        // Print the names of people in the order they finish extracting documents
        for (Gragjanin person : result) {
            System.out.println(person.imePrezime);
        }
    }

    private static List<Gragjanin> processDocuments(List<Gragjanin> people) {
        List<Gragjanin> result = new ArrayList<>();

        // Process people in the order they finish extracting documents
        for (Gragjanin person : people) {
            if (person.lKarta == 1 && !result.contains(person)) {
                result.add(person);
            }
        }

        for (Gragjanin person : people) {
            if (person.pasos == 1 && !result.contains(person)) {
                result.add(person);
            }
        }

        for (Gragjanin person : people) {
            if (person.vozacka == 1 && !result.contains(person)) {
                result.add(person);
            }
        }

        return result;
    }


}
