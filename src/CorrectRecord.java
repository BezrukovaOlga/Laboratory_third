import java.util.ArrayList;

public class CorrectRecord extends MathS {

    ArrayList<String> Tokens = new ArrayList<String>();
    char [] Delimiter = new char[6];
    String [] TrigonometricFunction = new String[4];
    int flagBrackets=0;
    boolean errorBrackets = false;

    CorrectRecord (ArrayList<String> tokenFromME, char[] delimiterFromME, String [] tfFromME ){
        Tokens = tokenFromME;
        Delimiter = delimiterFromME;
        TrigonometricFunction = tfFromME;
    }

    boolean doubleOperation (){
        boolean rez = true;
        int flag1 = 0, flag2 = 0;
        for (int i = 0; i < Tokens.size(); i++) {
            if ( isDelimiter(Tokens.get(i)) ) {
                if (!Tokens.get(i).equals(Delimiter[4]) && !Tokens.get(i).equals(Delimiter[5]) ){
                    flag1 = flag1+1;
                    flag2=0;
                }
            } else if ( isTrFunc(Tokens.get(i)) ) {
                flag2 = flag2+1;
                flag1 = 0;
            } else  if (!Tokens.get(i).equals("")){
                flag1 = 0;
                flag2 = 0;
            }
            if (flag1 == 2 || flag2==2){
                rez = false;
            }

        }
        return rez;
    }

    boolean errorByDelimiter() {
        boolean rez = true;
        for (int i = 0; i < Tokens.size(); i++) {
            if (Tokens.get(i) == ""){
                System.out.println("i= "+i);
                rez=false;
            }
        }
        return rez;
    }

    boolean isNumber(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isDelimiter(String str){
        boolean rez = false;
        String strDelimiter = "";
        for (int i=0; i<6; i++){
            strDelimiter = strDelimiter + Delimiter[i];
            if (str.equals(strDelimiter)){
                if (i==4){
                    flagBrackets = flagBrackets+1;
                } else if (i==5){
                    flagBrackets=flagBrackets-1;
                    if (flagBrackets<0){
                        errorBrackets=true;
                    }
                }
                rez = true;
            }
            strDelimiter = "";
        }
        return rez;
    }

    boolean isTrFunc(String str){
        boolean rez = false;
        for (int i=0; i<4; i++){
            if (str.equals(TrigonometricFunction[i])){
                rez = true;
            }
        }
        return rez;
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
        if (rez){
            char [] strToArray = str.toCharArray();
            char a = 'a', z='z';
            if (! ( (int)a <= (int)strToArray[0] && (int)strToArray[0] <= (int)z ) ){
                rez = false;
            }
        }
        return rez;
    }

    boolean verify() {

        boolean rez = true;
        int i = -1;
        while (rez && i < Tokens.size()-1) {
            i = i + 1;
            while (Tokens.get(i) == "" && i < Tokens.size()-1){
                i=i+1;
            }
            if (Tokens.get(i) != ""){
                 if (!isNumber(Tokens.get(i)) && !isDelimiter(Tokens.get(i))
                          && !isTrFunc(Tokens.get(i)) && !isParametr(Tokens.get(i))) {
                     rez = false;
                 }
            }
        }
        if (flagBrackets != 0) {
            errorBrackets = true;
        }

        boolean rez1 = doubleOperation ();

        if (rez && !errorBrackets && rez1 ) {
            return true;
        } else {
            return false;
        }
    }
    void printTokens() {
        for (int i = 0; i < Tokens.size(); i++) {
            System.out.print(Tokens.get(i) + "");
        }
        System.out.println();
    }
}
