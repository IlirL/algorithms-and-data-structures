import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



class SLLNode {
    protected int id;
    protected int salary;
    protected SLLNode succ;

    public SLLNode(int id,int salary, SLLNode succ) {
        this.id = id;
        this.salary=salary;
        this.succ = succ;
    }
}

class SLL {
    private SLLNode first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }


    public void insertFirst(int id, int salary) {
        SLLNode ins = new SLLNode(id,salary, first);
        first = ins;
    }

    public void insertLast(int id,int salary) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id, salary, null);
            tmp.succ = ins;
        } else {
            insertFirst(id,salary);
        }
    }

    public SLLNode getFirst() {
        return first;
    }


    public SLL delete_smaller_from(int value) {
        // Your code here
        //We have first node, we will iterate through the list and delete the nodes with salary less than value;

        SLLNode copy_first = first;
        SLL newSLL = new SLL();
        while(copy_first!=null)
        {
            if(copy_first.salary >= value)
            {
                newSLL.insertLast(copy_first.id, copy_first.salary);


            }
            copy_first = copy_first.succ;
        }
        if(newSLL.length() > 0)
            return newSLL;
        else
        {
            System.out.println("nema");
            return null;
        }


    }

    public SLL sort_descending() {
        // Your code here

        SLLNode current = first, index = null;
        int temp_salary, temp_id;

        if(first == null)
        {
            System.out.println("nema");
            return this;
        }
        else{
            while(current != null)
            {
                //Node index will point to node next to current
                index = current.succ;

                while(index != null)
                {
                    if(current.id < index.id)
                    {
                        temp_salary = current.salary;
                        temp_id = current.id;

                        current.salary = index.salary;
                        current.id = index.id;
                        index.salary = temp_salary;
                        index.id = temp_id;


                    }
                    index = index.succ;
                }
                current = current.succ;

            }
        }

        return this;
    }
    public void pecati (SLL lista)
    {
        SLLNode p=lista.first;
        while(p!=null)
        {
            System.out.println(p.id+" "+p.salary);
            p=p.succ;
        }
    }

}
public class SLLKompanija {
    public static void main(String[] args) throws IOException {

        SLL lista1 = new SLL();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        for (int i = 0; i < N; i++) {
            s=stdin.readLine();
            String s1=stdin.readLine();
            lista1.insertLast(Integer.parseInt(s),Integer.parseInt(s1));
        }
        s = stdin.readLine();

        lista1=lista1.delete_smaller_from(Integer.parseInt(s));
        if(lista1!=null)
        {
            lista1=lista1.sort_descending();
            lista1.pecati(lista1);
        }

    }
}
