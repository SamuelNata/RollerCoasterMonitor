package montanhaRussa.Domain;

import java.util.Random;

public class Person extends Thread{
	public String name;
	public boolean will;
	public boolean inMountain;
	private Car c;
	
	public Person(String name, Car c){
		super(name);
		will = false;
		inMountain = false;
		this.name = name;
		this.c = c;
	}
	
	@Override
	public void run(){
		Random r = new Random();
		while(c.stillRide()){
			if(!inMountain && !will){
				try {
					Thread.sleep(r.nextInt(1001));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				will = true;
				System.out.println(name + " quer andar na montanha russa.");
			}
			if(will && !inMountain){
				this.board();
			}
			if(!will && inMountain){
				this.unboard();
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void board(){
		if( c.stillRide() ){	c.load(this);		}
	}
	
	public synchronized void unboard(){
		c.unload(this);
	}
	
	@Override
	public String toString(){
		return name;
	}
	
}
