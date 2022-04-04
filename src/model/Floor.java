package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Floor {
	private int number;
	private HashMap<Integer, Office> offices;
	private Set<Person> occupants;
	private Elevator elevator;
	private Queue<Person> needGoUp;
	private Queue<Person> needGoDown;
	private Edifice edifice;
	
	public Floor(int n, int o, int oStart, Elevator elevator, Edifice edifice) {
		number = n;
		occupants = new HashSet<Person>();
		needGoUp = new LinkedList<Person>();
		needGoDown = new LinkedList<Person>();
		offices = new HashMap<Integer, Office>();
		for (int i=0; i<o; i++) {
			offices.put(oStart-i, new Office());
		}
		
		this.elevator = elevator;
		this.edifice  = edifice;
	}
	
	public void addPerson(String name, int destination, int targetFloor) {
		if (destination>edifice.getTotalOffices()) {
			System.out.println(name + " goes to an INEXISTENT OFFICE, omitted in simulation");
			edifice.addServedPerson();
		} else
		occupants.add(new Person(name, destination, targetFloor));
	}

	public void addPerson(Person person) {
		occupants.add(person);
	}
	
	public HashMap<Integer, Office> getOffices() {
		return offices;
	}
	
	public void simulate() {
		//System.out.println("- Floor " + number + " --- ");
		for (Person person : occupants) {
			if (person.getTargetFloor() == number) { // Si la persona está en el piso en el que está su oficina destino	
				System.out.println(person.getName() + " found his office in the floor " + number);
				offices.get(person.getDestination()).setOccupant(person);
				edifice.addServedPerson();
				
			} else { // Si la persona debe tomar el ascensor para ir a otro piso
				//System.out.println(person.getName() + " has to move to floor " + person.getTargetFloor());
				if (person.getTargetFloor()>number) { // La persona necesita subir
					needGoUp.offer(person); 
				}
				else {
					needGoDown.offer(person); // La persona necesita bajar
				}
				elevator.addDestination(number);
			}
		}
		
		occupants.clear();
		
		if (!needGoDown.isEmpty()) {
			elevator.addDestination(number);
		}
		
		if (!needGoUp.isEmpty()) {
			elevator.addDestination(number);
		}
	}
	
	public Set<Person> getOccupants(){
		return occupants;
	}
	
	public Queue<Person> getNeedGoUp() {
		return needGoUp;
	}

	public Queue<Person> getNeedGoDown() {
		return needGoDown;
	}
}
