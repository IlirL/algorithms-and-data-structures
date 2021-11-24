import java.util.Scanner;

public class DividingCoinsDP {
    public static void main(String[] args) {
        int[] coin = new int [505];
        int[] DP = new int [50005];
        int n;
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        int sum =0;
        for(int i = 0; i<n; i++)
        {
            coin[i] = s.nextInt();
            sum+=coin[i];
        }
        for(int i = 0; i<50005; i++)
        {
            DP[i] = 0;
        }

        DP[0] = 1;

        for(int i = 0; i<n; i++)
        {
            for(int j = sum; j>=coin[i]; j--)
            {
                if(DP[j-coin[i]]==1)
                    DP[j] = 1;
            }
        }

        for(int i = sum/2; i>=0; i--)
        {
            if(DP[i] == 1){
                System.out.println(sum-i-i);
                break;
            }
        }

    }
}
