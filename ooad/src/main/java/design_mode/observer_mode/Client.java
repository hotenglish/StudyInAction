package design_mode.observer_mode;

import java.util.Observable;
import java.util.Observer;

public class Client {

	public static void main(String[] args) {
		Dog dog = new Dog();
		Master m = new Master();
		dog.addObserver(m);
		Wife w = new Wife();
		dog.addObserver(w);
		//dog.deleteObservers();
		dog.grow();		
		dog.wang();
		
	}
}

class Dog extends Observable{

	public void grow(){
		this.setChanged();
		this.notifyObservers();
		System.out.println("OberserverCount:"+this.countObservers());
	}
	public void wang(){
		System.out.println("Wang wang...grow?:"+this.hasChanged());		
		this.notifyObservers();
	}
}

class Master implements Observer{
	public void update(Observable o,
            Object arg){
		System.out.println("Master:My dog grow!");
	}
}

class Wife implements Observer{
	public void update(Observable o,
            Object arg){
		System.out.println("Wife:My dog grow!");
	}
}

