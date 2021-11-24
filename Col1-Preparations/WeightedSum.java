import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WeightedSum {

    static int solve(int numbers[], int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni
        //this problem is solved with dp
        //I will try and make a dp array with the highest sum ending with index i, but choosing K-1 numbers not K
        //Or maybe doing a dp[i][j], for sum ending with i and j being number from 1...K;
        //then how will we find dp[n-1][K] = max(dp[n-2][K-1], dp[n-3][K-1,...dp[K-2][K-1]) + numbers[n-1]*K
        //we will save also array max[K] => max[i] = max(dp[n-2][i]...dp[i-1][i])


        int[][] DP = new int[N][K+1];
        int[][] maxSum = new int [N][K+1];
//        DP[0][0] = 0;
//        DP[0][1] = numbers[0];
//        DP[1][1] = Math.max(numbers[0], numbers[1]);
//        DP[2][1]  = ;
//        DP[3][1] = ;
//        .
//        .
//        .
        DP[0][1] = numbers[0];
         maxSum[0][1] = numbers[0];
        for(int i = 1; i<N; i++)
        {
            DP[i][1] = Math.max(maxSum[i-1][1],numbers[i]);
            maxSum[i][1] = Math.max(maxSum[i-1][1], DP[i][1]);
//            System.out.println("DP[i][1] = " + DP[i][1]);
        }
       for(int i = 2; i<=K; i++)
       {
           //
           for(int j = i-1; j<N; j++)
           {
                DP[j][i] = maxSum[j-1][i-1] + numbers[j]*i;
                maxSum[j][i] = Math.max(maxSum[j-1][i], DP[j][i]);
//               System.out.println("DP["+j+"]["+i+"] = " + DP[j][i]);
           }
       }
    return maxSum[N-1][K];
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int numbers[] = new int[N];

        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int res = solve(numbers, N, K);
        System.out.println(res);

        br.close();

    }

}