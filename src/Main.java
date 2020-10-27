// Simple stack example
// Evaluate postfix expressions such as 3 2 4 * +
// David John
// February 2020

import java.util.Stack;
import java.util.EmptyStackException;
import java.util.Scanner;


public class Main {

    // class variable myStack to hold integer inputs and
    // computed expressions
    private static Stack<Integer> myStack;

    public static void main(String[] args) {

        // instantiate myStack and keyboard
        myStack = new Stack<>();
        Scanner keyboard = new Scanner(System.in);

        // prompt for  a single line of input
        // and process expression tokens
        System.out.print("Enter expression: ");
        String userinput = keyboard.nextLine();
        String[] usertokens = userinput.split(" ");
        for (String myToken : usertokens) {

            // try to convent myToken to an integer, if so
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
        if (token.equals("+")){
            try{
                int val1 = myStack.pop();
                int val2 = myStack.pop();
                myStack.push(val1+val2);
            } catch (EmptyStackException e){
                System.out.println("  ILLEGAL EXPRESSION involving +");
                System.exit(3);
            }
        }
        else if (token.equals("*")) {
            try {
                int val1 = myStack.pop();
                int val2 = myStack.pop();
                myStack.push(val1 * val2);
            } catch (EmptyStackException e){
                System.out.println(" ILLEGAL EXPRESSION involving I");
                System.exit(4);
            }
        }
    }
}
