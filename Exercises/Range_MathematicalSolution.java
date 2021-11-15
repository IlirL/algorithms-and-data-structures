import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Range_MathematicalSolution {

    static boolean checkPerfectSquare(long number){
        double sqrt = Math.sqrt(number);
        return ((sqrt - Math.floor(sqrt))==0);
    }

    static int sum_digits(long n)
    {
        int rezultat = 0;
        while(n>0){
            rezultat+=n%10;
            n/=10;
        }
        return rezultat;
    }

    static long proveri(long N, long A, long B) {
        // Vasiot kod tuka
        for(long i = N + 100 * 100 - 81; i<N + 100*100; i++)
        {
            if(checkPerfectSquare(i))
            {
                long x = (long)Math.sqrt(i) - 100;
                if(x>=A && x<=B && i == N + 100*100-sum_digits(x))
                    return x;
            }

        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long res = proveri(N, A, B);
        System.out.println(res);

        br.close();

    }

}
