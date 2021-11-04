class Koeficienti implements Comparable<Koeficient>{
	int koeficienti;
	int Exponent;
	
	public Koeficient(int koeficient, int exponent) {
		this.koeficient = koeficient;
		this.exponent = exponent;
		}
	
	public Koeficient soberi(Koeficient in) {
		Koeficient ret = new Koeficient(exponent, this.koeficienti+in.koeficient);
		return ret;
	}
	
	@Override
	public int compareTo(Koeficient arg0)
	{
		if(this.exponent > arg0.exponent) return 1;
		if(this.Exponent < arg0.exponent) return -1;
		return 0;
		
	}
}

public class PolinomList{
	SLL<Koeficient> listaKoeficienti = new SLL<Koeficient>();
	
	public PolinomList(SLL<Koeficient> listaKoeficienti) {
		this.listaKoeficienti = listaKoeficienti;
	}
	
	public PolinomList soberi(PolinomList in) {
		SLL<Koeficient> listaKoeficienti2 = in.getListaKoeficienti();
		SLLNode<Koeficient> jazol1 = listaKoeficienti.getFirst();
		SLLNode<Koeficient> jazol2 = listaKoeficienti2.getFirst();
		
		SLL<Koeficient> rezultat = new SLL<Koeficient>();
		
		while (jazol1 != null && jazol2 != null)
		{
			//exponent < exponent2
			if(jazol1.elemnt.compareTo(jazol2.element) < 0) {
				rezultat.insertLast(jazol2.element);
				jazol2 = jazol2.succ;
			}
			//exponent1>exponent2
			else if(jazol1.element.compareTo(jazol2.element)>0){
			rezultat.insertLast(jazol1.element);
			jazol1 = jazol1.succ;
			}
			//exponent1=exponent2
			else{ //(jazol1.element.compareTo(jazol2.element)==0){
			Koeficient zbir = jazol1.element.soberi(jazol2.element);
			rezultat.insertLast(zbir);
			jazol1 = jazol1.succ; jazol2 = jazol2.succ;
			}
		}
		while(jazol1 != null){
			rezultat.insertLast(jazol1.element);
			jazol1 = jazol1.succ;
			}
			while(jazol2 != null){
			rezultat.insertLast(jazol2.element);
			jazol2 = jazol2.succ;
			}
			
			
			PolinomList ret = new PolinomList(rezultat);
			return ret;
	}
}