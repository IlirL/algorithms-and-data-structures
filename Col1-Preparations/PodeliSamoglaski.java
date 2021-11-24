import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

}

public class PodeliSamoglaski {


    public static void podeliSamoglaski(DLL<Character> lista, DLL<Character> lsamoglaski, DLL<Character> lsoglaski) {

        /*
         *
         *
         * Vasiot kod tuka
         *
         *
         */
        DLLNode<Character> fromStart = lista.getFirst();
        DLLNode<Character> fromEnd = lista.getLast();

        boolean fromBeginning = true;
        while(!fromStart.equals(fromEnd))
        {
            if(fromBeginning)
            {
                if(fromStart.element == 'a' || fromStart.element == 'e' || fromStart.element == 'i'
                    || fromStart.element == 'o' || fromStart.element == 'u'
                )
                {
                    lsamoglaski.insertLast(fromStart.element);
                }
                else
                {
                    lsoglaski.insertLast(fromStart.element);
                }
                fromStart = fromStart.succ;
            }
            else{
                if(fromEnd.element == 'a' || fromEnd.element == 'e' || fromEnd.element == 'i'
                        || fromEnd.element == 'o' || fromEnd.element == 'u')
                {
                    lsamoglaski.insertLast(fromEnd.element);
                }
                else
                {
                    lsoglaski.insertLast(fromEnd.element);
                }
                fromEnd = fromEnd.pred;
            }
            fromBeginning = !fromBeginning;
        }

        if(fromBeginning)
        {
            if(fromStart.element == 'a' || fromStart.element == 'e' || fromStart.element == 'i'
                    || fromStart.element == 'o' || fromStart.element == 'u'
            )
            {
                lsamoglaski.insertLast(fromStart.element);
            }
            else
            {
                lsoglaski.insertLast(fromStart.element);
            }
            fromStart = fromStart.succ;
        }
        else{
            if(fromEnd.element == 'a' || fromEnd.element == 'e' || fromEnd.element == 'i'
                    || fromEnd.element == 'o' || fromEnd.element == 'u'
            )
            {
                lsamoglaski.insertLast(fromEnd.element);
            }
            else
            {
                lsoglaski.insertLast(fromEnd.element);
            }
            fromEnd = fromEnd.pred;
        }


    }

    public static void main(String[] args) throws IOException {
        DLL<Character> lista = new DLL<Character>(), samoglaski = new DLL<Character>(), soglaski = new DLL<Character>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        for (int i = 0; i < s.length(); i++) {
            lista.insertLast(s.charAt(i));
        }

        podeliSamoglaski(lista, samoglaski, soglaski);

        // Pecatenje samoglaski
        DLLNode<Character> tmp = samoglaski.getFirst();
        while (tmp != null) {
            System.out.print(tmp.element);
            if (tmp.succ != null)
                System.out.print(" ");
            tmp = tmp.succ;
        }
        System.out.println();
        // Pecatenje soglaski
        tmp = soglaski.getFirst();
        while (tmp != null) {
            System.out.print(tmp.element);
            if (tmp.succ != null)
                System.out.print(" ");
            tmp = tmp.succ;
        }
        System.out.println();
    }

}