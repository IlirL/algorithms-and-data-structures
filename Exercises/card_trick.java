    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;

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

    class NoSuchElementException extends Exception{
        public NoSuchElementException() {
            super("NoSuchElementException");
        }
    }

    class ArrayStack<E> implements Stack<E> {
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


        public E peek ()  {
            // Go vrakja elementot na vrvot od stekot.
    //        if (depth == 0)
    //            throw new NoSuchElementException();
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
    //        if (depth == 0)
    //            throw new NoSuchElementException();
            E topmost = elems[--depth];
            elems[depth] = null;
            return topmost;
        }
    }


    class ArrayQueue<E>{
        E[] elems;
        int length, front, rear;
        @SuppressWarnings("unchecked")
        public ArrayQueue (int maxlength) {
            elems = (E[]) new Object[maxlength];
            clear();
        }
        public boolean isEmpty () {
            return (length == 0);
        }
        public int size () {
            return length;
        }
        public E peek () {
            if (length > 0)
                return elems[front];
            else{
                System.out.println("Queue is empty");
                return null;
            }
        }
        public void clear () {
            length = 0;
            front = rear = 0;
        }
        public void enqueue (E x) {
            elems[rear++] = x;
            if (rear == elems.length)  rear = 0;
            length++;
        }
        public E dequeue () {
            if (length > 0) {
                E frontmost = elems[front];
                elems[front++] = null;
                if (front == elems.length)  front = 0;
                length--;
                return frontmost;
            } else{
                System.out.println("Queue is empty");
                return null;
            }
        }
    }

    public class card_trick {
        public static int count(int N){
            // Vasiot kod tuka
            //we will put the cars in a queue from 1 to 51
            //we will put the 7 taken cards in a stack

            ArrayQueue<Integer> cards = new ArrayQueue<Integer>(51);
            for(int i = 1; i<=51; i++)
            {
                cards.enqueue(i);
            }
            ArrayStack<Integer> seven_cards = new ArrayStack<Integer>(7);
            int shuffles = 0;
            while(cards.peek() != N)
            {
                for(int i=  0; i<7; i++)
                {
                    seven_cards.push(cards.dequeue());
                }
                while(!seven_cards.isEmpty())
                {
                    cards.enqueue(seven_cards.pop());
                    cards.enqueue(cards.dequeue());
                }
//                System.out.println("At the end the top most card is: " + cards.front);
                shuffles++;
            }
            return shuffles;
        }

        public static void main(String[] args) throws NumberFormatException, IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
            System.out.println(count(Integer.parseInt(br.readLine())));
        }

    }
