import java.util.ArrayList;
import java.util.Stack;

public class Calc extends MathD {

    char [] Delimiter = new char[6];
    int indexOfDelimiter;
    double rezalt;
    String [] TrigonometricFunction = new String[4];
    ArrayList<String> postfixTokens = new ArrayList<String>();

    Stack stackForAlgoritm = new Stack();

    void initializationDelimiter (){
        Delimiter[0] = '+';
        Delimiter[1] = '-';
        Delimiter[2] = '*';
        Delimiter[3] = '/';
        Delimiter[4] = '(';
        Delimiter[5] = ')';
    }
    void trigonometricFunction(){
        TrigonometricFunction[0] = "sin";
        TrigonometricFunction[1] = "cos";
        TrigonometricFunction[2] = "tg";
        TrigonometricFunction[3] = "ctg";
    }

    Calc(ArrayList<String> postfixT ){
        initializationDelimiter ();
        trigonometricFunction();
        postfixTokens = postfixT;
    }

    boolean isParametr(String str){
        boolean rez = true;
        String strDelimiter = "";
        for (int i=0; i<6; i++){
            strDelimiter = strDelimiter + Delimiter[i];
            if (str.equals(strDelimiter)){
                rez = false;
            }
            strDelimiter = "";
        }
        if (rez){
            for (int i=0; i<4; i++){
                if (str.equals(TrigonometricFunction[i])){
                    rez = false;
                }
            }
        }
        return rez;
    }

    boolean isTriginimetricFunc (String operat){
        boolean rez = false;
        for (int i=0; i<4; i++){
            if (operat.equals(TrigonometricFunction[i])){
                indexOfDelimiter = i;
                rez = true;
            }
        }
        return rez;
    }

    boolean isOperation(String operat){
        boolean rez = false;
        for (int i=0; i<4; i++){
            char [] strToArray = operat.toCharArray();
            if (strToArray[0] == Delimiter[i]){
                indexOfDelimiter = i;
                rez = true;
            }
        }
        return rez;
    }

    void count () {
        for (int i = 0; i < postfixTokens.size(); i++) {
            String token = postfixTokens.get(i);
            if (!token.equals("")) {
                if (isParametr(token)) {
                    Double d = null;
                    try {
                        d = new Double(postfixTokens.get(i));
                        stackForAlgoritm.push(d);
                    } catch (NumberFormatException e) {
                        System.err.println("postfixForm.get(i) = " + postfixTokens.get(i) + " Error!");
                    }
                } else {
                    if (isOperation(token)) {
                        Double d1, d2, d3;
                        d1 = (Double) stackForAlgoritm.pop();
                        d2 = (Double) stackForAlgoritm.pop();
                        switch (indexOfDelimiter) {
                            case (0):
                                d3 = d2 + d1;
                                break;
                            case (1):
                                d3 = d2 - d1;
                                break;
                            case (2):
                                d3 = d2 * d1;
                                break;
                            case (3):
                                d3 = d2 / d1;
                                break;
                            default:
                                d3 = null;
                                System.err.println("Error!");
                                break;
                        }
                        stackForAlgoritm.push(d3);
                    } else if (isTriginimetricFunc(token)) {
                        Double d1, d3;
                        d1 = (Double) stackForAlgoritm.pop();
                        switch (indexOfDelimiter) {
                            case (0):
                                d3 = Math.sin(d1);
                                break;
                            case (1):
                                d3 = Math.cos(d1);
                                break;
                            case (2):
                                d3 = Math.tan(d1);
                                break;
                            case (3):
                                d3 = 1 / Math.tan(d1);
                                break;
                            default:
                                d3 = null;
                                System.err.println("Error@");
                                break;
                        }
                        stackForAlgoritm.push(d3);
                    }
                }
            }
        }
        rezalt = (double) stackForAlgoritm.pop();
    }
    void print(){
        System.out.println("Result "+ rezalt);
    }
}
