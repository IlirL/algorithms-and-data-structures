package problems;
import java.util.Scanner;


abstract class Trip{
	private String name;
	private int price;
	public Trip() {
		super();
	}
	public Trip(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public abstract int vratiVremeVoDenovi();
	public static int  minPrice(Trip niza[], int n, Trip holiday)
	{
		int min = 100000, k = 0;
		for(int i = 0; i<n; i++)
		{
			if(niza[i].vratiVremeVoDenovi() > holiday.vratiVremeVoDenovi() && niza[i].getPrice() < min)
			{
				min = niza[i].getPrice();
				k = i;
			}
			
		}
		
		return min;
	}
}


class FestiveTrip extends Trip{
	private int startDay;
	private int startMonth;
	private int endDay;
	private int endMonth;
	
	
	public FestiveTrip() {
		super();
	}
	
	public FestiveTrip(String name, int price) {
		super(name, price);
	}
	
	public FestiveTrip(String name, int price, int startDay, int startMonth, int endDay, int endMonth) {
		super(name, price);
		try {
			
			this.startDay = startDay;
			this.startMonth = startMonth;
			this.endDay = endDay;
			this.endMonth = endMonth;
			if((startMonth ==  endMonth && startDay > endDay) || startMonth > endMonth) {
				throw new Exception();
			}
				
		}
		
		catch(Exception e)
		{
			this.startDay = endDay;
			this.endDay = startDay;
			this.startMonth = endMonth;
			this.endMonth = startMonth;
			System.out.println("Exeption");
		}
		
//		System.out.println("Exeption");
		
	
	}
	
	public int getStartDay() {
		return startDay;
	}
	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}
	public int getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(int startMonth) {
		this.startMonth = startMonth;
	}
	public int getEndDay() {
		return endDay;
	}
	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}
	public int getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(int endMonth) {
		this.endMonth = endMonth;
	}
	@Override
	public int vratiVremeVoDenovi() {
		return (endMonth - startMonth)*30 + endDay - startDay;
	}
	
}

class Vacation extends Trip{
	private int duration;

	

	public Vacation() {
		super();
	}
	public Vacation(String name, int price) {
		super(name, price);
	}

	
	public Vacation(String name, int price, int duration) {
		super(name, price - 1000);
		this.duration = duration;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Override
	public int vratiVremeVoDenovi() {
		return duration;
	}
}

public  class Test {

	
	public static void main(String[] args) {
		
		
		int n;
		Scanner in=new Scanner(System.in);
		n=in.nextInt();
		
		Trip nizaPatuvanje[]=new Trip[n];
		
		for (int i=0;i<n;i++){
			int tip=in.nextInt();
			if (tip==0){
				String ime=in.next();
				int cena =in.nextInt();
				int vreme=in.nextInt();
				nizaPatuvanje[i]=new Vacation(ime,cena,vreme);
			}
			else {
				String ime=in.next();
				int cena =in.nextInt();
				int pocD=in.nextInt();
                int pocM=in.nextInt();
				int krajD=in.nextInt();
				int krajM=in.nextInt();
				nizaPatuvanje[i]=new FestiveTrip(ime,cena,pocD,pocM, krajD,krajM);
				
			}
		}
		
		//решение на барање 1
		for(int i = 0; i<n; i++)
		{
			if(nizaPatuvanje[i] instanceof FestiveTrip) {
				if(((FestiveTrip)nizaPatuvanje[i]).getStartMonth() == 6)
				{
					System.out.print(nizaPatuvanje[i].getName() + " ");
				}
			}
		}
        //решение на барање 2
		double average = 0;
		for(int i = 0; i<n; i++)
		{
			average+=nizaPatuvanje[i].vratiVremeVoDenovi();
			
		}
		average/=n;
		System.out.println("\n"+average);
        //решение на барање 3   
		String ime=in.next();
		int cena =in.nextInt();
		int vreme=in.nextInt();
		
		Vacation holiday = new Vacation(ime, cena, vreme);
		//решение на барање 4
		System.out.println(Trip.minPrice(nizaPatuvanje, n, holiday));
		

	}

}


