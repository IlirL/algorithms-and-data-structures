import java.util.Scanner;


public class LDS {


    private static int najdolgaOpagackaSekvenca(int[] a) {

        // Vasiot kod tuka
        //ok so the logic was fucking simple
        //lds[i] is the longest subsequence from the sequence a[]
        //for which a[i] is the last element in that subsequence
        //you needed the last fucking element, from there you should have been
        //inclined to this representation

        int n = a.length;
        int lds[] = new int[n];
        int i, j, max = 0;

        //initialize lds with 1, logical
        for( i = 0; i<n; i++)
        {
            lds[i] = 1;
        }

        for(i = 1;i<n;i++)
        {
            for(j = 0; j<i; j++)
            {
                if(a[i] < a[j] && lds[i] < lds[j] + 1 )
                {
                    lds[i] = lds[j] + 1;
                }
            }
        }

        for(i = 0; i<n; i++)
        {
            if(max<lds[i])
            {
                max = lds[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }


}
