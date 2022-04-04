package model;

import java.util.HashMap;

public class ElevatorSystem {
	private HashMap<String, Edifice> edifices;
	
	public ElevatorSystem() {
		edifices = new HashMap<String, Edifice>();
	}

	public void addEdifice(String k, int f, int o, int t, int to) {
		Edifice v = new Edifice(k, f, o, t, to);
		edifices.put(k, v);
	}
	
	public Edifice getEdifice(String id) {
		return edifices.get(id);
	}
	
	public void startSimulation() {
		for (Edifice edifice : edifices.values()) {
			System.out.println("Movements of edifice " + edifice.getId() + "\n");
			edifice.simulate();
			System.out.println(edifice.getFinalState());
		}
	}
}
