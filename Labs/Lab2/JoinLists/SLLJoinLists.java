import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

class SLLNode<E>{
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ)
    {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString(){
        return element.toString();
    }
}

class SLL<E>{
    private SLLNode<E> first;

    public SLL(){
        this.first = null;
    }

    public void deleteList(){
        first = null;
    }

    public int length(){
        int ret;
        if(first!=null)
        {
            SLLNode<E> tmp = first;
            ret = 1;
            while(tmp.succ!=null)
            {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        }
        return 0;
    }

    @Override
    public String toString(){
        String ret = new String();
        if(first != null)
        {
            SLLNode<E> tmp = first;
            ret+=tmp +"->";
            while(tmp.succ != null){
                tmp = tmp.succ;
                ret+=tmp + "->";

            }
        }else
            ret = "Prazna lista!!!";
        return ret;
    }


    public void insertFirst(E o){
        SLLNode<E> ins = new SLLNode<E>(o, null);
        first = ins;
    }

    public void insertLast(E o){
        if(first!=null)
        {
            SLLNode<E>tmp = first;
            while(tmp.succ != null)
            {
                tmp = tmp.succ;
            }
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        }
        else
            insertFirst(o);
    }


    public SLLNode<E> getFirst(){
        return first;

    }


    public Iterator<E> iterator(){
        return new LRIterator<E>();
    }

    private class LRIterator<E> implements Iterator<E>{
        private SLLNode<E> place, curr;

        private LRIterator(){
            place = (SLLNode<E>) first;
            curr = null;
        }

        public boolean hasNext(){
            return(place!=null);
        }

        public E next(){
            if(place ==null)
                throw new NoSuchElementException();
            E nextElem = place.element;
            curr = place;
            place = place.succ;
            return nextElem;
        }
    }

}

class JoinSortedLists<E extends Comparable<E>> {
    public SLL<E> join(SLL<E> list1, SLL<E> list2) {
        SLL<E> rezultat = new SLL<E>();
        SLLNode<E> jazol1 = list1.getFirst(), jazol2 = list2.getFirst();

        SLLNode<E> rjazol;

        if (jazol1.element.compareTo(jazol2.element) < 0) {
            rjazol = jazol2;
        } else {
            rjazol = jazol1;
        }

        int bul = 0;
        while (jazol1 != null && jazol2 != null) {
            if (jazol1.element.compareTo(jazol2.element) < 0) {
                if (bul == 0) {
                    rezultat.insertLast(jazol1.element);
                    rjazol = jazol1;
                    bul++;
                } else if (jazol1.element.compareTo(rjazol.element) != 0) {
                    rezultat.insertLast(jazol1.element);
                    rjazol = jazol1;
                }
                jazol1 = jazol1.succ;
            } else {
                if (bul == 0) {
                    rezultat.insertLast(jazol2.element);
                    rjazol = jazol2;
                    bul++;
                } else if (jazol2.element.compareTo(rjazol.element) != 0) {
                    rezultat.insertLast(jazol2.element);
                    rjazol = jazol2;
                }
                jazol2 = jazol2.succ;
            }
        }

        if (jazol1 != null) {
            while (jazol1 != null) {
                if (jazol1.element.compareTo(rjazol.element) != 0) {
                    rezultat.insertLast(jazol1.element);
                    rjazol = jazol1;
                }

                jazol1 = jazol1.succ;
            }
        }

        if (jazol2 != null) {
            while (jazol2 != null) {
                if (jazol2.element.compareTo(rjazol.element) != 0) {
                    rezultat.insertLast(jazol2.element);
                    rjazol = jazol2;
                }

                jazol2 = jazol2.succ;
            }
        }
        return rezultat;

    }
}

public class SLLJoinLists {
    public static void main(String[] args) throws IOException {


        SLL<Integer> lista1 = new SLL<Integer>();
        SLL<Integer> lista2 = new SLL<Integer>();

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        s = stdin.readLine();
        String[] pomniza = s.split(" ");

        for (int i = 0; i < N; i++) {
            lista1.insertLast(Integer.parseInt(pomniza[i]));
        }

        s = stdin.readLine();
        N = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista2.insertLast(Integer.parseInt(pomniza[i]));
        }

        JoinSortedLists<Integer> j = new JoinSortedLists<Integer>();
        SLL<Integer>spoeni;
        spoeni = j.join(lista1, lista2);
        // spoeni = lista1.joinLists(lista2);
        Iterator<Integer> it = spoeni.iterator();
        while (it.hasNext()) {
            System.out.print(it.next());
            if(it.hasNext())
                System.out.print(" ");
        }
        System.out.println();
    }
}
