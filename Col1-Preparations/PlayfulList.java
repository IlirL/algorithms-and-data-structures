import java.util.Scanner;

class DLLNode<E extends Comparable<E>> {
    protected E element;
    protected int br_pojavuvanja;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
        this.br_pojavuvanja = 1;
    }

    @Override
    public String toString() {
        return element.toString()+" Br.Pojavuvanja:"+this.br_pojavuvanja;
    }
}
 class DLL<E extends Comparable<E>> {
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

     public DLLNode<E> find(E o) {
         if (first != null) {
             DLLNode<E> tmp = first;
             while (tmp.element != o && tmp.succ != null)
                 tmp = tmp.succ;
             if (tmp.element == o) {
                 return tmp;
             } else {
                 System.out.println("Elementot ne postoi vo listata");
             }
         } else {
             System.out.println("Listata e prazna");
         }
         return first;
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

     public E delete(DLLNode<E> node) {
         if (node == first) {
             deleteFirst();
             return node.element;
         }
         if (node == last) {
             deleteLast();
             return node.element;
         }
         node.pred.succ = node.succ;
         node.succ.pred = node.pred;
         return node.element;

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

     public String toStringR() {
         String ret = new String();
         if (last != null) {
             DLLNode<E> tmp = last;
             ret += tmp + "<->";
             while (tmp.pred != null) {
                 tmp = tmp.pred;
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
    public class PlayfulList {


        public static void main(String[] args) {
            DLL<Character> newDLL = new DLL<Character>();
            Scanner s = new Scanner(System.in);
            int numberNodes = s.nextInt();
            char c;
            for(int i = 0; i<numberNodes; i++)
            {
                c = s.next().charAt(0);
                newDLL.insertLast(c);
            }
            //now we finished with the dll
            boolean fromBeginning = true;
            while(newDLL.length() > 1){

                if(fromBeginning){
                    DLLNode<Character> firstNode = newDLL.getFirst();
                    boolean deleteNode = false;
                    while(firstNode!=null)
                    {
                        if(deleteNode)
                        {
                            DLLNode<Character> copyNode = firstNode;
                            newDLL.delete(copyNode);
                        }
                        firstNode = firstNode.succ;
                        deleteNode = !deleteNode;
                    }
                }
                else{
                    //from the end
                    DLLNode<Character> lastNode = newDLL.getLast();
                    boolean deleteNode = false;
                    while(lastNode!=null)
                    {
                        if(deleteNode)
                        {
                            DLLNode<Character> copyNode = lastNode;
                            newDLL.delete(copyNode);
                        }
                        lastNode = lastNode.pred;
                        deleteNode = !deleteNode;
                    }
                }
                fromBeginning = !fromBeginning;

                //print after deletion
                System.out.print("After deletion: ");
                DLLNode<Character> firstNode = newDLL.getFirst();
                while(firstNode != null)
                {
                    System.out.print(firstNode.element + ", ");
                    firstNode = firstNode.succ;
                }
                System.out.println();
            }
        }
    }

