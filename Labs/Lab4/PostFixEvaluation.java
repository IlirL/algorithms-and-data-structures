import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {

    // The elements of the Stack are any kind of objects

    // Access methods:

    public boolean isEmpty ();
    // Returns true only if the stack is empty.

    public E peek ();
    // Returns the element on the top od the stack.

    // Transformation methods:

    public void clear ();
    // Clears the stack.

    public void push (E x);
    // Adds x on the top of the stack.

    public E pop ();
    // Removes and returns the element on the top.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Creating new empty stack
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Returns true only if the stack is empty.

        return (depth == 0);
    }


    public E peek () {
        // Returns the element on the top od the stack.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Clears the stack.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Adds x on the top of the stack.
        elems[depth++] = x;
    }


    public E pop () {
        // Removes and returns the element on the top.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class PostFixEvaluation {


    static int evaluatePostfix(char [] izraz, int n)
    {
        ArrayStack<Integer> ilir_stack = new ArrayStack<Integer>(10000);

//        StringBuilder currentNumber = new StringBuilder("");
        String currentNumber = new String("");
        for(int i = 0; i<n; i++)
        {
            if(izraz[i] <='9' && izraz[i] >='0')
            {
                currentNumber+=izraz[i];
////                currentNumber.append(izraz[i]);
            }
            else if(izraz[i] ==' ' && currentNumber.length() > 0)
            {
//               ilir_stack.push(Integer.parseInt(currentNumber));
               ilir_stack.push(Integer.valueOf(currentNumber));
                currentNumber = new String();

            }
            else if(izraz[i] == '+')
            {
                int number1 = ilir_stack.pop();
                int number2 = ilir_stack.pop();

                ilir_stack.push(number1+number2);
            }
            else if(izraz[i] == '-')
            {
                int number1 = ilir_stack.pop();
                int number2 = ilir_stack.pop();

                ilir_stack.push(number2-number1);
            }
            else if(izraz[i] == '/')
            {
                int number1 = ilir_stack.pop();
                int number2 = ilir_stack.pop();

                ilir_stack.push(number2/number1);
            }
            else if(izraz[i] == '*')
            {
                int number1 = ilir_stack.pop();
                int number2 = ilir_stack.pop();

                ilir_stack.push(number2*number1);
            }
        }

        return ilir_stack.peek();
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);

        br.close();

    }

}
