import java.util.NoSuchElementException;
import java.util.Scanner;

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
class ArrayStack<E> implements Stack<E> {

    // Stekot e pretstaven na sledniot nacin:
    //depth e dlabochinata na stekot, a
    // elems[0...depth-1] se negovite elementi.
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

 interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    // Ja vrakja dolzinata na redicata.

    public E peek ();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    // Ja prazni redicata.

    public void enqueue (E x);
    // Go dodava x na kraj od redicata.

    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.

}

 class ArrayQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Ako length > 0, togash elementite na redicata se zachuvani vo elems[front...rear-1]
    // Ako rear > front, togash vo  elems[front...maxlength-1] i elems[0...rear-1]
    E[] elems;
    int length, front, rear;

    // Konstruktor ...

    @SuppressWarnings("unchecked")
    public ArrayQueue (int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }

    public void clear () {
        // Ja prazni redicata.
        length = 0;
        front = rear = 0;  // arbitrary
    }

    public void enqueue (E x) {
        // Go dodava x na kraj od redicata.
        elems[rear++] = x;
        if (rear == elems.length)  rear = 0;
        length++;
    }

    public E dequeue () {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length)  front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}

class StackQueue<E extends Comparable<E>>{
     ArrayStack<E> s;
     ArrayQueue<E> q;
     int leftStackSize, leftQueueSize;
    public StackQueue(int stackSize, int queueSize) {
        this.leftQueueSize = queueSize;
        this.leftStackSize = stackSize;
        s = new ArrayStack<E>(leftStackSize);
        q = new ArrayQueue<E>(leftQueueSize);
    }

    public void insertElement(E o)
    {
        if(leftStackSize + leftQueueSize == 0)
        {
            throw new NoSuchElementException();
        }
        if(leftStackSize > 0)
        {
            s.push(o);
            leftStackSize--;
        }
        else{
            q.enqueue(o);
            leftQueueSize--;
        }
    }

    public E deleteElement()
    {
        if(s.isEmpty() && q.isEmpty())
        {
            throw new NoSuchElementException();
        }
        else if(s.isEmpty())
        {
            return q.dequeue();
        }
        else if(q.isEmpty())
        {
            return s.pop();
        }
        else
        {
            E topFromTheStack = s.peek();
            E frontFromQueue = q.peek();

            if(topFromTheStack.compareTo(frontFromQueue) == -1)
            {
                return q.dequeue();
            }
            else
            {
                return s.pop();
            }
        }
    }

    public boolean isEmpty()
    {
        if(s.isEmpty() && q.isEmpty())
            return true;
        return false;
    }

    public E peek()
    {
        if(s.isEmpty() && q.isEmpty())
        {
            throw new NoSuchElementException();
        }
        else if(s.isEmpty())
        {
            return q.peek();
        }
        else if(q.isEmpty())
        {
            return s.peek();
        }
        else
        {
            E topFromTheStack = s.peek();
            E frontFromQueue = q.peek();

            if(topFromTheStack.compareTo(frontFromQueue) == -1)
            {
                return q.peek();
            }
            else
            {
                return s.peek();
            }
        }
    }

}

public class TestStackQueue {


    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int sizeOfStack = s.nextInt();
        int sizeOfQueue = s.nextInt();
        int sizeTotal = s.nextInt();
        StackQueue<Integer> sq = new StackQueue<Integer>(sizeOfStack, sizeOfQueue);
        int element;
        for(int i = 0; i<sizeTotal; i++)
        {
            element = s.nextInt();
            sq.insertElement(element);
        }
        System.out.println(sq.peek());
        while(!sq.isEmpty())
        {
            System.out.print(sq.deleteElement()+ " ");
        }
    }


}
