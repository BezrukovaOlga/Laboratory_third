import java.util.ArrayList;
import java.util.Scanner;

public class GetValueOfVariables extends MathD {

    ArrayList<String> postfixTokens = new ArrayList<String>();
    char [] Delimiter = new char[6];
    String [] TrigonometricFunction = new String[4];

    ArrayList<WorkWithVariableAndValue> ListOfVariable = new ArrayList<WorkWithVariableAndValue>();

    GetValueOfVariables(ArrayList<String> postfixT){
        postfixTokens = postfixT;
        initializationDelimiter ();
        trigonometricFunction();
    }

    boolean isVariables (String token){
        boolean rez = isParametr(token);
        if (rez){
            char [] strToArray = token.toCharArray();
            char a = 'a', z='z';
            if (! ( (int)a <= (int)strToArray[0] && (int)strToArray[0] <= (int)z ) ){
                rez = false;
            }
        }
        return rez;
    }

    boolean ListHasVariable (String var){
        boolean rez = true;
        int i=0;
        while ( i<ListOfVariable.size() && !var.equals(ListOfVariable.get(i).variable) ){
            i=i+1;
        }
        if (i == ListOfVariable.size()){
            rez = false;
        }
        return rez;
    }

    void getValue(String var){
        System.out.print(var + " = ");
        Scanner in = new Scanner(System.in);
        double value = in.nextDouble();
        WorkWithVariableAndValue temporaryStorage = new WorkWithVariableAndValue(var, value);
        ListOfVariable.add(temporaryStorage);
    }

    void printValueOfVariables(){
        for (int i=0; i<ListOfVariable.size(); i++){
            System.out.println(ListOfVariable.get(i).value + "  =  " + ListOfVariable.get(i).variable );
        }
    }
    void findVariables (){
        for (int i=0; i<postfixTokens.size(); i++)
        {
            if ( !postfixTokens.get(i).equals("") ){
                if (isVariables(postfixTokens.get(i))){
                    if (!ListHasVariable( postfixTokens.get(i) )){
                        getValue( postfixTokens.get(i) );
                    }
                }
            }
        }
    }
}
