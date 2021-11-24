import java.util.Arrays;
import java.util.Scanner;

class ArrayQueue<E> {
    E[] elems;
    int length, front, rear;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty() {
        return (length == 0);
    }

    public int size() {
        return length;
    }

    public E peek() {
        if (length > 0)
            return elems[front];
        else {
            System.out.println("Redicata e prazna");
            return null;
        }
    }

    public void clear() {
        length = 0;
        front = rear = 0;
    }

    public void enqueue(E x) {
        elems[rear++] = x;
        if (rear == elems.length)
            rear = 0;
        length++;
    }

    public E dequeue() {
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length)
                front = 0;
            length--;
            return frontmost;
        } else {
            System.out.println("Redicata e prazna");
            return null;
        }
    }
}

public class Konsultacii {
    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        ArrayQueue<String> redKratkiPrasanja = new ArrayQueue<String>(50);
        ArrayQueue<String> redZadaci = new ArrayQueue<String>(50);
        ArrayQueue<String> redKratkiPrasanjaZadaci = new ArrayQueue<String>(50);


        int i,brStudentiKratkiPrasanja,brStudentiZadaci,brStudentiKratkiPrasanjaZadaci;

        brStudentiKratkiPrasanja=Integer.parseInt(br.nextLine());
        String[] imeStudentiKratkiPrasanja = new String[brStudentiKratkiPrasanja];

        for(i=0; i<brStudentiKratkiPrasanja; i++)
        {
            String red = br.nextLine();
            String[] pom = red.split(" ");
            imeStudentiKratkiPrasanja[i] = pom[0];
            redKratkiPrasanja.enqueue(imeStudentiKratkiPrasanja[i]);
        }

        brStudentiZadaci=Integer.parseInt(br.nextLine());
        String[] imeStudentiZadaci = new String[brStudentiZadaci];

        for(i=0; i<brStudentiZadaci; i++)
        {
            String red = br.nextLine();
            String[] pom = red.split(" ");
            imeStudentiZadaci[i] = pom[0];
            redZadaci.enqueue(imeStudentiZadaci[i]);
        }

        brStudentiKratkiPrasanjaZadaci=Integer.parseInt(br.nextLine());
        String[] imeStudentiKratkiPrasanjaZadaci = new String[brStudentiKratkiPrasanjaZadaci];

        for(i=0; i<brStudentiKratkiPrasanjaZadaci; i++)
        {
            String red = br.nextLine();
            String[] pom = red.split(" ");
            imeStudentiKratkiPrasanjaZadaci[i] = pom[0];
            redKratkiPrasanjaZadaci.enqueue(imeStudentiKratkiPrasanjaZadaci[i]);
        }


        // Vasiot kod ovde...
       boolean prasanjaTurn = true;
        while(!redZadaci.isEmpty() || !redKratkiPrasanjaZadaci.isEmpty() || !redKratkiPrasanja.isEmpty())
        {
            String currentStudent = new String("");
            if(prasanjaTurn)
            {
                if(!redKratkiPrasanja.isEmpty()){
                    currentStudent = redKratkiPrasanja.dequeue();
                    System.out.println(currentStudent);
                }
                else if(!redKratkiPrasanjaZadaci.isEmpty())
                {
                    currentStudent = redKratkiPrasanjaZadaci.dequeue();
                    System.out.println(currentStudent);
                    redZadaci.enqueue(currentStudent);
                }
                else{
                    prasanjaTurn = !prasanjaTurn;
                }
            }
            if(!prasanjaTurn)
            {
                if(!redZadaci.isEmpty())
                {
                    currentStudent = redZadaci.dequeue();
                    System.out.println(currentStudent);
                }
                else if(!redKratkiPrasanjaZadaci.isEmpty())
                {
                    currentStudent = redKratkiPrasanjaZadaci.dequeue();
                    System.out.println(currentStudent);
                    redKratkiPrasanja.enqueue(currentStudent);
                }
            }

            prasanjaTurn = !prasanjaTurn;
        }

    }
}