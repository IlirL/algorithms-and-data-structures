import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluateExpression(String expression){
        // Vasiot kod tuka


//    Solve using recursion
        //saving these suckers in an array and saving the operation previously taken
        char previousOperation = '+';
        String currentNumberString = new String("");
        int[]  numbersToBeAdded = new int[expression.length()];
        int lengthOfArray = 0;

        int currentNumber = 1;
        for(int i = 0; i<expression.length(); i++)
        {


            if(expression.charAt(i) <='9' && expression.charAt(i) >='0')
            {
                currentNumberString+=expression.charAt(i);
            }
            else
            {
                if(expression.charAt(i) == '+')
                {
                    if(previousOperation == '*')
                    {
                        currentNumber*=Integer.parseInt(currentNumberString);
//                        System.out.println("Current number in this case: " + i + " is = "+ currentNumber);
                        numbersToBeAdded[lengthOfArray++] = currentNumber;
                        currentNumber = 1;
                    }


                    else
                    {
                    numbersToBeAdded[lengthOfArray++] = Integer.parseInt(currentNumberString);

                    }
                    currentNumberString = new String("");
                    previousOperation = '+';
                }

                else
                {
                    //*
                    currentNumber *= Integer.parseInt(currentNumberString);
                    currentNumberString = new String("");
                    previousOperation = '*';
                }
            }
            if(i == expression.length() - 1){
                if(previousOperation == '*')
                currentNumber*=Integer.parseInt(currentNumberString);

                else{
                    currentNumber = Integer.parseInt(currentNumberString);
                }

                numbersToBeAdded[lengthOfArray++] = currentNumber;

            }
        }
        int result = 0;
        for(int i = 0; i<lengthOfArray; i++)
        {
//            System.out.println("number to be added: " + numbersToBeAdded[i]);
            result+=numbersToBeAdded[i];
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}