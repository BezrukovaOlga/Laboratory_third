import java.util.ArrayList;

public class ChangeNumber extends MathD {

    ArrayList<String> postfixTokens = new ArrayList<String>();
    ArrayList<WorkWithVariableAndValue> ListOfVariable = new ArrayList<WorkWithVariableAndValue>();
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
    void trigonometricFunction(){
        TrigonometricFunction[0] = "sin";
        TrigonometricFunction[1] = "cos";
        TrigonometricFunction[2] = "tg";
        TrigonometricFunction[3] = "ctg";
    }

    ChangeNumber(ArrayList<String> postfixT, ArrayList<WorkWithVariableAndValue> ListOfVar){
        postfixTokens = postfixT;
        ListOfVariable = ListOfVar;
        initializationDelimiter();
        trigonometricFunction();
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

    boolean isParametr1 (String str){
        boolean rez = isParametr( str );
        if (rez){
            char [] strToArray = str.toCharArray();
            char a = 'a', z='z';
            if (!( (int)a <= (int)strToArray[0] && (int)strToArray[0] <= (int)z )){
                rez = false;
            }
        }
        return rez;
    }

    double searchingForValue(String param){
        double rez = 0;
        int i=0;
        while ( ! ListOfVariable.get(i).variable.equals(param) ){
            i=i+1;
        }
        rez = ListOfVariable.get(i).value;
        return rez;
    }

    void supersed(){
        for (int i=0; i<postfixTokens.size(); i++){

            if ( !postfixTokens.get(i).equals("") ) {

                if (isParametr1(postfixTokens.get(i))) {
                    double num = searchingForValue(postfixTokens.get(i));
                    String str = Double.toString(num);
                    postfixTokens.set(i, str);
                }
            }
        }
    }
    void print(){
        for (int i = 0; i < postfixTokens.size(); i++) {
            System.out.print(postfixTokens.get(i) + "");
        }
    }

}
