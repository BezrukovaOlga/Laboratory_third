import java.util.ArrayList;
import java.util.Stack;

public class PostfixForm extends MathS {

    ArrayList<String> postfixTokens = new ArrayList<String>();
    Stack stackForAlgoritm = new Stack();

    boolean isParametr(String str){
        boolean rez = true;
        String strDelimiter = "";
        for (int i=0; i<6; i++){
            strDelimiter = strDelimiter + Delimiter[i];
            if (str == strDelimiter){
                rez = false;
            }
            strDelimiter = "";
        }
        if (rez){
            for (int i=0; i<4; i++){
                if (str == TrigonometricFunction[i]){
                    rez = false;
                }
            }
        }
        return rez;
    }

    int priority (String operation){
        int rez = 0;
        if ( (operation == ""+Delimiter[0]) || (operation == ""+Delimiter[1]) ){
            rez = 1;
        } else if ( (operation == ""+Delimiter[0]) || (operation == ""+Delimiter[1]) ){
            rez = 2;
        } else if ( (operation == TrigonometricFunction[0]) || (operation == TrigonometricFunction[2]) ||
                (operation == TrigonometricFunction[3]) || (operation == TrigonometricFunction[4]) ){
            rez = 3;
        }
        return rez;
    }

    void drag (String flag){
        String strFromStack = "";
        while (strFromStack != flag){
            strFromStack = (String)stackForAlgoritm.pop();
            if (strFromStack != "("){
                postfixTokens.add(strFromStack);
            }
        }
    }

    void drag1 (String token){
        String strFromStack = pastTokenFromStack();
        while ( (strFromStack != "(" || priority(strFromStack) >= priority(token)) && (!stackForAlgoritm.isEmpty()) ){
            postfixTokens.add(strFromStack);
            strFromStack = (String)stackForAlgoritm.pop();
            strFromStack = pastTokenFromStack();
        }

    }

    String pastTokenFromStack (){
        if (!stackForAlgoritm.isEmpty()){
            String strFromStack = (String)stackForAlgoritm.pop();
            stackForAlgoritm.push(strFromStack);
            return strFromStack;
        }
        else {
            return "";
        }
    }
    void translitPostfix (){
        for (int i=0; i<Tokens.size(); i++){

            System.out.println("i = " + i);
            String token = Tokens.get(i);

            if ( isParametr(token) ) {
                postfixTokens.add(token);
            } else {
                if (token == "("){
                    stackForAlgoritm.push(token);
                } else if (token == ")"){
                    drag("(");
                }
                else {
                    String strFromStack = pastTokenFromStack ();
                    if ( stackForAlgoritm.isEmpty() || strFromStack=="(" || priority(token)>priority(strFromStack) ){
                        stackForAlgoritm.push(token);
                    } else{
                        drag1(token);
                        stackForAlgoritm.push(token);
                    }
                }
            }
        }
        while (!stackForAlgoritm.isEmpty()){
            String strFromStack = (String)stackForAlgoritm.pop();
            postfixTokens.add(strFromStack);
        }
    }
    void printPostfixTokens() {
        for (int i = 0; i < postfixTokens.size(); i++) {
            System.out.print(postfixTokens.get(i) + " ");
        }
        System.out.println();
    }

}
