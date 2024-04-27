package lab4_task1_ArithmeticExpression;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        // Ako izrazot e samo eden karakter (broj)
        if (l == r) {
            return Character.getNumericValue(c[l]);
        }

        // Ako izrazot e vo oblik (A+B) ili (A-B)
        if (c[l] == '(' && c[r] == ')') {
            int i = l + 1; // Preskoci go otvoranjeto na zagrada
            int j = r - 1; // Preskoci go zatvoranjeto na zagrada
            int operatorIndex = -1;
            int parenthesesCount = 0;

            // Proveri gi znacite vo zagradata
            while (i <= j) {
                if (c[i] == '(') {
                    parenthesesCount++;
                } else if (c[i] == ')') {
                    parenthesesCount--;
                }

                if ((c[i] == '+' || c[i] == '-') && parenthesesCount == 0) {
                    operatorIndex = i;
                    break;
                }
                i++;
            }

            // Ako najdeme operator vo zagradata
            if (operatorIndex != -1) {
                int leftOperand = presmetaj(c, l + 1, operatorIndex - 1);
                int rightOperand = presmetaj(c, operatorIndex + 1, r - 1);

                // Izvrsi gi aritmetickite operacii
                if (c[operatorIndex] == '+') {
                    return leftOperand + rightOperand;
                } else {
                    return leftOperand - rightOperand;
                }
            }
        }

        // Vo ostanato, vrati vrednosta na brojot
        return Character.getNumericValue(c[l]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length - 1);
        System.out.println(rez);

        br.close();
    }
}
