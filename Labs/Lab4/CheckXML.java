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

public class CheckXML {

    public static boolean isOpeningTag(String s){
        if(s.charAt(0) == '[' && s.charAt(s.length()-1) == ']' && s.charAt(1) != '/')
        {
            return true;
        }
        return false;
    }

    public static boolean isClosingTag(String s)
    {
        if(s.charAt(0) == '[' && s.charAt(s.length()-1) == ']' && s.charAt(1) == '/')
        {
            return true;
        }
        return false;
    }

    public static String corresponding_closing_tag(String s)
    {
        String rezultat = new String();
        rezultat+="[/" + s.substring(1, s.length());

        return rezultat;

    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String[] redovi = new String[n];

        for (int i = 0; i < n; i++)
            redovi[i] = br.readLine();

        int valid;
        ArrayStack<String> tagStack = new ArrayStack<String>(1000);

        // Your code here
        // You can use additional functions
        //Now i will add all the tags in the stack

//        boolean okXML = true;
        valid = 1;
        for (int i = 0; i < n; i++)
        {
            if(isOpeningTag(redovi[i])){
                tagStack.push(redovi[i]);
            }

            else if(isClosingTag(redovi[i]))
            {
                String topElement = tagStack.pop();
//                System.out.println("Current Top element = " + topElement + "corr_closing_tag = " + corresponding_closing_tag(topElement) + "the current string = " + redovi[i]);
                if(!redovi[i].equals(corresponding_closing_tag(topElement))){
//                    okXML = false;
                    valid = 0;
                    break;
                }
            }
        }

        System.out.println(valid);

        br.close();
    }
}