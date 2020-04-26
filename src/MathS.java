
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class MathS {

    ArrayList<String> Tokens = new ArrayList<String>();

    char [] Delimiter = new char[6];
    String [] TrigonometricFunction = new String[4];

    void initializationDelimiter (){
        Delimiter[0] = '+';
        Delimiter[1] = '-';
        Delimiter[2] = '*';
        Delimiter[3] = '/';
        Delimiter[4] = '(';
        Delimiter[5] = ')';
    }
    void initializationTrigonometricFunction (){
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
    String readFile () {
        String path = "/Users/anna/Documents/Java/Лаб3/F1.txt";
        File file = new File(path);
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
    void splitByTokens (){
        String str = readFile ();
        char [] strToArray = str.toCharArray();
        String token = "";
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
    }
    void printTokens() {
        for (int i = 0; i < Tokens.size(); i++) {
            System.out.print(Tokens.get(i) + " ");
        }
        System.out.println();
    }
}
