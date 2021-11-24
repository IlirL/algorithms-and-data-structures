import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SpecialChoice {

    static int solve(int numbers[], int N, int M, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni
        int [][] DP = new int [N][K+1];
        for(int i = 0; i<N; i++)
        {
            DP[i][1] = numbers[i];
            if(DP[i][1] > M)
                DP[i][1] = -1;
//            System.out.println(DP[i][1]);
        }

        for(int j = 2; j<=K; j++)
        {
            for(int i = j-1; i<N; i++)
            {
                for(int k = i-1; k>=j-1 && k>=0; k--)
                {
                    if(DP[k][j-1] > -1 && numbers[k] + numbers[i] <= M)
                    {
                        DP[i][j] = Math.max(DP[i][j], DP[k][j-1] + numbers[i]);
                    }
                }
                if(DP[i][j] == 0)
                {
                    DP[i][j] =-1;
                }
//                System.out.println("DP["+i+"]["+j+"] = "+DP[i][j]);
            }

        }

        int maxSum = 0;
        for(int i = K-1; i<N;i++)
        {
            maxSum = Math.max(maxSum, DP[i][K]);
        }
        return maxSum;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, M, K);
        System.out.println(res);

        br.close();

    }

}