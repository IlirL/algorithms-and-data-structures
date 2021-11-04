public class SSLNode<E>{
	protected E element;
	protected SLLNode <E> succ;
	protected SLLNode (E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}
}

public class SLL<E>{
	private SLLNode<E> first;
	public SLL() {
		this.first = null;
	}
	public void insertFirst(E o) {
		SLLNode<E> ins = new SLLNode<E>(o, first)
			first = ins;
		
	}
	
	public void insertAfter(E o, SLLNode<E> after) {
		if(node != null)
		{
			SLLNode<E> ins = new SLLNode<E>(o, node.succ);
			node.succ = ins;
		}
	}
	
	public void insertBefore(E o, SLLNode<E> before) {
		if(first!=null) {
			SLLNode<E>tmp = first;
			if(first == before)
			{
				this.insertFirst(o);
				return;
			}
			
			//if first!=before;
			while(tmp.succ != before)
			{
				tmp = tmp.succ;
				if(tmp.succ == before)
				{
					SLLNode<E> ins = new SLLNode<E>(o, before);
					tmp.succ = ins;
				}
				else {
					System.out.println("The element is not in the list");
				}
			
			}
		}
	}
	
	public E deleteFirst() {
		if(first!=null)
		{
			SLLNode<E> tmp = first;
			first = first.succ;
			return tmp.element;
			
		}
	}
	
	public E delete(SLLNode<E> node) {
		if(first!=null)
		{
			SLLNode<E>  tmp = first;
			while(tmp.succ!=node && tmp.succ.succ !=null)
			{
				tmp = tmp.succ;
				if(tmp.succ == node)
				{
					tmp.succ = tmp.succ.succ;
					return node.element;
				}
			}
		}
	}
	
	public SLLNode<E> getFirst(){
		
	}
	
	public void insertLast(E o) {
		if(first != null)
		{
			SLLNode<E> tmp = first;
			while(temp.succ != null)
			{
				temp = temp.succ;
			}
			SLLNode<E> newNode  = new SLLNode<E>(o, null);
			temp.succ = newNode;
		}
		
		else {
			insertFirst(o);
		}

	}

	public SLLNode<E> find(E o){
		if(first != null){
			SLLNode<E> tmp = first;
			while(tmp.element != o && tmp.succ != null)
			{
				tmp = temp.succ;
				
					
			}
				if(tmp.element == 0)
				{
					return tmp;
				}
				else
				{
					System.out.println("The element is not in the list.");
				}
			}
		else {
			System.out.println("The list is empty;)
		}
		
		return first;
	}
	
	public int length() {
		int ret;
		if(first!=null) {
			SLLNode<E>tmp = first;
			ret = 1;
			while(tmp.succ != null) {
				tmp = tmp.succ;
				ret++;
			}
			
			return ret;
		}
			else 
			{
				return 0;
			}
		
	}
	
	public void merge(SLL<E>in)
	{
		if(first != null) {
			SLLNode<E> tmp = first;
			while(tmp.succ != null)
			{
				tmp = tmp.succ;
			}
			tmp.succ = in.getFirst();
		}
		else {
			first = in.getFirst();
		}
	}
	
	public Iterator<e> iterator(){
		//vraka iterator koj gi poosetuva site elementi na listat of levo na desno
		return new LRIterato<E>();
	}
	
//	Inner class
	private class LRIterator<E> implements Iterator<E>{
		private SLLNode<E> place, prev, curr;
		private LRIterator() {
			place = (SLLNode<E>) first;
			curr = prev = null;
		}
		
		public boolean hasNext() {
			return (place != null);
			
		}
		
		public E next() {
			if(place == null)
			{
				throw new NoSuchElementException();
				
			}
			
			E nextElem = place.element;
			prev = curr;
			curr = place;
			place = place.succ;
			return nextElem;
		}
		
		public void remove()
		{
			
		}
	}
	
}