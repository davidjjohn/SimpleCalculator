// Simple stack example
// Evaluate postfix expressions such as 3 2 4 * +
// David John
// February 2020
// February 2021 -- modified and better comments

import java.util.Stack;
import java.util.EmptyStackException;
import java.util.ArrayList;

import java.util.Scanner;


public class Main {

    // class variable myStack to hold integer inputs and
    // computed expressions
    private static Stack<Integer> myStack;

    // Operators holds the supported operators
    private static ArrayList<String> Operators = new ArrayList<String>();

    public static void main(String[] args) {


        // instantiate myStack and keyboard
        myStack = new Stack<Integer>();
        Scanner keyboard = new Scanner(System.in);

        // setup supported Operators
        Operators.add("+");
        Operators.add("-");
        Operators.add("*");
        Operators.add("/");
        Operators.add("?");

        // prompt for  a single line of input
        // and process expression tokens
        System.out.print("Enter expression: ");
        String userinput = keyboard.nextLine();
        String[] usertokens = userinput.split(" ");
        for (String myToken : usertokens) {

            // try to convert myToken to an integer, if so
            // push it; otherwise consider it an operator
            try {
                int myVal = Integer.parseInt(myToken);
                myStack.push(myVal);
            } catch (NumberFormatException e) {
                Evaluation(myToken);
            }
        }


        // if stack size is 1 then write out answer; otherwise
        // complain and abort
        if (myStack.size()==1) {
            // naively print out answer
            System.out.println("The value of the expression is: "+myStack.pop());
        }
        else {
            System.out.println("ILLEGAL EXPRESSION ENTERED");
            System.exit(17);
        }

    }

    // using operator token compute the expression
    // not elegant, but ok
    public static void Evaluation (String token){

        // local variables to hold arguments
        int val1 =0;
        int val2= 0;
        int val3 = 0;

        // check to make sure operator is one supported
        if (!Operators.contains(token)){
            System.out.println("Unsupported operator <"+token+">");
            System.exit(44);
        }

        // get arguments from operand stack
        try{
            val1 = myStack.pop();
            val2 = myStack.pop();
        } catch (EmptyStackException e){
            System.out.println("  Not enough arguments for operator <"+token+">");
            System.exit(3);
        }

        // evaluate result based on operator and operands, place
        // result back into stack
        // val1 + val2
        if (token.equals("+")){
            myStack.push(val1+val2);
        }
        // val1-val2
        else if (token.equals("-")){
                myStack.push(val1-val2);
        }
        // val1*val2
        else if (token.equals("*")) {
                myStack.push(val1 * val2);
        }
        //   val1/val2
        else if (token.equals("/")) {
                myStack.push(val1 / val2);
        }
        // custom ternary operator ?(val1,val2,val3)=(val1+2*val2+3*val3)/3
        else if (token.equals("?")) {
            try{
                val3 = myStack.pop();
                myStack.push( (val1 + 2*val2 + 3*val3)/3 );
            }catch (EmptyStackException e){
                System.out.println("  Not enough arguments for operator <?>");
                System.exit(13);
            }
        }
    }
}
