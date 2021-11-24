import java.util.Scanner;

public class DividingCoinsBruteForceApproach {
    private int minimumDifference = Integer.MAX_VALUE;

    public  int minimumDifferenceFunction(int niza[], int n, int currentIndex, int magdalena , int hristina)
    {
        if(currentIndex == n) {
            if(this.minimumDifference > Math.abs(magdalena - hristina))
                this.minimumDifference = Math.abs(magdalena - hristina);
        }
       else{
           minimumDifferenceFunction(niza, n, currentIndex+1, magdalena+niza[currentIndex], hristina);
           minimumDifferenceFunction(niza, n, currentIndex+1, magdalena, hristina+niza[currentIndex]);
       }
            return this.minimumDifference;
    }

    public static void main(String[] args) {
        DividingCoinsBruteForceApproach temp_object = new DividingCoinsBruteForceApproach();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] niza = new int[n];
        for(int i = 0; i<n; i++){
            niza[i] = s.nextInt();
        }
        System.out.println( temp_object.minimumDifferenceFunction(niza, n, 0, 0, 0));


    }

}
