public class Main {
    public static void main(String[] args) {

        MathD start = new MathD();
        start.initializationDelimiter();
        start.trigonometricFunction();
        start.readFromFile();
        start.split();
        start.printTokens();
        CorrectRecord correctRecord = new CorrectRecord(start.Tokens, start.Delimiter, start.TrigonometricFunction);
        if (correctRecord.verify()){
            start.translitPostfix();
            GetValueOfVariables getValueOfVariables = new GetValueOfVariables(start.postfixForm);
            getValueOfVariables.findVariables();
            getValueOfVariables.printValueOfVariables();
            ChangeNumber changeNumber = new ChangeNumber(start.postfixForm, getValueOfVariables.ListOfVariable);
            changeNumber.supersed();
            Calc calc = new Calc(changeNumber.postfixTokens);
            calc.count();
            calc.print();
        } else{
            System.out.println("Error!");
        }
    }
}
