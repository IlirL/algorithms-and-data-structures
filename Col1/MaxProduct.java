import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MaxProduct {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        int arr[] = new int[N];
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(pomniza[i]);
        }
        // Your code here
        long[] DP = new long[N];
        //DP[i] is the maximum product ending with index i
        long maxNumber = -1;
        DP[0] = arr[0];
        if(N == 1)
        {
            System.out.println(arr[0]);
        }
        else{
            if (arr[1] > arr[0])
                DP[1] = arr[0] * arr[1];
            else
                DP[1] = arr[0];
            maxNumber = Math.max(DP[0], DP[1]);
            for (int i = 2; i < N; i++)
            {
                DP[i] = arr[i];
                for(int j = i-1; j>=0; j--)
                {
                    if(arr[i] > arr[j])
                    {
                        DP[i] = Math.max(DP[i], DP[j] * arr[i]);
//                        System.out.println("We are in if1 with arr[" + "i] = "
//                                + arr[i] + " arr[" + j + "] = "+arr[j] + "for which we get DP[" + i + "] = " + DP[i]);
                    }
                    maxNumber = Math.max(maxNumber, DP[i]);
//                    else
//                    {
//                        DP[i] = Math.max(DP[i], DP[j]);
//                    }
                }
//                System.out.println("DP[" + i + "] = " + DP[i]);
            }
//        long max = -1;
//            for(int i = 0; i<N; i++)
//            {
//                max = Math.max(max, DP[i]);
//            }
            System.out.println(maxNumber);
        }
        }


}