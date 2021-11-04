public class DLLNode <E extends Comparable<E>>{
	protected E element;
	protected int br_pojavuvanja;
	protected DLLNode<E> pred, succ;
	
	public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ)
	{
		this.element = elem;
		this.pred = pred;
		this.succ = succ;
		this.br_pojavuvanja = 1;
	}
	

	@Override
	public String toString() {
	return
	element.toString()+"Br.Pojavuvanja:"+this.br_pojavuvanja;
	}
}


public class DLL<E extends Comparable<E>>{
//	All previously written methdosd of DLL
	public void izvadiDupliIPrebroj() {
		if(first != null) {
			DLLNode<E> tmp=  first, tmp2 = tmp.succ;
			while(tmp.succ!= null) {
				while(tmp2 != null) {
					if(tmp.element.compareTo(tmp2.element) == 0) {
						tmp.br_pojavuvanja++;
						tmp2 = tmp2.succ;
						this.delete(tmp2.pred);		
					}
					else
						tmp2 = tmp2.succ;
				}
				tmp = tmp.succ; tmp2 = tmp.succ;
			}
		}else
			return;
	}
}