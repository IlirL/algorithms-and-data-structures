
package SpecialJoinLists;

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

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public SLLNode<E> getSucc() {
        return succ;
    }

    public void setSucc(SLLNode<E> succ) {
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
            ret+=tmp +" ";
            while(tmp.succ != null){
                tmp = tmp.succ;
                ret+=tmp + " ";

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

class SpecialJoinLists<E extends Comparable<E>> {
    SLL<E> firstList, secondList;

    public SpecialJoinLists() {
        this.firstList = new SLL<E>();
        this.secondList = new SLL<E>();
    }

    public SLL<E> specialJoin(SLL<E> lista1, SLL<E> lista2)
    {
        boolean turnForOne = true;
        SLLNode<E> firstNodeFirstList = lista1.getFirst();
        SLLNode<E> firstNodeSecondList = lista2.getFirst();
        SLL<E> result = new SLL<E>();


        while((firstNodeFirstList != null && firstNodeFirstList.succ != null) && (firstNodeSecondList != null && firstNodeSecondList.succ != null))
        {
            if(turnForOne)
            {
                result.insertLast(firstNodeFirstList.element);
                result.insertLast(firstNodeFirstList.succ.element);;
                firstNodeFirstList = firstNodeFirstList.succ.succ;
            }
            else {
                result.insertLast(firstNodeSecondList.element);
                result.insertLast(firstNodeSecondList.succ.element);

                firstNodeSecondList = firstNodeSecondList.succ.succ;
            }
            turnForOne = !turnForOne;
        }

        if(!turnForOne && firstNodeSecondList != null)
        {
            for(int i = 0; i<2; i++)
            {
                if(firstNodeSecondList == null)
                    break;
                result.insertLast(firstNodeSecondList.element);
                firstNodeSecondList = firstNodeSecondList.succ;
            }
        }
        while(firstNodeFirstList != null)
        {
            result.insertLast(firstNodeFirstList.element);
            firstNodeFirstList = firstNodeFirstList.succ;
        }

        while(firstNodeSecondList != null)
        {
            result.insertLast(firstNodeSecondList.element);
            firstNodeSecondList = firstNodeSecondList.succ;
        }

            return result;

    }
}

public class SpecialSLLJoin {


    public static void main(String[] args) throws IOException{
    SLL<Integer> lista1 = new SLL<Integer>();
    SLL<Integer>lista2 = new SLL<Integer>();
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
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
        SpecialJoinLists<Integer> j = new SpecialJoinLists<Integer>();

        SLL<Integer> spoeni = j.specialJoin(lista1,lista2);
        System.out.println(spoeni);
    }
}
