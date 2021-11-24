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

public class Kolokvium {
    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);

        ArrayQueue<String> redAPS = new ArrayQueue<String>(50);
        ArrayQueue<String> redAPSMatematika = new ArrayQueue<String>(50);

        int i,j,brStudentiTermin,brStudentiAPS,brStudentiAPSMatematika,brStudentiMatematika;

        brStudentiTermin=Integer.parseInt(br.nextLine());
        brStudentiAPSMatematika=Integer.parseInt(br.nextLine());
        String[] imeStudentiAPSMatematika = new String[brStudentiAPSMatematika];

        for(i=0; i<brStudentiAPSMatematika; i++)
        {
            String red = br.nextLine();
            String[] pom = red.split(" ");
            imeStudentiAPSMatematika[i] = pom[0];
            redAPSMatematika.enqueue(imeStudentiAPSMatematika[i]);
        }

        brStudentiAPS=Integer.parseInt(br.nextLine());
        String[] imeStudentiAPS = new String[brStudentiAPS];

        for(i=0; i<brStudentiAPS; i++)
        {
            String red = br.nextLine();
            String[] pom = red.split(" ");
            imeStudentiAPS[i] = pom[0];
            redAPS.enqueue(imeStudentiAPS[i]);
        }

        brStudentiMatematika=Integer.parseInt(br.nextLine());
        String[] imeStudentiMatematika = new String[brStudentiMatematika];

        for(i=0; i<brStudentiMatematika; i++)
        {
            String red = br.nextLine();
            String[] pom = red.split(" ");
            imeStudentiMatematika[i] = pom[0];
            //redAPS.enqueue(imeStudentiMatematika[i]);
        }

        // Vasiot kod ovde...
        int termin = 1;
        int currentFreeSpaces = brStudentiTermin;
        System.out.println(termin);
        while(!redAPSMatematika.isEmpty()){
            String currentStudent = redAPSMatematika.dequeue();
            if(Arrays.asList(imeStudentiMatematika).contains(currentStudent))
            {
                if(currentFreeSpaces > 0)
                {
                    System.out.println(currentStudent);
                    currentFreeSpaces--;

                }
                else{
                    currentFreeSpaces = brStudentiTermin;
                    termin++;
                    System.out.println(termin);
                    System.out.println(currentStudent);
                    currentFreeSpaces--;
                }
            }
            else{
                redAPS.enqueue(currentStudent);
            }
        }
        while(!redAPS.isEmpty())
        {
            String currentStudent = redAPS.dequeue();
            if(currentFreeSpaces > 0)
            {
                System.out.println(currentStudent);
                currentFreeSpaces--;
            }
            else
            {
                currentFreeSpaces = brStudentiTermin;
                termin++;
                System.out.println(termin);
                System.out.println(currentStudent);
                currentFreeSpaces--;
            }
        }

    }
}