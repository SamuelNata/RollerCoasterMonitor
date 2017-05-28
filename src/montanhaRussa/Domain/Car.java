package montanhaRussa.Domain;

import java.util.ArrayList;
import java.util.Arrays;

public class Car implements Runnable {
	public static int capacity;
	public static int inCount;
	public static int outCount;
	private String name;
	public ArrayList<Person> passengers;
	private static int rides;
	
	public Car(String name, int capacity, int maximunRuns){
		this.name = name;
		Car.capacity = capacity;
		Car.rides = maximunRuns;
		passengers = new ArrayList<Person>();
	}
	
	@Override
	public void run(){
		while(rides>0){
			Car.inCount = 0;
			Car.outCount = 0;
			
			//Full Car
			System.out.println(name+" esperando pessoas: "+ Arrays.toString(passengers.toArray()));
			while(inCount<capacity){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(name+" cheio: "+ Arrays.toString(passengers.toArray()));
			
			//Ride
			System.out.println(name+" Correndo");
			ride();
			
			//Empty Car
			System.out.println(name+" esvaziando: "+ Arrays.toString(passengers.toArray()));
			while(outCount<capacity){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(name+" saiu todo mundo: "+ Arrays.toString(passengers.toArray()));
			
			//Count ride
			rides--;
			System.out.println("-----Fechando o brinquedo em: "+rides);
		}	
	}
	
	public synchronized void ride(){
		for( Person p : passengers ){
			p.will=false;
		}
	}
	
	public synchronized void load( Person p ){
		if( rides>0 && inCount<capacity){
			inCount++;
			p.inMountain=true;
			passengers.add(p);
			System.out.println("\t\t>>>" + p.name + " entrou");
		}
	}
	
	public synchronized void unload( Person p ){
		p.inMountain=false;
		passengers.remove(p);
		outCount++;
		System.out.println("\t\t<<<" + p.name + " saiu");
	}
	
	public boolean stillRide(){
		return rides!=0;
	}
	
}
