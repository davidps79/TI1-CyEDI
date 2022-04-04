package model;

public class Person {
	private String name;
	private int destination;
	private int targetFloor;
	
	public Person(String name, int destination, int targetFloor) {
		this.name = name;
		this.destination = destination;
		this.targetFloor = targetFloor; 
	}
	
	public int getDestination() {
		return destination;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTargetFloor() {
		return targetFloor;
	}
}
