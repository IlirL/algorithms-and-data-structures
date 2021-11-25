import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Letters {

    static boolean isSamoglaska(char c)
    {
        if(c=='a' || c=='e' || c == 'i' || c=='o' || c=='u')
            return true;
        return false;
    }
    static int solve(String S, int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni
        int [][] DP = new int [N][K+1];
        for(int i = 0; i<N; i++)
        {
            DP[i][1] = 1;
        }

        for(int j = 2; j<=K; j++)
        {
            for(int i = j-1; i<N; i++)
            {
                for(int k = i-1; k>=0 && k>=j-1-1; k--)
                {
                    if(isSamoglaska(S.charAt(k)) && isSamoglaska(S.charAt(i)))
                    {
                        continue;
                    }
                    else
                    {
                        DP[i][j]+=DP[k][j-1];
//                        System.out.println("We are in here for i = " + i + " and j = "+j+" and k = "+k);
//                        ?\System.out.println("DP[" + i +"][" + j+"] = " + DP[i][j]);
                    }

                }
//                System.out.println("DP[" + i +"][" + j+"] = " + DP[i][j]);
            }
        }

        int result = 0;
        for(int i = K-1; i<N; i++)
        {
            result = result+DP[i][K];
        }
        return result;

    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int N = S.length();
        int K = Integer.parseInt(br.readLine());

        int res = solve(S, N, K);
        System.out.println(res);

        br.close();

    }
}