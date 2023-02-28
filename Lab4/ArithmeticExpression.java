import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        Stack <Integer> broevi = new Stack<>();
        Stack <Character> operatori = new Stack<>();
        Stack <Character> zagradi = new Stack<>();
        zagradi.push(c[l]);
        int rezultat=0;
        for (int i=l+1; i<=r; i++) {
            if (c[i] == '(') {
                zagradi.push(c[i]);
                while (zagradi.size() > 1) {
                    i++;
                    if (c[i] == '(')
                        zagradi.push(c[i]);
                    if (c[i] == ')') {
                        zagradi.pop();
                        if (operatori.pop() == '+')
                            broevi.push(broevi.pop()+broevi.pop());
                        else
                            broevi.push(-broevi.pop()+broevi.pop());
                    }
                    if (c[i] == '+' || c[i] == '-')
                        operatori.push(c[i]);
                    if (Character.isDigit(c[i]))
                        broevi.push(Integer.parseInt(String.valueOf(c[i])));
                }
            } else {
                if (c[i] == '+' || c[i] == '-')
                    operatori.push(c[i]);
                if (Character.isDigit(c[i]))
                    broevi.push(Integer.parseInt(String.valueOf(c[i])));
                if (c[i] == ')') {
                    zagradi.pop();
                    if (operatori.pop() == '+')
                        rezultat += (broevi.pop()+broevi.pop());
                    else
                        rezultat += (-broevi.pop()+broevi.pop());
                }
            }
        }
        return rezultat;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
