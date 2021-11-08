package Average;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Array<E> {

    private int size;
    private E[] niza;

    public Array(int size)
    {
        this.size = size;
        this.niza = (E[]) new Object[size];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public E[] getNiza() {
        return niza;
    }

    public void setNiza(E[] niza) {
        this.niza = niza;
    }

    public static int brojDoProsek(Array<Integer>niza){
        //Code here...
        float sum = 0, average = 0;
        for(int i= 0; i<niza.size; i++)
        {
            sum+=niza.niza[i];
        }
        average = sum / niza.size;


        float closestDiff = Float.MAX_VALUE;
        int closestElement= 0;

        for(int i = 0; i<niza.size; i++)
        {
            if(Math.abs(niza.niza[i] - average) < closestDiff || (Math.abs(niza.niza[i] - average) == closestDiff && niza.niza[i] < niza.niza[closestElement]))
            {
                closestElement = i;
                closestDiff = Math.abs(niza.niza[i] - average);
            }
        }

        return niza.niza[closestElement];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        //Code here...
        Array<Integer> niza = new Array<Integer>(N);
        Integer[] mojaNiza = new Integer[N];

        for(int i = 0; i<N; i++)
        {
            s = stdin.readLine();
            mojaNiza[i] = Integer.parseInt(s);
        }

        niza.setNiza(mojaNiza);

        System.out.println(brojDoProsek(niza));
    }



}