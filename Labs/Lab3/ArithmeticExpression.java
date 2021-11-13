import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        // Vasiot kod tuka
        if(r - l + 1 ==5){
            if(c[l+2]=='+')
            {
                return (c[l+1] - '0') + (c[l+3]-'0');
            }
            else{
                return (c[l+1] - '0') - (c[l+3] - '0');
            }
        }

        int open = 0, mid = 0;
        for(int i = l; i<r; i++)
        {
            if(c[i] == '('){
                open++;
            }
            if(c[i] == ')')
            {
                open--;
            }

            if(c[i] == '+' || c[i] == '-'){
                if(open == 1){
                    mid = i;
                    break;
                }
            }
        }

        if(c[mid] == '+')
            return presmetaj(c, l+1, mid-1) + presmetaj(c, mid+1, r-1);
        else
            return presmetaj(c, l+1, mid-1) - presmetaj(c, mid+1, r-1);


    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
