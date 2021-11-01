//Write a class Example with the following attributes:
//- description (string)
//- hours (int)
//- status (boolean)
//Implement the constructors, get and set methods and redefine toString() method
//Then write a class Employee with the following attributes:
//- Name (string)
//- Surname (string)
//- PointValue (static double)
//- Salary (double)
//- Experience (int)
//- Points (int)
//- tasks (tasks[10])
//- NoTasks(int)
//Implement default constructor and a constructor with parameters: name,
//surname, number of points, get and set methods and redefine toString() method.
//Write a method for adding new tasks. Additionally, write a method which will
//give the total hours needed for these tasks, and a method which will give the
//percentage of finished tasks.
//Develop a class Company with a list of employees. Implement the following
//methods:
//- mostWorking() – gives the employee with the highest total hours
//- printBySuccess() – sorts employees by success and prints their name, surname and success
//- print() – gives information about the employees

package problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class Problem2 {
 
	class Example{
		private String description;
		private int hours;
		private boolean status;
		
		
		
		public Example() {
			super();
		}



		public Example(String description, int hours)
		{
			this.description = description;
			this.hours =  hours;
//			this.status = status;
		}



		public String getDescription() {
			return description;
		}



		public void setDescription(String description) {
			this.description = description;
		}



		public int getHours() {
			return hours;
		}



		public void setHours(int hours) {
			this.hours = hours;
		}



		public boolean isStatus() {
			return status;
		}



		public void setStatus(boolean status) {
			this.status = status;
		}



		@Override
		public String toString() {
			return "Description: " + description +"\nhours: " + hours + "\nstatus: " + (status ? "active" : "finished");
		}
		
		
	}
	
	
	class Employee implements Comparator<Employee> {
		private String name;
		private String surname;
		private static double POINTVALUE = 50;
		private double salary;
		private int experience;
		private int points;
		private Example examples[];
		private int noTasks;
		public Employee() {
//			super();
			this.examples = new Example[10];
			this.noTasks = 0;
		}
		public Employee(String name, String surname, int points) {
			this();
//			super();
		
			this.name = name;
			this.surname = surname;
			this.points = points;
		}
		
		@Override
		public String toString() {
			return "Employee [name=" + name + ", surname=" + surname + ", salary=" + salary + ", experience="
					+ experience + ", points=" + points + ", examples=" + Arrays.toString(examples) + ", noTasks="
					+ noTasks + "]";
		}
		
		
		public void addNewTask(Example e)
		{
			if(this.noTasks == 10)
				System.out.println("A task cannot be added");
			else
				this.examples[this.noTasks++] = e;
		}
		
		public int totalHours() {
			int total = 0;
			for(int i = 0; i<this.noTasks; i++)
			{
				total+=this.examples[i].getHours();
			}
			
			return total;
		
		}
		
		public double finishedTasks()
		{
			int finished = 0;
			for(int i = 0; i<this.noTasks; i++)
			{
				if(this.examples[i].isStatus())
				{
					finished++;
				}
			}
			
			return ((double)finished/this.noTasks) * 100;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSurname() {
			return surname;
		}
		public void setSurname(String surname) {
			this.surname = surname;
		}
		public static double getPOINTVALUE() {
			return POINTVALUE;
		}
		public static void setPOINTVALUE(double pOINTVALUE) {
			POINTVALUE = pOINTVALUE;
		}
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}
		public int getExperience() {
			return experience;
		}
		public void setExperience(int experience) {
			this.experience = experience;
		}
		public int getPoints() {
			return points;
		}
		public void setPoints(int points) {
			this.points = points;
		}
		public Example[] getExamples() {
			return examples;
		}
		public void setExamples(Example[] examples) {
			this.examples = examples;
		}
		public int getNoTasks() {
			return noTasks;
		}
		public void setNoTasks(int noTasks) {
			this.noTasks = noTasks;
		}
		@Override
		public int compare(Employee o1, Employee o2) {
			// TODO Auto-generated method stub
		return o1.finishedTasks() < o2.finishedTasks() ? -1
				:o1.finishedTasks() > o2.finishedTasks() ? 1
				:0;
//			return 0;
		}
		
		
		
	}
	
	class Company{
		private Employee employees[];
		
		public Company(Employee[] employees)
		{
			this.employees = employees;
		}
		
		public Employee mostWorking() {
			Employee mostWorkingEmployee = this.employees[0];
			for(int i = 1; i<this.employees.length; i++)
			{
				if(this.employees[i].totalHours() > mostWorkingEmployee.totalHours())
				{
					mostWorkingEmployee = this.employees[i];
				}
			}
			
			return mostWorkingEmployee;
		}
		
		public void printBySuccess()
		{
			Arrays.sort(this.employees);
			for(int i = 0; i<this.employees.length; i++)
			{
				this.employees[i].toString();
			}
		}
		
		public void print()
		{
			for(int i = 0; i<this.employees.length; i++)
			{
				this.employees[i].toString();
			}
		}
	}
	
	public static void main(String[] args) {
		
		Problem2 prb = new Problem2();

		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		Employee [] pom = new Employee[n];
		for(int i = 0; i<n; i++){
		
			
		Employee v = prb.new Employee();
		v.setName(input.next());
		v.setSurname(input.next());
		v.setExperience(input.nextInt());
		v.setPoints(n);
		pom[i] = v;
		
		int p = input.nextInt();
		for(int j = 0;j<p;j++){
		Example z = prb.new Example();
		z.setHours(input.nextInt());
		z.setDescription(input.next());
		z.setStatus(input.nextBoolean());
		v.addNewTask(z);
		}
		}
		Company k =prb.new Company(pom);
		k.print();
		k.printBySuccess();
		System.out.println("The employee that works the most is: " + k.mostWorking());

		
	}
}
