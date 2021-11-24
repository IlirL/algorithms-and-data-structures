import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {

    static int solve(int numbers[], int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni
        int[][] DP = new int[N][K+1];
        for(int i = 0; i<N; i++)
        {
            DP[i][1] = numbers[i];

        }

        for(int i=1; i<N; i++)
        {
            for(int k = i-1; k>=0; k--)
            {
                DP[i][2] = Math.max(DP[i][2], Math.abs(DP[k][1] - numbers[i]));
            }
            System.out.println("DP[" + i + "][2] =" + DP[i][2]);
        }

        for(int j = 3; j<=K; j++)
        {
            for(int i = j-1; i<N; i++)
            {
                for(int k = i-1; k>=0 && k>=j-1-1; k--)
                {
                    DP[i][j] = Math.max(DP[k][j-1] + Math.abs(numbers[i] - numbers[k]), DP[i][j]);

                }
                System.out.println("DP[" + i + "][" + j+"] =" + DP[i][j]);
            }
        }

        int result = 0;
        for(int i = K-1; i<N; i++)
        {
            result = Math.max(result, DP[i][K]);
        }


        return result;
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