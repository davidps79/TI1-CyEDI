package model;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Elevator {
	private Stack<Person> occupants;
	private PriorityQueue<Integer> master;
	private PriorityQueue<Integer> up;
	private PriorityQueue<Integer> down;
	private Edifice edifice;
	private int currentFloor;
	private int direction;

	public Elevator(Edifice edifice) {
		direction = 1;
		occupants = new Stack<Person>();	
		up = new PriorityQueue<Integer>();
		down = new PriorityQueue<Integer>(Collections.reverseOrder());
		master = up;
		this.edifice = edifice;
		this.currentFloor = 1;
	}
	
	public void enter(Person person) {
		occupants.push(person);
		//System.out.println(person.getName() + " has enter to the elevator");
	}
	
	public Person getOut() {
		Person person = occupants.pop();;
		//System.out.println(person.getName() + " has left the elevator");
		return person;
	}

	public void addDestination(int destination) {
		if (destination>currentFloor) {
			if (!up.contains(destination)) up.offer(destination);
		} else
			if (!down.contains(destination)) down.offer(destination);
	}
	
	public void simulate() {
		//System.out.println("- Elevator --- ");		
		//System.out.println("The elevator is in floor " + currentFloor);
		Stack<Person> temp = new Stack<>();
		while(!occupants.isEmpty()) {
			if (occupants.peek().getTargetFloor() == currentFloor) { // La persona ha llegado a su piso destino
				edifice.getFloor(currentFloor).addPerson(getOut()); 
			} else {
				temp.push(occupants.pop()); // Si la persona no va a ese piso, sale temporalmente del ascensor para dar paso a los siguientes
			}
		}
		
		while (!temp.isEmpty()) { // Las personas que salieron temporalmente para dejar salir a las otras vuelven a ingresar al ascensor
			occupants.push(temp.pop());
		}
		
		Queue<Person> queue;
		if (direction == 1) { // Si el elevador va subiendo, se suben los que necesitan subir}
			queue = edifice.getFloor(currentFloor).getNeedGoUp();
		} else { // Si el elevador va bajando, se suben los que necesitan bajar
			queue = edifice.getFloor(currentFloor).getNeedGoDown();
		}
		
		while (!queue.isEmpty()) { // Se suben dependiendo de la condición anterior
			Person person = queue.poll();
			addDestination(person.getTargetFloor());
			//System.out.println(person.getName() + " added floor " + person.getTargetFloor());
			enter(person);
		}
		
		/*System.out.print("[ ");
		for (Person person : occupants) {
			System.out.print(person.getName() + " ");
		}
		System.out.println("]");*/
		
		if (direction == 1 && up.isEmpty()) {
			master = down;
			direction = -1;
		}
		
		if (direction == -1 && down.isEmpty()) {
			master = up;
			direction = 1;
		}

		if (!master.isEmpty()) currentFloor = master.poll();
	}

	public int getDirection() {
		return direction;
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}
}
