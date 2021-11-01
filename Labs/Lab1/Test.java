package problems;

import java.util.Scanner;

public class Test {


interface IMarathon{

    public Athlete bestTime();
    
    int AthletesFrom(String s);
}

public static class Athlete{

    private String name;
    private String gender;
    private int age;
    private double score;
    private String origin;
	public Athlete() {
	}
	public Athlete(String name, String gender, int age, double score, String origin) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.score = score;
		this.origin = origin;
	}
	@Override
	public String toString() {
		return name + "\n" + age  +"\n" + origin + "\n" + score + "\n";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	
    
}

public static class Marathon implements IMarathon{
	private String host;
	private int year;
	private Athlete[] athletes;
	public Marathon() {
		super();
	}
	public Marathon(String host, int year, Athlete[] athletes) {
		super();
		this.host = host;
		this.year = year;
		this.athletes = athletes;
	}
	
	@Override
	public String toString() {
		String s = new String("");
		
		s+=host + "\n" + String.valueOf(year) + "\n";
		
		for(int i = 0; i<athletes.length; i++)
		{
//			if(i < athletes.length-1)
//			s+=athletes[i].name + "\n" + athletes[i].getAge() + "\n" + athletes[i].getOrigin() + "\n" + athletes[i].getScore() + "\n";
			s+=athletes[i].toString();
		
		}
		return s;
	}
	
	public Athlete bestTime()
	{
		double min = 100000000;
		int k = 0;
		for(int i = 0; i<athletes.length; i++)
		{
			if(athletes[i].getScore() < min)
			{
				min = athletes[i].getScore();
				k = i;
//				System.out.println("NowTheAthleteis: " +  athletes[i].toString());
			}
		}
		return athletes[k];
	}
	
	
	public int AthletesFrom(String s)
	{
		
		int n = 0;
		
		for(int i = 0; i<athletes.length; i++)
		{
			if(athletes[i].getOrigin().equals(s))
				n++;
		}
		
		return n;
	}
	
}
	
	public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        Athlete[] atleticari = new Athlete[n];
        
        String ime;
        String pol;
        int vozrast;
        double vreme;
        String zemja;
        
        input.nextLine();
            
        for(int i=0;i<n;i++)
        {
            ime = input.nextLine();
            pol = input.nextLine();
            vozrast = input.nextInt();
            vreme = input.nextDouble();
            input.nextLine();
            zemja = input.nextLine();
            atleticari[i]=new Athlete(ime,pol,vozrast,vreme,zemja);
        }
        
        String mesto;
        int godina;
        String zemjaP;
        mesto = input.nextLine();
        godina = input.nextInt();
        input.nextLine();
        
        Marathon m1 = new Marathon(mesto, godina, atleticari);
        System.out.print(m1.toString());
        
        zemjaP = input.nextLine();
        System.out.println("Prvo mesto: " + m1.bestTime().toString());
        System.out.println("Ima vkupno " + m1.AthletesFrom(zemjaP) + " atleticar/i od " + zemjaP);
    }
}

