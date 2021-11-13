import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {

    static int solve(int numbers[], int N, int K) {

        int k = 2;
        int j, i;

        int [][]max_absolute = new int [K+1][N];

        while(k<=K){
            for(j = k-1; j<N; j++)
            {
                for( i = 0; i<j; i++)
                {
                    int abs_value = Math.abs(numbers[i] - numbers[j]) + max_absolute[k-1][i];
                    if(abs_value > max_absolute[k][j])
                        max_absolute[k][j] = abs_value;
                }
        }
            k++;
        }

        int max_value = 0;
        for(i = 0; i<N; i++)
        {
            if(max_absolute[K][i] > max_value)
            {
                max_value = max_absolute[K][i];
            }
    }
        return max_value;
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