import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Range {

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

        long l = A, r = B;
        while(r-l > 1)
        {
            long mid = (l+r)/2;
            long calculation = mid*(mid+200) + sum_digits(mid);
            if(calculation ==N)
            {
                return mid;
            }
            else if(calculation<N)
            {
                l = mid;
            }
            else
            {
                r = mid;
            }
        }
        for(;l<=r;l++)
        {
            long calculation = l*(l+200) + sum_digits(l);
            if(calculation == N)
                return l;
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
