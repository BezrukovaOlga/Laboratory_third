
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class MathD {

    ArrayList<String> Tokens = new ArrayList<String>();

    char [] Delimiter = new char[6];
    String [] TrigonometricFunction = new String[4];

    ArrayList<String> postfixForm = new ArrayList<String>();
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

    boolean isDelimiter(char ch){
        boolean rez = false;
        for (int i=0; i<6; i++){
            if (ch == Delimiter[i]){
                rez = true;
            }
        }
        return rez;
    }
    String readFromFile() {
        File file = new File("input.txt");
        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            return line;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            String line = "error";
            return line;
        }
    }
    void split(){
        String str = readFromFile();
        char [] strToArray = str.toCharArray();
        String token="";
        for(int i = 0; i < strToArray.length; i++){
            if ( isDelimiter(strToArray[i]) ){
                Tokens.add(token);
                token = "";
                token = token+strToArray[i];
                Tokens.add(token);
                token = "";
            } else{
                token = token+strToArray[i];
            }
        }
        Tokens.add(token);
    }
    void printTokens() {
        for (int i = 0; i < Tokens.size(); i++) {
            System.out.print(Tokens.get(i) + " ");
        }
        System.out.println();
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

    int priority (String operation){
        int rez = 0;
        if ( (operation.equals(""+Delimiter[0])) || (operation.equals(""+Delimiter[1])) ){
            rez = 1;
        } else if ( (operation.equals(""+Delimiter[2])) || (operation.equals(""+Delimiter[3])) ){
            rez = 2;
        } else if ( (operation.equals(TrigonometricFunction[0])) || (operation.equals(TrigonometricFunction[1])) ||
                (operation.equals(TrigonometricFunction[2])) || (operation.equals(TrigonometricFunction[3])) ){
            rez = 3;
        }
        return rez;
    }

    void dragFlag(String flag){
        String strFromStack = "";
        while ( !strFromStack.equals(flag) ){
            strFromStack = (String)stackForAlgoritm.pop();
            if ( !strFromStack.equals("(") ){
                postfixForm.add(strFromStack);
            }
        }
    }

    void dragToken(String token){
        String strFromStack = fromStack();
        while ( ( !strFromStack.equals("(") || priority(strFromStack) >= priority(token)) && (!stackForAlgoritm.isEmpty()) ){
            postfixForm.add(strFromStack);
            strFromStack = (String)stackForAlgoritm.pop();
            strFromStack = fromStack();
        }

    }

    String fromStack(){
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
            String token = Tokens.get(i);
            if ( isParametr(token) ) {
                postfixForm.add(token);
            } else {
                if (token.equals("(")){
                    stackForAlgoritm.push(token);
                } else if (token.equals(")")){
                    dragFlag("(");
                }
                else {
                    String strFromStack = fromStack();
                    if ( stackForAlgoritm.isEmpty() || strFromStack.equals("(") || priority(token)>priority(strFromStack) ){
                        stackForAlgoritm.push(token);
                    } else{
                        dragToken(token);
                        stackForAlgoritm.push(token);
                    }
                }
            }
        }
        while (!stackForAlgoritm.isEmpty()){
            String strFromStack = (String)stackForAlgoritm.pop();
            postfixForm.add(strFromStack);
        }
    }
    void printPostfixTokens() {
        for (int i = 0; i < postfixForm.size(); i++) {
            System.out.print(postfixForm.get(i) + "");
        }
    }
    void printStack() {
        System.out.print("                          ");
        for (int i = 0; i < stackForAlgoritm.size(); i++) {
            System.out.print(stackForAlgoritm.get(i) + " ");
        }
        System.out.println();
    }
}
