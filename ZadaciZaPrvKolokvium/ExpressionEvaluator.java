import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        char [] izraz = expression.toCharArray();
        Stack <Integer> broevi = new Stack<Integer>();
        Stack <Character> plus = new Stack<Character>();
        Stack <Character> po = new Stack<Character>();
        for (int i=0; i<izraz.length;i++){
            if (izraz[i]=='+'){
                plus.push(izraz[i]);
                while (po.size()>0){
                    po.pop();
                    broevi.push(broevi.pop()*broevi.pop());
                }
            }
            if (izraz[i]=='*')
                po.push(izraz[i]);
            if (Character.isDigit(izraz[i])) {
                int broj=Integer.parseInt(String.valueOf(izraz[i]));
                while (i<izraz.length-1&&Character.isDigit(izraz[i+1])){
                    i++;
                    broj=broj*10+Integer.parseInt(String.valueOf(izraz[i]));
                }
                broevi.push(broj);
                if (i==izraz.length-1){
                    while (po.size()!=0){
                        broevi.push(broevi.pop()*broevi.pop());
                        po.pop();
                    }
                }
            }
        }
        int rezultat=0;
        while (broevi.size()!=0){
            int c=broevi.pop();
            rezultat+=c;
        }
        return rezultat;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}