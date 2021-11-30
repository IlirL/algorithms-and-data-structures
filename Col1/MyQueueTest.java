import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}
interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:
    public void enqueue (E x);
    // Go dodava x na kraj od redicata.
    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.
    public E peek ();
    // go vraka elementot od pocetokot na redicata
    public int size();
    // ja vraka goleminata na redicata
}

class ArrayStack<E> implements Stack<E> {

    // the stack is constructed in the following way:
    // depth is the depth of the stack
    // elems[0..depth-1] are its elements
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // the contructor of a new empty stack
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // returns true iff the stack is empty
        return (depth == 0);
    }


    public E peek () {
        // returns the element on the top of the stack
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // clears the stack
        for (int i = 0; i < depth; i++)
            elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // adds x on the top of the stack
        elems[depth++] = x;
    }


    public E pop () {
        // deletes and returns the element from the top of the stack
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }

}




class MyQueue<E> implements Queue<E> {
    // Your code here
        private ArrayStack<E> elements;
        private int size;
    public MyQueue() {
        elements = new ArrayStack<E>(10000);
        size = 0;
    }

    public void enqueue (E x){
        if(size == 0)
        {
            elements.push(x);
            size++;
            return;
        }
            ArrayStack<E> temp = new ArrayStack<E>(10000);
        while(!elements.isEmpty()){
            temp.push(elements.pop());
        }
        elements.push(x);
        while(!temp.isEmpty())
        {
            elements.push(temp.pop());
        }
            size++;
    }

    public E dequeue (){
        if(size>0)
            size--;
        return elements.pop();
    }

    public E peek (){
        return elements.peek();
    }
    public int size(){
        return this.size;
    }
}

public class MyQueueTest {

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MyQueue<Integer> ob = new MyQueue<Integer>();

        int N = Integer.parseInt(br.readLine());
//        int[] niza = new int[N];
        // ... Your code here
//        try{
//            System.out.println(Integer.parseInt("END"));
//        }
//        catch(Error e){
//                System.out.println("ilir");
//        }
        for(int i = 0; i<N; i++)
        {
//            niza[i] = Integer.parseInt(br.readLine());
            ob.enqueue(Integer.parseInt(br.readLine()));
        }
        String nextInput = br.readLine();
        while(nextInput.compareTo("1")==0 || nextInput.compareTo("2") == 0)
        {
//            System.out.println("ilir");
            if(nextInput.compareTo(("1")) == 0)
            {
//                if(ob.size() != 0)
//                {
//                    System.out.println(ob.dequeue());
//                }
                try{
                    int current_element = ob.dequeue();
                    System.out.println(current_element);
                }
                catch (Exception e)
                {
                    System.out.println("Empty queue");
                }
            }
            else if(nextInput.compareTo(("2")) == 0)
            {
                ob.enqueue(Integer.parseInt(br.readLine()));
            }
            nextInput = br.readLine();
//            System.out.println("nextInput = " + nextInput);
        }
        System.out.println("Queue size is: " + ob.size());
    }

}

