package model;

import java.util.HashMap;

public class Edifice {
	private String id;
	private HashMap<Integer, Floor> floors;
	private Elevator elevator;
	private int totalPeople;
	private int servedPeople;
	private int totalOffices;
	
	public Edifice(String id, int f, int o, int t, int to) {
		this.id = id;
		totalPeople = t;
		servedPeople = 0;
		floors = new HashMap<Integer, Floor>();
		int oTotal = f*o;
		elevator = new Elevator(this);
		for (int i=0; i<f; i++) {
			floors.put(i+1, new Floor(i+1 , o, oTotal-(i*o), elevator, this));
		}
		totalOffices = to;
	}
	
	public void addServedPerson() {
		servedPeople++;
		System.out.println("Served: " + servedPeople + "/" + totalPeople);
	}
	
	public Floor getFloor(int number) {
		return floors.get(number);
	}
	
	public void simulate() {
		//int i=1;
		while (servedPeople != totalPeople) {
			//System.out.println("-- Step " + i + " ---------------- ");
			for (Floor floor : floors.values()) {
				floor.simulate(); // Inicia la simulación en cada piso
			}
			
			elevator.simulate();
			//System.out.println("");
			//i++;
		}
	}
	
	public String getId() {
		return id;
	}
	
	public String getFinalState() {
		String s = "Final state of edifice " + id + "\n[ ";
		for (Floor f : floors.values()) {
			for (Office o : f.getOffices().values()) {
				if (o.getOccupant()!=null) s+= o.getOccupant().getName() + " ";
			}
		}
		s+="]\n";
		return s;
	}
	
	public int getTotalOffices() {
		return totalOffices;
	}
}
